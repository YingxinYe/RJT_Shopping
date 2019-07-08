package android.example.rjt_shopping.helpers;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {
    Context context;
    SharedPreferences sharedPreferences;
    public static String USER_ID;
    public static String API_KEY;
    public static String EMAIL;
    public static String USER_NAME;
    public static String MOBILE;
    public static final String PREFERENCE_KEY="USER";

    public Session(Context context){
        this.context=context;
        sharedPreferences = context.getSharedPreferences(PREFERENCE_KEY, context.MODE_PRIVATE);

    }

    public void getUserIdandKey(){
        USER_NAME=sharedPreferences.getString("FIRST_NAME",null)+" "+sharedPreferences.getString("LAST_NAME",null);
        USER_ID = sharedPreferences.getString("USER_ID", null);
        API_KEY = sharedPreferences.getString("APIKEY", null);
        EMAIL=sharedPreferences.getString("EMAIL",null);
        MOBILE=sharedPreferences.getString("MOBILE",null);
    }


}
