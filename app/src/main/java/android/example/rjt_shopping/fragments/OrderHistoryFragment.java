package android.example.rjt_shopping.fragments;


import android.example.rjt_shopping.adapters.OrderHistoryAdapter;
import android.example.rjt_shopping.app.Endpoint;
import android.example.rjt_shopping.helpers.Session;
import android.example.rjt_shopping.model.CategoryList;
import android.example.rjt_shopping.model.OrderConfirm;
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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderHistoryFragment extends Fragment implements OrderHistoryAdapter.onClickListener {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    OrderHistoryAdapter adapter;
    ArrayList<OrderConfirm> mlist=new ArrayList<>();

    public OrderHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_order_history, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        recyclerView=view.findViewById(R.id.order_history_recycler_view);
        layoutManager=new LinearLayoutManager(getContext());
        adapter=new OrderHistoryAdapter(getContext(),mlist);
        adapter.initOnClickListener(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        connect();

    }

    private void connect() {
        String url= Endpoint.getOrderHistory(Session.API_KEY,Session.USER_ID,Session.MOBILE);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                GsonBuilder builder=new GsonBuilder();
                Gson gson=builder.create();
                CategoryList categoryList=gson.fromJson(response.toString(),CategoryList.class);
                mlist=categoryList.getOrderhistory();
                adapter.setData(mlist);

                for(int i=0;i<mlist.size();i++){
                    Log.i("MyTagg",mlist.get(i).getOrderid());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("MyTag","Get History error "+error.getMessage());
            }
        });
        VolleySingleton.getInstance().addRequestQue(request);
    }

    @Override
    public void setOnClickListner(View v, int position) {
        Bundle bundle=new Bundle();
        bundle.putString("ORDER_ID",mlist.get(position).getOrderid());
        ShippingTrackFragment shippingTrackFragment=new ShippingTrackFragment();
        shippingTrackFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.history_fragment_container,shippingTrackFragment).addToBackStack(null).commit();
    }
}
