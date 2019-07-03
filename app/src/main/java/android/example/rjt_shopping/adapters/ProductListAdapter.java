package android.example.rjt_shopping.adapters;

import android.content.Context;
import android.example.rjt_shopping.R;
import android.example.rjt_shopping.app.Config;
import android.example.rjt_shopping.model.Product;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.MyViewHolder> {

    Context context;
    ArrayList<Product> mlist;
    LayoutInflater inflater;
    setGoToDetailPage setgo;

    public ProductListAdapter(Context context, ArrayList<Product> list){
        this.context=context;
        mlist=list;
        inflater=LayoutInflater.from(context);
//        setgo = (setGoToDetailPage)context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=inflater.inflate(R.layout.row_product_list,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.product_price.setText(mlist.get(i).getPrize());
        myViewHolder.product_name.setText(mlist.get(i).getPname());
        Glide.with(context).load(mlist.get(i).getImage()).into(myViewHolder.product_image);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public interface setGoToDetailPage{
        void goToDetailPage(View v);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView product_image;
        TextView product_name,product_price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            product_image=itemView.findViewById(R.id.product_image);
            product_name=itemView.findViewById(R.id.product_name);
            product_price=itemView.findViewById(R.id.product_price);
            product_image.setOnClickListener(this);
            product_name.setOnClickListener(this);
            product_price.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //setgo=(setGoToDetailPage)context.getApplicationContext();
            setgo.goToDetailPage(v);
        }
    }
}
