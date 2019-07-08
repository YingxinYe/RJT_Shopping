package android.example.rjt_shopping.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.rjt_shopping.R;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmptyCartFragment extends Fragment {

    Button continue_shopping_btn;

    public EmptyCartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_empty_cart, container, false);
        continue_shopping_btn=view.findViewById(R.id.empty_cart_continue_shopping);
        continue_shopping_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return view;
    }

}
