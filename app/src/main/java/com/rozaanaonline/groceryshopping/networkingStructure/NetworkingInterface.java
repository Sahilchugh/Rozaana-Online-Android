package com.rozaanaonline.groceryshopping.networkingStructure;

import androidx.annotation.Nullable;

public interface NetworkingInterface {

    <T> void networkingRequestPerformed(@Nullable MethodType type, boolean status, @Nullable T error, Object o);

    enum MethodType {
        login, signUp, sendForgotOtp, createPassword,
        allCategoriesSubCategories, staticBanners, validateForgotOtp,
        allCategories,allSubCategories,verifyPincode,addAddress,
        sliderBanners, topProducts, fetchSuperDeals,allAddress,
        allCartProducts,updateCart, fetchProductDetail
    }
}
