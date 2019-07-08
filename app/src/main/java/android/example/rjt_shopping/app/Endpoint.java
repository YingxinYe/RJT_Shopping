package android.example.rjt_shopping.app;

public class Endpoint {

    private static final String REGISTER_BASE="shop_reg.php?";
    private static final String LOGIN_BASE="shop_login.php?";
    private static final String EDIT_PROFILE="edit_profile.php?";
    private static final String RESET_PASSWORD="shop_reset_pass.php?";
    private static final String GET_CATEGORY="cust_category.php?";
    private static final String GET_SUB_CATEGORY="cust_sub_category.php?";
    private static final String PRODUCT_LIST="product_details.php?";
    private static final String ORDER_BASE="orders.php?";
    private static final String ORDER_HISTORY="order_history.php?";
    private static final String TRACK_SHIPMENT="shipment_track.php?";
    private final static String FNAME="fname";
    private static final String LNAME="lname";
    private static final String ADDRESS="address";
    private static final String EMAIL="email";
    private static final String MOBILE="mobile";
    private static final String PASSWORD="password";
    private static final String API_KEY="api_key";
    private static final String USER_ID="user_id";
    private static final String SUB_CAT_ID="Id";
    private static final String PRODUCT_CAT_ID="cid";
    private static final String PRODUCT_SUB_CAT_ID="scid";

    private static final String ITEM_ID="item_id";
    private static final String ITEM_NAME="item_names";
    private static final String ITEM_QUANTITY="item_quantity";
    private static final String FINAL_PRICE="final_price";
    private static final String USER_NAME="user_name";
    private static final String BILLING_ADD="billingadd";
    private static final String DELIVERYADD="deliveryadd";
    private static final String NEWPASSWORD="newpassword";

    private static final String ORDER_ID="order_id";
    private static final String COUPON="coupon.php?";
    private static final String COUPONON="couponno";

    public static String UserRegister(String fname,String lname, String address,String password, String email, String mobile){
        return Config.BASE_URL+REGISTER_BASE
                +FNAME+"="+fname+"&"
                +LNAME+"="+lname+"&"
                +ADDRESS+"="+address+"&"
                +PASSWORD+"="+password+"&"
                +EMAIL+"="+email+"&"
                +MOBILE+"="+mobile;
    }

    public static String UserLogin(String mobile, String password){
        return Config.BASE_URL+LOGIN_BASE
                +MOBILE+"="+mobile+"&"
                +PASSWORD+"="+password;
    }

    public static String UpdateProfile(String fname,String lname,String address,String email,String mobile){
        return Config.BASE_URL+EDIT_PROFILE+FNAME+"="+fname+"&"
                +LNAME+"="+lname+"&"
                +ADDRESS+"="+address+"&"
                +EMAIL+"="+email+"&"
                +MOBILE+"="+mobile;
    }

    //PROBLEM
    public static String ResetPassword(String mobile,String password, String newpassword){
        return Config.BASE_URL+RESET_PASSWORD+MOBILE+"="+mobile+"&"
                +PASSWORD+"="+password+"&"
                +NEWPASSWORD+"="+newpassword;
    }

    public static String GetCategory(String api_key, String user_id){
        return Config.CATEGORY_BASE_URL+GET_CATEGORY
                +API_KEY+"="+api_key+"&"
                +USER_ID+"="+user_id;
    }

    public static String GetSubCategory(String sub_cat_id,String api_key,String user_id){
        return Config.CATEGORY_BASE_URL+GET_SUB_CATEGORY
                +SUB_CAT_ID+"="+sub_cat_id+"&"
                +API_KEY+"="+api_key+"&"
                +USER_ID+"="+user_id;

    }


    public static String GetProductList(String cid,String scid,String api_key,String user_id){
        return Config.CATEGORY_BASE_URL+PRODUCT_LIST
                +PRODUCT_CAT_ID+"="+cid+"&"
                +PRODUCT_SUB_CAT_ID+"="+scid+"&"
                +API_KEY+"="+api_key+"&"
                +USER_ID+"="+user_id;
    }


    //problem!!!!
    public static String PlaceOrder(String item_id,String item_names,String item_quantity,String final_price,String api_key,String user_id,String user_name,String billingadd,String deliveryadd,String mobile,String email){
        return  Config.BASE_URL+ORDER_BASE+ITEM_ID+"="+item_id+"&"
                +ITEM_NAME+"="+item_names+"&"
                +ITEM_QUANTITY+"="+item_quantity+"&"
                +FINAL_PRICE+"="+final_price+"&"
                +API_KEY+"="+api_key+"&"
                +USER_ID+"="+user_id+"&"
                +USER_NAME+"="+user_name+"&"
                +BILLING_ADD+"="+billingadd+"&"
                +DELIVERYADD+"="+deliveryadd+"&"
                +MOBILE+"="+mobile+"&"
                +EMAIL+"="+email;
    }

    public static String getOrderHistory(String api_key,String user_id,String mobile){
        return Config.BASE_URL+ORDER_HISTORY+API_KEY+"="+api_key+"&"
                +USER_ID+"="+user_id+"&"
                +MOBILE+"="+mobile;
    }

    public static String TrackShipment(String api_key,String user_id,String order_id){
        return Config.BASE_URL+TRACK_SHIPMENT+API_KEY+"="+api_key+"&"
                +USER_ID+"="+user_id+"&"
                +ORDER_ID+"="+order_id;
    }

    public static String ApplyCoupon(String api_key, String user_id, String couponno){
        return Config.BASE_URL+COUPON+API_KEY+"="+api_key+"&"
                +USER_ID+"="+user_id+"&"
                +COUPONON+"="+couponno;
    }

}
