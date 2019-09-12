package aru.kotlinbasic.api
import retrofit2.Call
import okhttp3.RequestBody
import okhttp3.MultipartBody
import retrofit2.http.*


interface ApiInterface {


    /*@GET("user/get_user_content")
    fun get_user_content(): Call<TutorialModel>

    @FormUrlEncoded
    @POST("user/register")
    fun register(@Field("type") type: String,
                 @Field("first_name") first_name: String,
                 @Field("last_name") last_name: String,
                 @Field("username") username: String,
                 @Field("email") email: String,
                 @Field("password") password: String,
                 @Field("mobile") mobile: String,
                 @Field("street") street: String,
                 @Field("city") city: String,
                 @Field("state") state: String,
                 @Field("zipcode") zipcode: String,
                 @Field("latitude") latitude: String,
                 @Field("longitude") longitude: String,
                 @Field("eDeviceType") eDeviceType: String,
                 @Field("vPushToken") vPushToken: String): Call<ResponseModel>


    @FormUrlEncoded
    @POST("user/email_exist")
    fun email_exist(@Field("email") email: String): Call<ResponseModel>

    @FormUrlEncoded
    @POST("user/username_exist")
    fun username_exist(@Field("username") email: String): Call<ResponseModel>


    @FormUrlEncoded
    @POST("user/login")
    fun login(@Field("username") username: String,
              @Field("password") password: String,
              @Field("eDeviceType") eDeviceType: String,
              @Field("vPushToken") vPushToken: String,
              @Field("type") type: String): Call<LoginResponseModel>


    @FormUrlEncoded
    @POST("user/change_password")
    fun change_password(@Header(TOKEN) token: String,
                        @Field("vOldPassword") vOldPassword: String,
                        @Field("vNewPassword") vNewPassword: String): Call<ResponseModel>


    @POST("user/logout")
    fun logout(@Header(TOKEN) token: String): Call<ResponseModel>


    @FormUrlEncoded
    @POST("user/forgot_password")
    fun forgotPassword(@Header(TOKEN) token: String,
                       @Field("email") email: String): Call<ResponseModel>

    @FormUrlEncoded
    @POST("user/notification_on_off")
    fun notification(@Header(TOKEN) token: String,
                     @Field("notification") first_name: String): Call<ResponseModel>


//    first_name:prem
//    last_name:limbachiya
//    email:premkiran.zestbrains@gmail.com
//    mobile:1234567893
//    street:ahmedabad
//    latitude:23.58615
//    longitude:72.28878
//    city:ahmedabad
//    state:gujarat
//    zipcode:380001
//    card_number:4242424242472
//    cvv:123
//    exp_month:12
//    exp_year:2018
//    radius:9


    @FormUrlEncoded
    @POST("user/edit_profile")
    fun update(@Header(TOKEN) token: String,
               @Field("first_name") first_name: String,
               @Field("last_name") last_name: String,
               @Field("email") email: String,
               @Field("mobile") mobile: String,
               @Field("street") street: String,
               @Field("city") city: String,
               @Field("state") state: String,
               @Field("zipcode") zipcode: String,
               @Field("latitude") latitude: String,
               @Field("longitude") longitude: String,
               @Field("card_number") card_number: String,
               @Field("cvv") cvv: String,
               @Field("exp_month") exp_month: String,
               @Field("exp_year") exp_year: String): Call<LoginResponseModel>


    @GET("user/get_hair_details")
    fun get_hair_details(): Call<HairDetailsModel>


    @FormUrlEncoded
    @POST("user/place_order")
    fun place_order(@Header(TOKEN) token: String,
                    @Field("address") first_name: String,
                    @Field("latitude") latitude: String,
                    @Field("longitude") longitude: String,
                    @Field("card_number") card_number: String,
                    @Field("cvv") cvv: String,
                    @Field("exp_month") exp_month: String,
                    @Field("exp_year") exp_year: String,
                    @Field("date_time") date_time: String,
                    @Field("hair_type") hair_type: String,
                    @Field("hair_count") hair_count: String,
                    @Field("promocode_id") promocode_id: String,
                    @Field("hair_cut_now") hair_cut_now: String,
                    @Field("stylist_code") stylist_code: String,
                    @Field("save_card") save_card: String): Call<PlaceOderModel>


    @FormUrlEncoded
    @POST("user/price_count")
    fun price_count(@Header(TOKEN) token: String,
                    @Field("hair_type") hair_type: String,
                    @Field("hair_count") hair_count: String): Call<PriceCountModel>


    @FormUrlEncoded
    @POST("user/apply_promocode")
    fun apply_promocode(@Header(TOKEN) token: String,
                        @Field("promocode") promocode: String,
                        @Field("fare_price") fare_price: String): Call<PromoCodeModel>


    @FormUrlEncoded
    @POST("user/get_order_details")
    fun get_order_details(@Header(TOKEN) token: String,
                          @Field("order_id") order_id: String): Call<PlaceOderResponseModel>

    @FormUrlEncoded
    @POST("user/get_stylist_by_code")
    fun get_stylist_by_code(@Header(TOKEN) token: String,
                            @Field("stylist_code") stylist_code: String): Call<StylistCodeModel>

    @FormUrlEncoded
    @POST("user/thread_list")
    fun get_stylist_get_messege(@Header(TOKEN) token: String,
                                @Field("iLimit") iLimit: String,
                                @Field("iOffset") iOffset: String): Call<Massege>

    @FormUrlEncoded
    @POST("user/chat_history")
    fun get_stylist_get_chatmessege(@Header(TOKEN) token: String,
                                    @Field("thread_id") thread_id: String,
                                    @Field("iOffset") iOffset: String): Call<Massege>

    @FormUrlEncoded
    @POST("user/chat")
    fun get_stylist_set_chat(@Header(TOKEN) token: String,
                             @Field("to_user_id") to_user_id: String,
                             @Field("message") message: String,
                             @Field("order_id") order_id: String): Call<ResponseStustas>


    @FormUrlEncoded
    @POST("user/complete_order_by_user")
    fun complete_order_by_user(@Header(TOKEN) token: String,
                               @Field("order_id") order_id: String,
                               @Field("tip") accept: String): Call<ResponseModel>

    @FormUrlEncoded
    @POST("user/rating_review")
    fun rating_review(@Header(TOKEN) token: String,
                      @Field("order_id") order_id: String,
                      @Field("ratings") ratings: String,
                      @Field("review") review: String): Call<ResponseModel>
    @FormUrlEncoded
    @POST("user/social_login")
    fun socialLogin(@Field("facebook_id") facebook_id: String,
                    @Field("type") type: String,
                    @Field("first_name") first_name: String,
                    @Field("last_name") last_name: String,
                    @Field("username") username: String,
                    @Field("email") email: String,
                    @Field("password") password: String,
                    @Field("mobile") mobile: String,
                    @Field("street") street: String,
                    @Field("city") city: String,
                    @Field("state") state: String,
                    @Field("zipcode") zipcode: String,
                    @Field("latitude") latitude: String,
                    @Field("longitude") longitude: String,
                    @Field("eDeviceType") eDeviceType: String,
                    @Field("vPushToken") vPushToken: String): Call<LoginResponseModel>


    @FormUrlEncoded
    @POST("user/cancel_order")
    fun cancel_order(@Header(TOKEN) token: String,
                               @Field("order_id") order_id: String): Call<ResponseModel>
    @FormUrlEncoded
    @POST("user/get_orders")
    fun get_orders(@Header(TOKEN) token: String,
                   @Field("limit") iLimit: String,
                   @Field("offset") iOffset: String): Call<OrderList>*/

}
