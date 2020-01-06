package com.blibli.blibook.backend;

public class ApiPath {

    // SOME FIXED VARIABLES
    private static final String API = "/api";

    // USER ENDPOINTS
    public static final String USER = API + "/users";
    public static final String USER_SIGNUP = USER + "/signup";
    public static final String USER_REGISTER = USER + "/register";
    public static final String USER_UPDATE = USER + "/update";
    public static final String USER_LOGIN = USER + "/login";
    public static final String USER_DELETE = USER + "/delete";

    // SHOP ENDPOINTS
    public static final String SHOP = API + "/shops";
    public static final String SHOP_REGISTER = SHOP + "/register";
    public static final String SHOP_UPDATE = SHOP + "/update";
    public static final String SHOP_BY_USER_ID = SHOP + "/user";

    // PRODUCT ENDPOINTS
    public static final String PRODUCT = API + "/products";
    public static final String PRODUCT_BY_PRODUCT_CATEGORY_ID = PRODUCT + "/category";
    public static final String PRODUCT_BY_SHOP_ID = PRODUCT + "/shop";
    public static final String PRODUCT_SEARCH_BY_NAME = PRODUCT + "/search";
    public static final String PRODUCT_SEARCH_BY_PRICE_LESS_THAN = PRODUCT + "/search/price";
    public static final String PRODUCT_SEARCH_BY_COUNTRY = PRODUCT + "/country";

    // CART ENDPOINTS
    public static final String CART = API + "/carts";
    public static final String CART_BY_USER_ID = CART + "/user" ;
    public static final String ADD_PRODUCT_TO_CART = CART + "/addProduct";

    // WISHLIST ENDPOINTS
    public static final String WISHLIST = API + "/wishlists";
    public static final String WISHLIST_BY_USER_ID = WISHLIST + "/user" ;
    public static final String WISHLIST_CART_DELETE_BY_ID = WISHLIST + "/carts/delete" ;
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

    // ADMIN ENDPOINTS
    public static final String ADMIN = API + "/admin";
    public static final String ALL_USERS =  ADMIN + "/users";
    public static final String ALL_PRODUCTS = ADMIN + "/products";
    public static final String ALL_CARTS =  ADMIN + "/carts";
    public static final String ALL_ORDER = ADMIN + "/orders";
    public static final String ALL_PAYMENT = ADMIN + "/payments";


}
