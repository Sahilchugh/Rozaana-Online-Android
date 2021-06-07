package com.rozaanaonline.groceryshopping.networkingStructure;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.rozaanaonline.groceryshopping.pojo.AddressPojoParser;
import com.rozaanaonline.groceryshopping.pojo.AllCategoriesPojo;
import com.rozaanaonline.groceryshopping.pojo.Category_SubCat_SubSubCat_PojoParser;
import com.rozaanaonline.groceryshopping.pojo.DealsDataParser;
import com.rozaanaonline.groceryshopping.pojo.GetCartPojoParser;
import com.rozaanaonline.groceryshopping.pojo.LoginPojo;
import com.rozaanaonline.groceryshopping.pojo.ProductDetailDataParser;
import com.rozaanaonline.groceryshopping.pojo.SignupPojo;
import com.rozaanaonline.groceryshopping.pojo.SliderPojo;
import com.rozaanaonline.groceryshopping.pojo.StaticAdvertisementPojoParser;
import com.rozaanaonline.groceryshopping.pojo.SubCategoryDataByProductsParser;
import com.rozaanaonline.groceryshopping.pojo.TopProductParser;
import com.rozaanaonline.groceryshopping.pojo.VerifyPincodePojo;
import com.rozaanaonline.groceryshopping.sharePrefs.SharePrefs;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class NetworkingCalls {

    private final SharePrefs sharePrefs;
    private final Context context;
    private final Activity activity;
    RequestQueue requestQueue;
    BaseUrl baseUrl = new BaseUrl();
    GsonBuilder gsonBuilder;
    Gson gson;
    NetworkingInterface networkingInterface;

    private final String SUCCESS  = "SUCCESS";

    public NetworkingCalls(Context context, Activity activity) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(this.context);
        this.activity = activity;
        sharePrefs = new SharePrefs(context);
    }

    public NetworkingCalls(Context context, Activity activity,
                           NetworkingInterface networkingInterface) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(this.context);
        this.activity = activity;
        this.networkingInterface = networkingInterface;
        sharePrefs = new SharePrefs(context);
    }

    private void addToQueue(JsonObjectRequest request) {
        request.setRetryPolicy(new DefaultRetryPolicy(
                (int) TimeUnit.SECONDS.toMillis(30),
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }

    public void signup(String f_name,String l_name,String email,String password,String phone){

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("first_name", f_name);
            jsonBody.put("last_name", l_name);
            jsonBody.put("email", email);
            jsonBody.put("mobile_number",phone);
            jsonBody.put("password",password);

            Log.e("jsonBodySignup", ""+jsonBody.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setMessage("Please Wait..");
        dialog.show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                baseUrl.getSIGNUP(), jsonBody, response -> {
            dialog.dismiss();

            Log.e("onResSignup",response.toString());
            gsonBuilder = new GsonBuilder();
            gson = gsonBuilder.create();
            Type type = new TypeToken<SignupPojo>(){}.getType();
            SignupPojo signupPojo = gson.fromJson(String.valueOf(response),type);

            if (signupPojo.getStatus().equalsIgnoreCase(SUCCESS)){

                String firstName = signupPojo.getFirstname();
                String lastname = signupPojo.getLastname();
                String email1 = signupPojo.getEmail();
                String mobile_number = signupPojo.getMobileNumber();
                String token = signupPojo.getToken();

                sharePrefs.setFirstName(firstName);
                sharePrefs.setLastName(lastname);
                sharePrefs.setEmail(email1);
                sharePrefs.setMobileNum(mobile_number);
                sharePrefs.setToken(token);

                Log.e("valSignup", ""+firstName+" "+lastname+" "+ email1 +" "+mobile_number
                        +" "+token);

                networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.signUp,
                        true,null, signupPojo);
            }

        }, error -> {
            Log.e("onErrorSignUp",error.toString());
            dialog.dismiss();
            networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.signUp,
                    false,"Something went wrong!",null);
        });
        addToQueue(jsonObjectRequest);
    }

    public void login(String input, String password) {
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("username", input);
            jsonBody.put("password", password);

            Log.e("jsonBodyLogin", ""+jsonBody.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setMessage("Please Wait..");
        dialog.show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                baseUrl.getLOGIN(), jsonBody, response -> {
            dialog.dismiss();
            Log.e("onResLogin", response.toString());
            try {

                if (response.getString("status").equalsIgnoreCase(SUCCESS)){
                    gsonBuilder = new GsonBuilder();
                    gson = gsonBuilder.create();
                    Type type = new TypeToken<LoginPojo>() {}.getType();
                    LoginPojo loginPojo = gson.fromJson(response.getString("data"), type);

                    sharePrefs.setEmail(loginPojo.getEmail());
                    sharePrefs.setToken(loginPojo.getToken());
                    sharePrefs.setFirstName(loginPojo.getFirstName());
                    sharePrefs.setLastName(loginPojo.getLastName());
                    sharePrefs.setMobileNum(loginPojo.getMobile());
                    sharePrefs.setLoggedIn(true);

                    networkingInterface.networkingRequestPerformed(
                            NetworkingInterface.MethodType.login,
                            true, null, null);
                }
                else {
                    Toast.makeText(context, response.getString("message"),
                            Toast.LENGTH_SHORT).show();
                    networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.login,
                            false, null, null);
                }
            }catch (Exception e){
                networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.login,
                        false, e, null);
            }

        }, error -> {
            Log.e("onErrorSignUp",error.toString());
            dialog.dismiss();
            networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.login,
                    false,"Something went wrong!",null);
        });
        addToQueue(jsonObjectRequest);
    }

    public void sendForgotOtp(String number) {
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("mobile", number);

            Log.e("forgot", ""+jsonBody.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setMessage("Please Wait..");
        dialog.show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                baseUrl.getSEND_FORGOT_OTP(), jsonBody, response -> {
            dialog.dismiss();
            Log.e("onResReset", response.toString());

            try {
                if (response.getString("status").equalsIgnoreCase(SUCCESS))
                    networkingInterface.networkingRequestPerformed
                            (NetworkingInterface.MethodType.sendForgotOtp,
                            true, null, null);
                else
                    networkingInterface.networkingRequestPerformed(
                            NetworkingInterface.MethodType.sendForgotOtp,
                            false, null, null);
            } catch (JSONException e) {
                networkingInterface.networkingRequestPerformed(
                        NetworkingInterface.MethodType.sendForgotOtp,
                        false, e, null);
                e.printStackTrace();
            }
        }, error -> {
            Log.e("onErrorSignUp",error.toString());
            dialog.dismiss();
            networkingInterface.networkingRequestPerformed(
                    NetworkingInterface.MethodType.sendForgotOtp,
                    false,error,null);
        });
        addToQueue(jsonObjectRequest);

    }

    public void validateOtp(String mobile, String otp) {
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("mobile", mobile);
            jsonBody.put("otp", otp);

            Log.e("Validate_Otp", ""+jsonBody.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setMessage("Please Wait..");
        dialog.show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST,baseUrl.getVALIDATE_FORGOT_OTP(),
                jsonBody, response -> {
            dialog.dismiss();
            Log.e("onResReset", response.toString());

            try {
                if (response.getString("status").equalsIgnoreCase(SUCCESS))
                    networkingInterface.networkingRequestPerformed
                            (NetworkingInterface.MethodType.validateForgotOtp,
                            true, null, null);

                else
                    networkingInterface.networkingRequestPerformed
                            (NetworkingInterface.MethodType.validateForgotOtp,
                            false, null, null);

            } catch (JSONException e) {

                networkingInterface.networkingRequestPerformed
                        (NetworkingInterface.MethodType.validateForgotOtp,
                        false, e, null);
                e.printStackTrace();
            }
        }, error -> {
            Log.e("onErrorSignUp",error.toString());
            dialog.dismiss();
            networkingInterface.networkingRequestPerformed
                    (NetworkingInterface.MethodType.validateForgotOtp,
                    false,error,null);
        });
        addToQueue(jsonObjectRequest);

    }

    public void exploreAllCategories(){

        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setMessage("Please Wait..");
        dialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, baseUrl.getSUB_CATEGORIES()
                , response -> {

            Log.e("onResponse", ""+response);
            dialog.dismiss();

            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            Type listType = new TypeToken<List<Category_SubCat_SubSubCat_PojoParser>>(){}.getType();
            List<Category_SubCat_SubSubCat_PojoParser> categorySubCatSubSubCatPojoParserList =
                    gson.fromJson(String.valueOf(response),listType);

            networkingInterface.networkingRequestPerformed
                    (NetworkingInterface.MethodType.allCategoriesSubCategories,true,false,
                            categorySubCatSubSubCatPojoParserList);

        }, error -> {
            Log.e("onErrorResCategories", "");
            networkingInterface.networkingRequestPerformed
                    (NetworkingInterface.MethodType.allCategoriesSubCategories,
                    false,error,null);
        });
        requestQueue.add(stringRequest);

    }

    public void getStaticBanners(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, baseUrl.getSTATIC_BANNERS(),
                response -> {
            Log.e("onResStaticBanners", ""+response);
            gsonBuilder = new GsonBuilder();
            gson = gsonBuilder.create();
            Type type = new TypeToken<List<StaticAdvertisementPojoParser>>() {}.getType();
            List<StaticAdvertisementPojoParser> staticAdvertisementPojoList =
                    gson.fromJson(String.valueOf(response), type);

            Log.e("GalleryFragBannersList", ""+staticAdvertisementPojoList.size());
            networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.staticBanners,
                    true,false,staticAdvertisementPojoList);

        }, error -> {
            Log.e("onErrorResCategories", "");
            networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.staticBanners,
                    false,error,null);
        });
        requestQueue.add(stringRequest);
    }

    public void createNewPassword(String mobile, String otp, String password) {
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("mobile", mobile);
            jsonBody.put("otp", otp);
            jsonBody.put("password", password);

            Log.e("createPassword", ""+jsonBody.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setMessage("Please Wait..");
        dialog.show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST,baseUrl.getCHANGE_PASSWORD(),
                        jsonBody, response -> {

            dialog.dismiss();
            Log.e("onResReset", response.toString());

            try {
                if (response.getString("status").equalsIgnoreCase(SUCCESS))
                    networkingInterface.networkingRequestPerformed
                            (NetworkingInterface.MethodType.createPassword, true,
                                    null, null);
                else
                    networkingInterface.networkingRequestPerformed
                            (NetworkingInterface.MethodType.createPassword, false,
                                    null, null);
            } catch (JSONException e) {
                networkingInterface.networkingRequestPerformed
                        (NetworkingInterface.MethodType.createPassword, false, e, null);
                e.printStackTrace();
            }
        }, error -> {
            Log.e("onErrorSignUp",error.toString());
            dialog.dismiss();
            networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.createPassword,
                    false,error,null);
        });
        addToQueue(jsonObjectRequest);
    }

    public void getAllCategories(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, baseUrl.getALL_CATEGORIES(),
                response -> {
            Log.e("onResAllCategories", ""+response);

            gsonBuilder = new GsonBuilder();
            gson = gsonBuilder.create();
            Type type = new TypeToken<List<AllCategoriesPojo>>() {}.getType();
            List<AllCategoriesPojo> allCategoriesPojo = gson.fromJson(String.valueOf(response), type);

            networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.allCategories,
                    true,false,allCategoriesPojo);

        }, error -> {
            Log.e("onErrorResAllCategories", "");
            networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.allCategories,
                    false,error,null);
        });
        requestQueue.add(stringRequest);
    }

    public void getProductsAccordingToSubCategory(){

        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setMessage("Please Wait..");
        dialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, baseUrl.getALL_SUB_CATEGORIES(),
                response -> {

            Log.e("getProdAccToSubCategory", ""+response);
            dialog.dismiss();

            try {
                JSONObject jsonObject = new JSONObject(response);
                if (jsonObject.getString("status").equals(SUCCESS)){
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    Gson gson = gsonBuilder.create();

                    Type listType = new TypeToken<List<SubCategoryDataByProductsParser>>(){}.getType();
                    List<SubCategoryDataByProductsParser> subCategoryDataParserList =
                            gson.fromJson(String.valueOf(jsonObject.getJSONArray("data")), listType);
                    networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.allSubCategories,
                            true,false,subCategoryDataParserList);
                }
                else {
                    Toast.makeText(context, ""+jsonObject.getString("message"),
                            Toast.LENGTH_SHORT).show();
                    networkingInterface.networkingRequestPerformed
                            (NetworkingInterface.MethodType.allSubCategories,
                                    false,jsonObject.getString("message"),null);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            Log.e("onErrResAllSubCat", ""+error);
            networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.allSubCategories,
                    false,error,null);
        });
        requestQueue.add(stringRequest);
    }

    public void verifyPincode(String pincode){
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("pincode", pincode);

            Log.e("jsonBodyVerifyPin", ""+jsonBody.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setMessage("Please Wait..");
        dialog.show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                baseUrl.getVERIFY_ADDRESS(),jsonBody, response -> {

            Log.e("onResVerifyPincode", ""+response);
            dialog.dismiss();

            gsonBuilder = new GsonBuilder();
            gson = gsonBuilder.create();
            Type type = new TypeToken<VerifyPincodePojo>(){}.getType();
            VerifyPincodePojo verifyPincodePojo = gson.fromJson(String.valueOf(response),type);

            if (verifyPincodePojo.getStatus().equals(SUCCESS)){
                Toast.makeText(context, ""+verifyPincodePojo.getData(),Toast.LENGTH_SHORT).show();
                networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.verifyPincode,
                        true,false,verifyPincodePojo);
            }
            else {
                Toast.makeText(context, ""+verifyPincodePojo.getData(), Toast.LENGTH_SHORT).show();
                networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.verifyPincode,
                        false,verifyPincodePojo.getData(),verifyPincodePojo);
            }
        }, error -> {
            Log.e("onErrVerifyPincode", ""+error);
            networkingInterface.networkingRequestPerformed
                    (NetworkingInterface.MethodType.verifyPincode,false,error,null);
        });
        addToQueue(jsonObjectRequest);
    }

    public void addAddress(String firName,String lasName,String phoneNumber,String state,String city,
                           String pinCode,String address,String landmark,String defaultAddress){

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("first_name", firName);
            jsonBody.put("last_name", lasName);
            jsonBody.put("telephone", phoneNumber);
            jsonBody.put("postcode", pinCode);
            jsonBody.put("default", defaultAddress);
            jsonBody.put("state", state);
            jsonBody.put("apartment", address);
            jsonBody.put("landmark", landmark);
            jsonBody.put("city", city);

            Log.e("jsonBodyAddAddress", ""+jsonBody.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setMessage("Please Wait..");
        dialog.show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                baseUrl.getADD_ADDRESS(),jsonBody, response -> {

            Log.e("onResAddAddress", ""+response);
            dialog.dismiss();

            try {
                if (response.getString("status").equalsIgnoreCase(SUCCESS)){
                    networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.addAddress,
                            true,false,null);
                    Toast.makeText(context, "Address added!", Toast.LENGTH_SHORT).show();
                }
                else if (response.getString("status").equalsIgnoreCase("ERROR")){
                    Toast.makeText(context, ""+response.getString("message"),
                            Toast.LENGTH_SHORT).show();
                    networkingInterface.networkingRequestPerformed
                            (NetworkingInterface.MethodType.addAddress,false,true,null);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.addAddress,
                        false,e,null);
            }
        }, error -> {
            Log.e("onErrAddAddress", ""+error);
            networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.addAddress,
                    false,error,null);
        }){
            @Override
            public Map<String, String> getHeaders() {
                Map<String , String> header = new HashMap<>();
                Log.e("auth" , new SharePrefs(context).getToken());
                header.put("Authorization" , "Token " + new SharePrefs(context).getToken());
                return header;
            }
        };
        addToQueue(jsonObjectRequest);
    }

    public void getSliderBanners() {
        StringRequest request = new StringRequest(Request.Method.GET, baseUrl.getSLIDER_BANNERS(), response -> {
            Log.e("onResSliderBanners", "" + response);
            try {
                JSONObject jsonObject = new JSONObject(response);
                if (jsonObject.getString("status").equals("SUCCESS")) {
                    gsonBuilder = new GsonBuilder();
                    gson = gsonBuilder.create();
                    Type type = new TypeToken<SliderPojo>() {}.getType();
                    Type listType = new TypeToken<List<SliderPojo>>(){}.getType();

                    List<SliderPojo> sliderPojoList =
                            gson.fromJson(String.valueOf(jsonObject.getJSONArray("data")), listType);

                    networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.sliderBanners,
                            true, null, sliderPojoList);
                } else
                    networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.sliderBanners,
                            false, null, null);

            } catch (Exception e) {
                networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.sliderBanners,
                        false, e, null);

            }
        }, error -> {
            Log.e("onErrorResCategories", "");
            networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.sliderBanners,
                    false, error, null);
        });
        requestQueue.add(request);
    }

    public void getTopProducts(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, baseUrl.getTOP_PRODUCTS(), response -> {
            Log.e("onResGetTopProducts", ""+response);

            try {
                JSONObject jsonObject = new JSONObject(response);
                if (jsonObject.getString("status").equalsIgnoreCase(SUCCESS)){

                    gsonBuilder = new GsonBuilder();
                    gson = gsonBuilder.create();
                    Type listType = new TypeToken<List<TopProductParser>>(){}.getType();
                    List<TopProductParser> topProductParserList = gson.fromJson(jsonObject.getString("data"),listType);

                    networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.topProducts,true,false,topProductParserList);
                }
                else {
                    networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.topProducts,false,null,null);

                }
            } catch (JSONException e) {
                e.printStackTrace();
                networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.topProducts,false,e,null);
            }
            },
            error -> {
            networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.topProducts,false,error,null);
        });
        requestQueue.add(stringRequest);
    }

    public void getSuperDetails() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, baseUrl.getSUPER_DEALS(),
                response -> {
            Log.e("onResGetSuerDeals", ""+response);

            try {
                JSONObject jsonObject = new JSONObject(response);
                if (jsonObject.getString("status").equalsIgnoreCase(SUCCESS)){

                    gsonBuilder = new GsonBuilder();
                    gson = gsonBuilder.create();
                    Type listType = new TypeToken<List<DealsDataParser>>(){}.getType();
                    List<DealsDataParser> dealsDataParserList = gson.fromJson(jsonObject.getString("data"),
                            listType);

                    networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.fetchSuperDeals,
                            true,null,dealsDataParserList);
                }
                else
                    networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.fetchSuperDeals,
                            false,null,null);
            } catch (JSONException e) {
                e.printStackTrace();
                networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.fetchSuperDeals,
                        false, e,null);
            }
        },
                error -> {
                    networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.fetchSuperDeals,
                            false,error,null);
                });
        requestQueue.add(stringRequest);
    }

    public void getAddress(){

        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setMessage("Please Wait..");
        dialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, baseUrl.getGET_ALL_ADDRESSES(),
                response -> {
                    Log.e("onResGetAddress", ""+response);
                    dialog.dismiss();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.getString("status").equalsIgnoreCase(SUCCESS)){

                            gsonBuilder = new GsonBuilder();
                            gson = gsonBuilder.create();
                            Type listType = new TypeToken<List<AddressPojoParser>>(){}.getType();
                            List<AddressPojoParser> addressPojoParserList = gson.fromJson(jsonObject.getString("data"),
                                    listType);

                            networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.allAddress,
                                    true,null,addressPojoParserList);
                        }
                        else
                            networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.allAddress,
                                    false,false,null);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.allAddress,
                                false,e,null);
                    }

                }, error -> {
                        networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.allAddress,
                    false,error,null);
                }){
            @Override
            public Map<String, String> getHeaders() {
                Map<String , String> header = new HashMap<>();
                Log.e("auth" , new SharePrefs(context).getToken());
                header.put("Authorization" , "Token " + new SharePrefs(context).getToken());
                return header;
            }
        };
        requestQueue.add(stringRequest);

    }

    public void getProductDetails(int id) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, baseUrl.getGET_PRODUCT_DETAIL()+ id,
                response -> {
                    Log.e("onResGetProductDetail", ""+response);

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.getString("status").equalsIgnoreCase(SUCCESS)){

                            gsonBuilder = new GsonBuilder();
                            gson = gsonBuilder.create();
                            Type type = new TypeToken<ProductDetailDataParser>(){}.getType();
                            ProductDetailDataParser productDetailDataParser = gson.fromJson(jsonObject.getString("data"),
                                    type);

                            networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.fetchProductDetail,
                                    true,null, productDetailDataParser);
                        }
                        else
                            networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.fetchProductDetail,
                                    false,null,null);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.fetchProductDetail,
                                false, e,null);
                    }
                },
                error -> {
                    networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.fetchProductDetail,
                            false,error,null);
                });
        requestQueue.add(stringRequest);
    }

    public void getCartProducts(){

        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setMessage("Please Wait..");
        dialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, baseUrl.getGET_CART_PRODUCTS(), response -> {

            Log.e("onResGetCartProducts",response);
            dialog.dismiss();

            try {
                JSONObject jsonObject = new JSONObject(response);
                if (jsonObject.getString("status").equalsIgnoreCase(SUCCESS)){

                    gsonBuilder = new GsonBuilder();
                    gson = gsonBuilder.create();
                    Type listType = new TypeToken<List<GetCartPojoParser>>(){}.getType();
                    List<GetCartPojoParser> getCartPojoParserList = gson.fromJson(jsonObject.getString("data"), listType);

                    networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.allCartProducts,true,null,getCartPojoParserList);
                }
                else
                    networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.allCartProducts,false,null,null);

            } catch (JSONException e) {
                e.printStackTrace();
                networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.allCartProducts,false,e,null);
            }

        }, error -> {
            Log.e("onErrGetCart", ""+error );
            networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.allCartProducts,false,error,null);
        }){
            @Override
            public Map<String, String> getHeaders() {
                Map<String , String> header = new HashMap<>();
                Log.e("auth" , new SharePrefs(context).getToken());
                header.put("Authorization" , "Token " + new SharePrefs(context).getToken());
                return header;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void updateCartQuantity(int cartItemId,int productId,int quantity){

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("quantity", quantity);
            jsonBody.put("product", productId);

            Log.e("jsonBodyUpdateCart", ""+jsonBody.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setMessage("Please Wait..");
        dialog.show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, baseUrl.getUPDATE_CART_QUANTITY()+cartItemId+"/", jsonBody, response -> {
            dialog.dismiss();
            Log.e("onResUpdateCart", response.toString());
            try {

                if (response.getString("status").equalsIgnoreCase(SUCCESS)){

                    networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.updateCart, true, null, null);
                }
                else {
                    networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.updateCart, false, null, null);
                }
            }catch (Exception e){
                networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.updateCart, false, e, null);
            }

        }, error -> {
            Log.e("onErrUpdateCart",error.toString());
            dialog.dismiss();
            networkingInterface.networkingRequestPerformed(NetworkingInterface.MethodType.updateCart, false,error,null);
        }){
            @Override
            public Map<String, String> getHeaders() {
                Map<String , String> header = new HashMap<>();
                Log.e("auth" , new SharePrefs(context).getToken());
                header.put("Authorization" , "Token " + new SharePrefs(context).getToken());
                header.put("Content-Type","application/json");
                return header;
            }
        };
        addToQueue(jsonObjectRequest);
    }
}

