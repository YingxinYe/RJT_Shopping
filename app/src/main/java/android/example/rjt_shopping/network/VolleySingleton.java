package android.example.rjt_shopping.network;

import android.example.rjt_shopping.app.MyApplication;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {

    private static VolleySingleton mInstance=null;
    private RequestQueue requestQueue=null;


    public static VolleySingleton getInstance(){
        if(mInstance==null){
            mInstance=new VolleySingleton();
        }
        return mInstance;
    }

    private VolleySingleton(){
        requestQueue= Volley.newRequestQueue(MyApplication.getAppContext());
    }

    public <T> void addRequestQue(Request<T> request){
        requestQueue.add(request);
    }


}
