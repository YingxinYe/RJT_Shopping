package android.example.rjt_shopping.fragments;


import android.example.rjt_shopping.app.Endpoint;
import android.example.rjt_shopping.network.VolleySingleton;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.rjt_shopping.R;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    EditText edit_fname;
    EditText edit_lname;
    EditText edit_address;
    EditText edit_password;
    EditText edit_email;
    EditText edit_mobile;
    Button btn_register;

    String enter_fname;
    String enter_lname;
    String enter_address;
    String enter_password;
    String enter_email;
    String enter_mobile;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_register, container, false);

        edit_fname=view.findViewById(R.id.register_edit_first_name);
        edit_lname=view.findViewById(R.id.register_edit_last_name);
        edit_address=view.findViewById(R.id.register_edit_address);
        edit_password=view.findViewById(R.id.register_edit_password);
        edit_email=view.findViewById(R.id.register_edit_email);
        edit_mobile=view.findViewById(R.id.register_edit_mobile);

        btn_register=view.findViewById(R.id.register_button);



        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enter_fname=edit_fname.getText().toString();
                enter_lname=edit_lname.getText().toString();
                enter_address=edit_address.getText().toString();
                enter_password=edit_password.getText().toString();
                enter_email=edit_email.getText().toString();
                enter_mobile=edit_mobile.getText().toString();

                String url= Endpoint.UserRegister(enter_fname,enter_lname,enter_address,enter_password,enter_email,enter_mobile);
                StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getContext(),response,Toast.LENGTH_SHORT).show();

                        goLogin();
                        Log.i("MyTag",response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                        Log.i("MyError",error.getMessage());
                    }
                });
                VolleySingleton.getInstance().addRequestQue(request);

            }
        });


        return view;
    }

    private void goLogin(){
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new LoginFragment()).commit();
    }

}
