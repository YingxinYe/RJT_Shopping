package android.example.rjt_shopping.helpers;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {
    Context context;
    SharedPreferences sharedPreferences;
    public static String USER_ID;
    public static String API_KEY;
    public static final String PREFERENCE_KEY="USER";

    public Session(Context context){
        this.context=context;
        sharedPreferences = context.getSharedPreferences(PREFERENCE_KEY, context.MODE_PRIVATE);

    }

    public void getUserIdandKey(){
        USER_ID = sharedPreferences.getString("USER_ID", null);
        API_KEY = sharedPreferences.getString("APIKEY", null);
    }


}
