package android.example.rjt_shopping.fragments;


import android.content.Intent;
import android.example.rjt_shopping.activities.PaymentProcessActivity;
import android.example.rjt_shopping.adapters.CartProductAdapter;
import android.example.rjt_shopping.database.DBHelper;
import android.example.rjt_shopping.model.Product;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.rjt_shopping.R;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment implements CartProductAdapter.setOnClickListener, View.OnClickListener {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    CartProductAdapter adapter;
    ArrayList<Product> mlist = new ArrayList<>();  //problem here
    DBHelper helper;
    TextView total_price;
    Button continue_shopping_btn, pay_now_btn;


    public CartFragment() {
        // Required empty public constructor
    }

    public void init(View view){
        recyclerView = view.findViewById(R.id.cart_recycler_view);
        total_price = view.findViewById(R.id.cart_total_price);
        continue_shopping_btn = view.findViewById(R.id.cart_continue_shopping_button);
        pay_now_btn = view.findViewById(R.id.cart_check_out_button);
        continue_shopping_btn.setOnClickListener(this);
        pay_now_btn.setOnClickListener(this);
        layoutManager = new LinearLayoutManager(getContext());
    }

    private void setTotal_price(ArrayList<Product> mlist){
        int total_amount=0;
        for(int i=0;i<mlist.size();i++){
            total_amount+=Integer.parseInt(mlist.get(i).getQuantity())*Integer.parseInt(mlist.get(i).getPrize());
        }
        total_price.setText("$ "+total_amount);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        init(view);
        helper = new DBHelper(getContext());
        mlist = helper.readAlldata();
        setTotal_price(mlist);
        adapter = new CartProductAdapter(getContext(), mlist);
        adapter.initOnClickListener(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onClickListener(View v, int position) {
        switch (v.getId()) {
            case R.id.cart_plus:
                helper.addProduct(mlist.get(position));
                reset_data();
                break;
            case R.id.cart_minus:
                helper.minusProduct(mlist.get(position));
                reset_data();
                break;
            case R.id.cart_product_delete_button:
                helper.deleteProduct(mlist.get(position));
                reset_data();
                break;
        }
    }

    private void reset_data() {
        mlist = helper.readAlldata();
        if (mlist.size() == 0) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.cart_fragment_container, new EmptyCartFragment()).commit();
        } else {
            setTotal_price(mlist);
            adapter.setData(mlist);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cart_continue_shopping_button:
                getActivity().finish();
                break;
            case R.id.cart_check_out_button:
                Intent i=new Intent(getActivity(), PaymentProcessActivity.class);
                //i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                break;
        }
    }
}
