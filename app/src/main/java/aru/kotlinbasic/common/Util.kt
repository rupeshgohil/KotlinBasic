@file:Suppress("DEPRECATION")
package aru.kotlinbasic.common





import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.util.Patterns
import android.view.inputmethod.InputMethodManager
import android.app.Activity
import android.view.View
import android.widget.Toast
import aru.kotlinbasic.common.MyCustomProgressDialog


private val TAG = "Util"

fun getProgressDialog(context: Context): ProgressDialog {
    val myCustomProgressDialog = MyCustomProgressDialog(context)
    myCustomProgressDialog.isIndeterminate = true
    myCustomProgressDialog.setCancelable(false)
    myCustomProgressDialog.show()
    return myCustomProgressDialog

}

fun dismissDialog(context: Context, mProgressDialog: ProgressDialog) {
    try {
        if (mProgressDialog.isShowing) {
            mProgressDialog.dismiss()
        }
    } catch (e: Exception) {

    }

}

fun openSoftKeyboard(context: Context) {
    try {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    } catch (e: NullPointerException) {
        e.printStackTrace()
    } catch (e: Exception) {
        e.printStackTrace()
    }

}


fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null
}


fun isNetWork(context: Context): Boolean {
    var flag = false
    flag = isNetworkAvailable(context)
    return flag
}

fun hideKeyboard(activity: Activity) {
    val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view = activity.currentFocus
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(activity)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

@SuppressLint("ShowToast")
fun ShowToast(response: String,activity: Activity) {
    Toast.makeText(activity, response, Toast.LENGTH_SHORT).show()
}


