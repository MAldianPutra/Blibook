package com.blibli.blibook.backend;

public class ApiPath {

    // SOME FIXED VARIABLES
    private static final String API = "/api";

    // USER ENDPOINTS
    public static final String USER = API + "/users";
    public static final String USER_SIGNUP = USER + "/signup";
    public static final String USER_DELETE = USER + "/delete";

    // SHOP ENDPOINTS
    public static final String SHOP = API + "/shops";

    // PRODUCT ENDPOINTS
    public static final String PRODUCT = API + "/products";
    public static final String PRODUCT_BY_PRODUCT_CATEGORY_ID = PRODUCT + "/category";
    public static final String PRODUCT_BY_SHOP_ID = PRODUCT + "/shop";
    public static final String PRODUCT_SEARCH = PRODUCT + "/search";

    // CART ENDPOINTS
    public static final String CART = API + "/carts";
    public static final String CART_BY_USER_ID = CART + "/user" ;
    public static final String ADD_PRODUCT_TO_CART = CART + "/addProduct";

    // WISHLIST ENDPOINTS
    public static final String WISHLIST = API + "/wishlists";
    public static final String WISHLIST_BY_USER_ID = WISHLIST + "/user" ;
    public static final String ADD_PRODUCT_TO_WISHLIST = WISHLIST + "/addProduct";

    // ORDER AND PAYMENT ENDPOINTS
    public static final String ORDER = API + "/orders";
    public static final String ORDER_INITIATE = ORDER + "/initiate";
    public static final String ORDER_NOT_PAID_BY_USER_ID = ORDER + "/unpaid-order/user";
    public static final String ORDER_NOT_PAID_BY_SHOP_ID = ORDER + "/unpaid-order/shop";
    public static final String PAYMENT = ORDER + "/pay";
    public static final String ORDER_WAITING_CONFIRM_BY_USER_ID = ORDER + "/confirm-order/user";
    public static final String ORDER_WAITING_CONFIRM_BY_SHOP_ID = ORDER + "/confirm-order/shop";
    public static final String ORDER_CONFIRMATION = ORDER + "/confirm";

    // LIBRARY ENDPOINTS (IN ORDER CONTROLLER) LIBRARY = COMPLETED ORDER
    public static final String LIBRARY_BY_USER_ID = API + "/library/user";

    // UPLOAD ENDPOINTS
    public static final String UPLOAD = API + "/upload";
    public static final String UPLOAD_USER_PHOTO = UPLOAD + "/userPhoto";
    public static final String UPLOAD_PRODUCT_PHOTO = UPLOAD + "/productPhoto";
    public static final String UPLOAD_PDF = UPLOAD + "/productPDF";

}
