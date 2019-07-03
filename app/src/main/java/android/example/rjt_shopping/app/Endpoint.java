package android.example.rjt_shopping.app;

public class Endpoint {

    private static final String REGISTER_BASE="shop_reg.php?";
    private static final String LOGIN_BASE="shop_login.php?";
    private static final String GET_CATEGORY="cust_category.php?";
    private static final String GET_SUB_CATEGORY="cust_sub_category.php?";
    private static final String PRODUCT_LIST="product_details.php?";
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
}
