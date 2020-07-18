package com.foddez.service.Util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerURLs {
    public static final String ROOT_URL="https://www.foddez.com/myapp/";
    public static final String CHECK_MOBILE_URL ="https://www.foddez.com/myapp/check-user-mobile.php";
    public static final String AREA_LIST_URL ="https://www.foddez.com/myapp/address-search.php";
    public static final String AFTER_SEARCH_AREA_URL ="https://www.foddez.com/myapp/after-search-area.php";
    public static final String HOME_CATEGORY_URL ="https://www.foddez.com/myapp/home-category.php";
    public static final String SLIDER_URL ="https://www.foddez.com/myapp/home-slider.php";
    public static final String BUSINESS_LIST_URL ="https://www.foddez.com/myapp/business-list.php";
    public static final String GET_SUB_CATEGORY_LIST_UTL ="https://www.foddez.com/myapp/sub-category-list.php";
    public static final String GET_PRODUCT_LIST_UTL = "https://www.foddez.com/myapp/product-list.php";
    public static final String BUSINESS_DETAIL_URL ="https://www.foddez.com/myapp/business-detail.php";
    public static final String AFTER_SEARCH_AREA_HOME_URL ="https://www.foddez.com/myapp/after-search-area-home.php";
    public static final String SAVE_USER_ADDRESS_URL ="https://www.foddez.com/myapp/save-user-address.php";
    public static final String USER_PERSONAL_DETAIL_URL ="https://www.foddez.com/myapp/user-personal-detail.php";
    public static final String UPDATE_USER_DETAIL_URL ="https://www.foddez.com/myapp/update-user-personal-detail.php";
    public static final String SAVED_ADDRESS_URL ="https://www.foddez.com/myapp/saved-address-list.php";
    public static final String UPDATE_USER_ADDRESS_URL ="https://www.foddez.com/myapp/update-user-address.php";
    public static final String CHECK_USER_PROFILE_URL ="https://www.foddez.com/myapp/check-user-profile.php";

    public static Retrofit retrofit;
    public static Retrofit getAddresstList(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
