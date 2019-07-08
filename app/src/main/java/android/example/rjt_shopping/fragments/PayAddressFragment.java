package android.example.rjt_shopping.fragments;


import android.content.Context;
import android.example.rjt_shopping.activities.PaymentProcessActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.rjt_shopping.R;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PayAddressFragment extends Fragment implements View.OnClickListener {

    EditText name,street,apt,city,state,zip,phone;
    Button btn;
    String address;
    TextView continue_shopping;
    AddressOnClickListner myOnClickListner;

    public PayAddressFragment() {
        // Required empty public constructor
    }

    private void init(View view){
        name=view.findViewById(R.id.address_ship_name);
        street=view.findViewById(R.id.address_ship_street_address);
        apt=view.findViewById(R.id.address_ship_apt);   //question
        city=view.findViewById(R.id.address_ship_city);
        state=view.findViewById(R.id.address_ship_state);
        zip=view.findViewById(R.id.address_ship_ZIP);
        phone=view.findViewById(R.id.address_ship_phone);
        btn=view.findViewById(R.id.address_button);
        continue_shopping=view.findViewById(R.id.address_cancel_and_continue_shopping);
        btn.setOnClickListener(this);
        continue_shopping.setOnClickListener(this);
    }

    public interface AddressOnClickListner{
        void setOnClickListener(String name,String address,String phone);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        myOnClickListner=(AddressOnClickListner)getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_pay_address, container, false);
        init(view);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.address_cancel_and_continue_shopping:
                getActivity().finish();
                break;
            case R.id.address_button:
                address= street.getText().toString()+" "+apt.getText().toString()+","+city.getText().toString()+","+state.getText().toString()+","+zip.getText().toString();
                myOnClickListner.setOnClickListener(name.getText().toString(),address,phone.getText().toString());
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.pay_fragment_container,new PayMoneyFragment()).addToBackStack(null).commit();
                break;
        }
    }
}
