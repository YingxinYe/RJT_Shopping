package android.example.rjt_shopping.fragments;


import android.example.rjt_shopping.app.Endpoint;
import android.example.rjt_shopping.helpers.Session;
import android.example.rjt_shopping.model.CategoryList;
import android.example.rjt_shopping.model.Shipment;
import android.example.rjt_shopping.network.VolleySingleton;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompatExtras;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.rjt_shopping.R;
import android.widget.Button;
import android.widget.TextView;

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
public class ShippingTrackFragment extends Fragment {

    TextView id,status;
    String order_id;
    Button back;
    ArrayList<Shipment> mlist=new ArrayList<>();

    public ShippingTrackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_shipping_track, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        back=view.findViewById(R.id.shipment_back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        id=view.findViewById(R.id.shipment_id);
        status=view.findViewById(R.id.shipment_status);
        Bundle bundle=getArguments();
        order_id=bundle.getString("ORDER_ID");
        connect();
    }

    private void connect() {
        String url= Endpoint.TrackShipment(Session.API_KEY,Session.USER_ID,order_id);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                GsonBuilder builder=new GsonBuilder();
                Gson gson=builder.create();
                CategoryList categoryList=gson.fromJson(response.toString(),CategoryList.class);
                mlist=categoryList.getShipment();

                setData();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("MyTag","Get Shipment error "+error.getMessage());
            }
        });
        VolleySingleton.getInstance().addRequestQue(request);
    }

    private void setData() {
        id.setText("Shipment ID: "+mlist.get(0).getShipmentid());
        switch (mlist.get(0).getShipmentstatus()){
            case "1":
                status.setText("Status: Order Confirm");
                break;
            case "2":
                status.setText("Status: Order Dispatch");
                break;
            case "3":
                status.setText("Status: Order On the Way");
                break;
            case "4":
                status.setText("Status: Order Delivered");
                break;
        }

    }

}
