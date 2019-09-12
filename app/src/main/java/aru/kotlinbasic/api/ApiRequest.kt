package aru.kotlinbasic.api

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.widget.Toast
import aru.kotlinbasic.common.dismissDialog
import aru.kotlinbasic.common.getProgressDialog
import clipz.customer.utils.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("ParcelCreator")
class ApiRequest<T>(private val activity: Activity, objectType: T, private val TYPE: Int,
                    private val isShowProgressDialog: Boolean, private val apiResponseInterface: ApiResponseInterface) : Callback<T>, Parcelable {
    private var mProgressDialog: ProgressDialog? = null
    private var retryCount = 0
    private var call: Call<T>? = null

    init {
        showProgress()
        call = objectType as Call<T>?
        call!!.enqueue(this)
    }

    private fun showProgress() {
        if (isShowProgressDialog) {
            mProgressDialog = getProgressDialog(activity)
        }
    }

    private fun dismissProgress() {
        if (isShowProgressDialog) {
            dismissDialog(activity, mProgressDialog!!)
        }
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        dismissProgress()

        Log.e("API : ",call.request().url().toString()+ " CODe "+response.code())

        when
        {
            response.isSuccessful -> apiResponseInterface.getApiResponse(ApiResponseManager(response.body(), TYPE))

            response.code() == 503 -> {
                Toast.makeText(activity, response.message().toString(), Toast.LENGTH_SHORT).show()
            }
//            response.code() == 412 -> {
//                 apiResponseInterface.getApiResponse(ApiResponseManager(response.body(), TYPE))
//            }
        else ->
        {
                val error = ErrorUtils.parseError(response)

                /*
                * val error = ErrorUtils.parseError(response)
                when {
                    response.code() == 401 -> {
                        val preference = SessionManager(activity)
                        preference.logout()
                        val mIntent = Intent(activity, LoginActivity::class.java)
                        activity.startActivity(mIntent)
                        activity.finish()
                    }

                    else -> Toast.makeText(activity, error.message, Toast.LENGTH_SHORT).show()
                }
                * */
                when
                {
                    response.code() == 401 ->
                    {
                       /* val preference = SessionManager(activity)
                        preference.logout()*/
                        //val mIntent = Intent(activity, LoginActivity::class.java)
                       // activity.startActivity(mIntent)
                        //activity.finish()
                    }
                    response.code() == 412 ->
                    {
                    apiResponseInterface.getApiResponse(ApiResponseManager(response.body(), TYPE))
                    }
                    error.message.success != null -> Toast.makeText(activity, error.message.success.toString(), Toast.LENGTH_SHORT).show()
                    else -> Toast.makeText(activity, response.message().toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onFailure(call: Call<T>, error: Throwable) {
        error.printStackTrace()

        if (retryCount++ < TOTAL_RETRIES) {
            Log.v(TAG, "Retrying... ($retryCount out of $TOTAL_RETRIES)")
            retry()
            return
        }
        dismissProgress()
    }

    private fun retry() {
        call!!.clone().enqueue(this)
    }

    companion object {
        private val TAG = "ApiRequest"
        private val TOTAL_RETRIES = 2
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(TYPE)
        parcel.writeByte(if (isShowProgressDialog) 1 else 0)
        parcel.writeInt(retryCount)
    }

    override fun describeContents(): Int {
        return 0
    }


}
