package android.example.rjt_shopping.fragments;


import android.content.Context;
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
public class PayMoneyFragment extends Fragment implements View.OnClickListener {

    EditText card_name,card_number,card_exp;
    EditText street,apt,city,state,zip;
    Button btn;
    String billing_address;
    TextView continue_shopping;
    BillingOnClickListner myListener;

    public PayMoneyFragment() {
        // Required empty public constructor
    }

    public interface BillingOnClickListner{
        void setBillingOnClickListener(String address);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        myListener=(BillingOnClickListner)getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_pay_money, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        card_name=view.findViewById(R.id.address_pay_name);
        card_number=view.findViewById(R.id.address_pay_card_number);
        card_exp=view.findViewById(R.id.address_pay_exp_date);

        street=view.findViewById(R.id.address_pay_billing_street_address);
        apt=view.findViewById(R.id.address_pay_billing_apt);   //question
        city=view.findViewById(R.id.address_pay_city);
        state=view.findViewById(R.id.address_pay_state);
        zip=view.findViewById(R.id.address_pay_ZIP);

        btn=view.findViewById(R.id.address_pay_button);
        continue_shopping=view.findViewById(R.id.address_pay_cancel_and_continue_shopping);
        btn.setOnClickListener(this);
        continue_shopping.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.address_pay_cancel_and_continue_shopping:
                getActivity().finish();
                break;
            case R.id.address_pay_button:
                billing_address=street.getText().toString()+" "+apt.getText().toString()+","+city.getText().toString()+","+state.getText().toString()+","+zip.getText().toString();
                myListener.setBillingOnClickListener(billing_address);
                break;
        }
    }
}
