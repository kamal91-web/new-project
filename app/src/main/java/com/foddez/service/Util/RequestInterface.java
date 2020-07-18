package com.foddez.service.Util;

import com.foddez.service.Model.AddressSearchModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RequestInterface {
    @GET("address-search.php")
    Call<List<AddressSearchModel>> getAreas(@Query("key") String keywords);

    /*@GET("user-pending-orders.php")
    Call<List<UserPendingOrdersModel>> getPendingOrders(@Query("userEmail") String useremail);

    @GET("delete-new-order.php")
    Call<List<DeletePendingOrdersModel>> deletePendingOrders(@Query("userEmail") String useremail, @Query("orderId") String orderid);

    @GET("user-complete-orders.php")
    Call<List<UserCompleteOrdersModel>> getCompleteOrders(@Query("userEmail") String useremail);
    @GET("user-cancle-orders.php")
    Call<List<UserCancleOrderModel>> getCancleOrders(@Query("userEmail") String useremail);

    @FormUrlEncoded
    @POST("send-sms.php")
    Call<CheckMobile> checkMobileExist(@Field("mobile_no") String mobile_no);*/
}
