package android.example.rjt_shopping.fragments;


import android.content.Intent;
import android.example.rjt_shopping.activities.PaymentProcessActivity;
import android.example.rjt_shopping.database.DBHelper;
import android.example.rjt_shopping.model.Product;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.rjt_shopping.R;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailProductFragment extends Fragment implements View.OnClickListener {

    Product product;
    TextView detail_product_name, detail_product_price, detail_product_description;
    ImageView detail_product_image;
    Button detail_product_add_to_cart_button, detail_product_buy_now_button;
    DBHelper dbHelper;

    public DetailProductFragment() {
        // Required empty public constructor
    }

    private void init(View view) {
        detail_product_name = view.findViewById(R.id.detail_product_name);
        detail_product_price = view.findViewById(R.id.detail_product_price);
        detail_product_description = view.findViewById(R.id.detail_product_description);
        detail_product_image = view.findViewById(R.id.detail_product_image);
        detail_product_add_to_cart_button = view.findViewById(R.id.detail_product_add_to_cart_button);
        detail_product_buy_now_button = view.findViewById(R.id.detail_product_buy_now_button);
        detail_product_add_to_cart_button.setOnClickListener(this);
        detail_product_buy_now_button.setOnClickListener(this);

        dbHelper=new DBHelper(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_product, container, false);
        init(view);
        Bundle bundle = getArguments();
        product = (Product) bundle.getSerializable("DETAIL_PRODUCT");

        detail_product_name.setText(product.getPname());
        detail_product_price.setText("$ "+product.getPrize());
        detail_product_description.setText(product.getDiscription());
        Glide.with(view).load(product.getImage()).into(detail_product_image);

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.detail_product_add_to_cart_button:
                dbHelper.insertProduct(product);
                Toast.makeText(getContext(),"Product is added",Toast.LENGTH_SHORT).show();
                break;
            case R.id.detail_product_buy_now_button:
                dbHelper.insertProduct(product);
                startActivity(new Intent(getActivity(), PaymentProcessActivity.class));
                break;
        }
    }
}
