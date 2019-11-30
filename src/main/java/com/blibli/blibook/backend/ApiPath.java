package com.blibli.blibook.backend;

public class ApiPath {

    public static final String API = "/api";

    public static final String USER = API + "/user";
    public static final String USER_BY_USER_ID = USER + "/?userId={userId}";
    public static final String USER_SIGNUP = USER + "/?roleId={userRoleId}&statusId={userStatusId}";

    public static final String SHOP = API + "/shop";
    public static final String SHOP_BY_SHOP_ID = SHOP + "/?shop={shopId}";

    public static final String PRODUCT = API + "/product";
    public static final String PRODUCT_BY_PRODUCT_ID = PRODUCT + "/?productId={productId}";
    public static final String PRODUCT_BY_PRODUCT_CATEGORY = PRODUCT + "/?categoryId={productCategoryId}";

    public static final String USER_PRODUCT_SHOP = "/?userId={userId}" + "&productId={productId}" + "&shopId={shopId}";
    public static final String ADD_PRODUCT_TO_CART = API + "/cart" + USER_PRODUCT_SHOP;
    public static final String ADD_PRODUCT_TO_WISHLIST = API + "/wishlist" + USER_PRODUCT_SHOP;

    public static final String ORDER = API + "/order";
    public static final String ORDER_INITIATE = ORDER + "/initiate" + USER_PRODUCT_SHOP;
    public static final String ORDER_CONFIRMATION = ORDER + "/confirm" + "/?orderId={orderId}";
    public static final String PAYMENT = ORDER + "/pay" + "/?orderId={orderId}";

}
