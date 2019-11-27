package com.blibli.blibook.backend;

public class ApiPath {

    public static final String API = "/api";

    public static final String USER = API + "/user";
    public static final String USER_BY_USER_ID = USER + "/{userId}";

    public static final String SHOP = API + "/shop";
    public static final String SHOP_BY_SHOP_ID = SHOP + "/{shopId}";

    public static final String PRODUCT = API + "/product";
    public static final String PRODUCT_BY_PRODUCT_ID = PRODUCT + "/{productId}";
    public static final String PRODUCT_BY_PRODUCT_CATEGORY = PRODUCT + "/category/{productCategoryId}";

}
