package android.example.rjt_shopping.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.example.rjt_shopping.R;
import android.example.rjt_shopping.activities.LoginActivity;
import android.example.rjt_shopping.activities.MainActivity;
import android.example.rjt_shopping.app.Endpoint;
import android.example.rjt_shopping.network.VolleySingleton;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    EditText edit_mobile;
    EditText edit_password;
    Button btn_login;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String first_name,last_name,email,mobile,apikey,user_id;
    TextView textView;
    JsonArrayRequest request;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);

        edit_mobile=view.findViewById(R.id.login_mobile_edit_text);
        edit_password=view.findViewById(R.id.login_password_edit_text);
        btn_login=view.findViewById(R.id.login_button);
        textView=view.findViewById(R.id.login_go_register);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new RegisterFragment(),null).commit();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences=getActivity().getSharedPreferences("USER", Context.MODE_PRIVATE);
                editor=sharedPreferences.edit();

                String enter_mobile=edit_mobile.getText().toString();
                String enter_password=edit_password.getText().toString();

                String url= Endpoint.UserLogin(enter_mobile,enter_password);
                Log.i("MyTag",url);
                request=new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Log.i("MyTag","apikey:"+response.toString());
                                JSONObject jsonObject = response.getJSONObject(0);

                                first_name=jsonObject.getString("firstname");
                                last_name=jsonObject.getString("lastname");
                                email=jsonObject.getString("email");
                                mobile=jsonObject.getString("mobile");
                                apikey=jsonObject.getString("appapikey ");
                                user_id=jsonObject.getString("id");
                                Log.i("MyTag","apikey:"+jsonObject.getString("firstname"));

                                if(apikey!=null){
                                    editor.putString("APIKEY",apikey);
                                    editor.putString("FIRST_NAME",first_name);
                                    editor.putString("LAST_NAME",last_name);
                                    editor.putString("EMAIL",email);
                                    editor.putString("MOBILE",mobile);
                                    editor.putString("USER_ID",user_id);
                                    editor.commit();

                                    Toast.makeText(getContext(),"Log in successfully",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getActivity(), MainActivity.class));
                                }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       // Log.i("MyError",error.getMessage());
                    }
                });
                VolleySingleton.getInstance().addRequestQue(request);
            }

        });

        return view;
    }

}
