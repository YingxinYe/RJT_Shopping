package android.example.rjt_shopping.fragments;


import android.content.Intent;
import android.example.rjt_shopping.activities.MainActivity;
import android.example.rjt_shopping.helpers.Session;
import android.example.rjt_shopping.model.OrderConfirm;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.rjt_shopping.R;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PayDoneFragment extends Fragment {

    TextView name,number;
    OrderConfirm myorder;
    Button button;

    public PayDoneFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_pay_done, container, false);
        init(view);

        Bundle bundle=getArguments();
        myorder= (OrderConfirm) bundle.getSerializable("ORDER");
        name.setText("Thank you, "+ Session.USER_NAME);
        number.setText("Your Order number is: "+myorder.getOrderid());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
        return view;
    }

    private void init(View view) {
        name=view.findViewById(R.id.order_done_text_name);
        number=view.findViewById(R.id.order_done_text_order_number);
        button=view.findViewById(R.id.order_done_back_to_home);
    }



}
