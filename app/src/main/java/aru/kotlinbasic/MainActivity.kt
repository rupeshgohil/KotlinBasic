package aru.kotlinbasic

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import aru.kotlinbasic.api.ApiResponseInterface
import aru.kotlinbasic.api.ApiResponseManager
import aru.kotlinbasic.common.ShowToast


class MainActivity : AppCompatActivity(), ApiResponseInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        callSocialLogin()

    }
    fun callSocialLogin() {
       /* ApiRequestFace<Any>(this@MainActivity, ApiInitialize.initializes()
                .socialLogin(),
                1, true, this@MainActivity)*/

    }
    override fun getApiResponse(apiResponseManager: ApiResponseManager<*>) {
        if (apiResponseManager.type == 1) {
           // val mModel = apiResponseManager.response as LoginModal
            ShowToast("", this)
        }else{
            ShowToast("", this)
        }
    }
}
