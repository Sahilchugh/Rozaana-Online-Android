package com.rozaanaonline.groceryshopping.networkingStructure;

public class BaseUrl {

    public String BASE_URL= "BASE_URL";

    public String BASE_URL_MEDIA= "BASE_URL_MEDIA";

    private String SIGNUP = BASE_URL+"user/api/signup/";

    private String LOGIN = BASE_URL+"user/api/login/";

    private String SEND_FORGOT_OTP = BASE_URL+"user/api/validate-mobile-forgot-otp/";

    private String SUB_CATEGORIES = BASE_URL+"product/api/sub-category/";

    private String STATIC_BANNERS = BASE_URL+"advertisement/api/static-advertisement/";

    private String VALIDATE_FORGOT_OTP = BASE_URL+"user/api/validate-forgot-otp/";

    private String CHANGE_PASSWORD = BASE_URL+"user/api/forgot-password/";

    private String ALL_CATEGORIES = BASE_URL+"product/api/category/";

    private String ALL_SUB_CATEGORIES = BASE_URL+"product/api/sub-category-product/1";

    private String ADD_ADDRESS = BASE_URL+"user/api/addresses/";

    private String VERIFY_ADDRESS = BASE_URL+"product/api/verify-pincode/";

    private String SLIDER_BANNERS = BASE_URL+"advertisement/api/slider-advertisement/";

    private String TOP_PRODUCTS = BASE_URL+"product/api/top-product";

    private String SUPER_DEALS = BASE_URL+"product/api/super-deal";

    private String GET_ALL_ADDRESSES = BASE_URL+"user/api/addresses/";

    private String GET_PRODUCT_DETAIL = BASE_URL+"product/api/select-variant/";

    private String GET_CART_PRODUCTS = BASE_URL+"cart/api/cart/";

    private String UPDATE_CART_QUANTITY = BASE_URL+"cart/api/cart-item/";

    public String getBASE_URL_MEDIA() {
        return BASE_URL_MEDIA;
    }

    public String getSLIDER_BANNERS() {
        return SLIDER_BANNERS;
    }

    public String getCHANGE_PASSWORD() {
        return CHANGE_PASSWORD;
    }

    public String getVALIDATE_FORGOT_OTP() {
        return VALIDATE_FORGOT_OTP;
    }

    public String getSIGNUP() {
        return SIGNUP;
    }

    public String getLOGIN() {
        return LOGIN;
    }

    public String getSEND_FORGOT_OTP() {
        return SEND_FORGOT_OTP;
    }

    public String getSUB_CATEGORIES() {
        return SUB_CATEGORIES;
    }

    public String getSTATIC_BANNERS() {
        return STATIC_BANNERS;
    }

    public String getALL_CATEGORIES() {
        return ALL_CATEGORIES;
    }

    public String getALL_SUB_CATEGORIES() {
        return ALL_SUB_CATEGORIES;
    }

    public String getADD_ADDRESS() {
        return ADD_ADDRESS;
    }

    public String getVERIFY_ADDRESS() {
        return VERIFY_ADDRESS;
    }

    public String getTOP_PRODUCTS() {
        return TOP_PRODUCTS;
    }

    public String getSUPER_DEALS() {
        return SUPER_DEALS;
    }

    public String getGET_ALL_ADDRESSES() {
        return GET_ALL_ADDRESSES;
    }

    public String getGET_PRODUCT_DETAIL() {
        return GET_PRODUCT_DETAIL;
    }

    public String getGET_CART_PRODUCTS() {
        return GET_CART_PRODUCTS;
    }

    public String getUPDATE_CART_QUANTITY() {
        return UPDATE_CART_QUANTITY;
    }
}
