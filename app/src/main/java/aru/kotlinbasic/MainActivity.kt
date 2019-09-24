package aru.kotlinbasic

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioGroup
import aru.kotlinbasic.api.ApiResponseInterface
import aru.kotlinbasic.api.ApiResponseManager
import aru.kotlinbasic.common.ShowToast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), ApiResponseInterface, AdapterView.OnItemSelectedListener {
    var mArrayPerod: ArrayList<String>? = ArrayList()
    var strCategory:String?= null
    var strGender:String?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        callSocialLogin()
        setDropdownList()
        selectGender()

    }

    private fun selectGender() {
        gender.setOnCheckedChangeListener(
                RadioGroup.OnCheckedChangeListener { group, checkedId ->

                    if (checkedId == R.id.rbmale) {
                        strGender = "Male"
                        Log.e("Gender",strGender)
                    } else {
                        strGender = "Female"
                        Log.e("Gender",strGender)

                    }

                })
    }

    private fun setDropdownList() {
        mArrayPerod!!.add("India")
        mArrayPerod!!.add("Englend")
        mArrayPerod!!.add("France")

        spinnercategory!!.onItemSelectedListener = this
        spinnercategory.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, mArrayPerod)
    }
    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        when (parent!!.id) {
            R.id.spinnercategory -> {
                Log.e("selected", mArrayPerod!!.get(position) + "null")
                strCategory = mArrayPerod!!.get(position)
                Log.e("Category:",strCategory)
            }

        }

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
