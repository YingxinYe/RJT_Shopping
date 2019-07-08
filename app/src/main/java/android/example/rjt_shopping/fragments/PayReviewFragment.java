package android.example.rjt_shopping.fragments;


import android.example.rjt_shopping.adapters.ReviewListAdapter;
import android.example.rjt_shopping.app.Endpoint;
import android.example.rjt_shopping.database.DBHelper;
import android.example.rjt_shopping.helpers.Session;
import android.example.rjt_shopping.model.CategoryList;
import android.example.rjt_shopping.model.Coupon;
import android.example.rjt_shopping.model.OrderConfirm;
import android.example.rjt_shopping.model.Product;
import android.example.rjt_shopping.network.VolleySingleton;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.rjt_shopping.R;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A simple {@link Fragment} subclass.
 */
public class PayReviewFragment extends Fragment implements View.OnClickListener {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ReviewListAdapter adapter;
    TextView name, shipping_address, billing_address, phone;
    Button btn,btn_coupon;
    TextView continue_shopping,current_price,updated_price,not_ava_coupon;
    String ship_name,ship_address,ship_phone,bill_address,total_price;
    ArrayList<Product> mlist = new ArrayList<>();
    ArrayList<OrderConfirm> olist = new ArrayList<>();
    ArrayList<Coupon> mcoupons=new ArrayList<>();
    OrderConfirm myorder;
    DBHelper helper;
    Bundle bundle;
    EditText coupon_edit;
    int amount;


    public PayReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pay_review, container, false);
        init(view);
        setData();
        return view;
    }

    private void setData() {
        bundle = getArguments();
        ship_name = bundle.getString("SHIP_NAME");
        ship_address = bundle.getString("SHIP_ADDRESS");
        ship_phone = bundle.getString("SHIP_PHONE");
        bill_address = bundle.getString("BILLING_ADDRESS");

        name.setText(ship_name);
        shipping_address.setText(ship_address);
        billing_address.setText(bill_address);
        phone.setText(ship_phone);

        amount = Integer.parseInt(mlist.get(0).getPrize()) * Integer.parseInt(mlist.get(0).getQuantity());
        total_price = String.valueOf(amount);
        current_price.setText("$ "+total_price);
    }


    private void init(View view) {

        recyclerView=view.findViewById(R.id.review_recycler_view);
        layoutManager=new LinearLayoutManager(getContext());

        name = view.findViewById(R.id.review_text_name_box);
        shipping_address = view.findViewById(R.id.review_text_shipping_address_box);
        billing_address = view.findViewById(R.id.review_text_billing_address_box);
        phone = view.findViewById(R.id.review_text_phone_box);

        btn_coupon=view.findViewById(R.id.review_apply_coupon_button);
        btn_coupon.setOnClickListener(this);
        coupon_edit=view.findViewById(R.id.review_coupon_edit_text);
        current_price=view.findViewById(R.id.review_current_total);
        updated_price=view.findViewById(R.id.review_update_total);
        not_ava_coupon=view.findViewById(R.id.review_coupon_show_not_avail);

        btn = view.findViewById(R.id.review_order_button);
        continue_shopping = view.findViewById(R.id.review_address_pay_cancel_and_continue_shopping);
        btn.setOnClickListener(this);
        continue_shopping.setOnClickListener(this);

        helper = new DBHelper(getContext());
        mlist=helper.readAlldata();
        adapter=new ReviewListAdapter(getContext(),mlist);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.review_address_pay_cancel_and_continue_shopping:
                getActivity().finish();
                break;
            case R.id.review_order_button:
                placeOrder();
                break;
            case R.id.review_apply_coupon_button:
                ApplyCoupon();
                break;
        }
    }

    private void ApplyCoupon() {
        String coupon=coupon_edit.getText().toString();
        String url = Endpoint.ApplyCoupon(Session.API_KEY,Session.USER_ID,coupon);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("MyTag", "order response is: " + response.toString());
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                CategoryList categoryList = gson.fromJson(response.toString(), CategoryList.class);
                mcoupons=categoryList.getCoupons();
                String discount=mcoupons.get(0).getDiscount();
                amount= (int) (amount*(1-(Integer.valueOf(discount)*0.01)));
                total_price = String.valueOf(amount);
                not_ava_coupon.setText("Applied");
                updated_price.setText("Now:$ "+amount);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("MyTag", "order error:" + error.getMessage());
                not_ava_coupon.setText("Coupon not accepted");
            }
        });
        VolleySingleton.getInstance().addRequestQue(request);
    }

    //problem!!!
    private void placeOrder() {
        String url = Endpoint.PlaceOrder(mlist.get(0).getId(), mlist.get(0).getPname(), mlist.get(0).getQuantity(), total_price, Session.API_KEY, Session.USER_ID, Session.USER_NAME, bill_address, ship_address, ship_phone, Session.EMAIL);
        Log.i("MyTag", "Order: " + url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("MyTag", "order response is: " + response.toString());
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                CategoryList categoryList = gson.fromJson(response.toString(), CategoryList.class);
                olist = categoryList.getOrderConfirms();
                myorder = olist.get(0);

                Bundle bundle = new Bundle();
                bundle.putSerializable("ORDER", myorder);
                PayDoneFragment payDoneFragment = new PayDoneFragment();
                payDoneFragment.setArguments(bundle);

                helper.deleteAllProduct();

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.pay_fragment_container,payDoneFragment).commit();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("MyTag", "order error:" + error.getMessage());
            }
        });
        VolleySingleton.getInstance().addRequestQue(request);
    }
}
