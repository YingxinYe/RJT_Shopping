package android.example.rjt_shopping.adapters;

import android.content.Context;
import android.example.rjt_shopping.R;
import android.example.rjt_shopping.model.Product;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.MyViewHolder> {
    Context context;
    ArrayList<Product> mlist;
    LayoutInflater inflater;

    public ReviewListAdapter (Context context,ArrayList<Product> list){
       mlist=list;
       this.context=context;
       inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=inflater.inflate(R.layout.row_review_order_list,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Glide.with(context).load(mlist.get(i).getImage()).into(myViewHolder.image);
        myViewHolder.name.setText(mlist.get(i).getPname());
        myViewHolder.price.setText("$ "+mlist.get(i).getPrize());
        myViewHolder.quantity.setText("Quantity: "+mlist.get(i).getQuantity()+" pc");
        myViewHolder.total.setText("Total: $ "+Integer.parseInt(mlist.get(i).getQuantity())*Integer.parseInt(mlist.get(i).getPrize()));
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name,price,quantity,total;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            image=itemView.findViewById(R.id.review_product_image);
            name=itemView.findViewById(R.id.review_product_name);
            price=itemView.findViewById(R.id.review_product_price);
            quantity=itemView.findViewById(R.id.review_product_quantity);
            total=itemView.findViewById(R.id.review_single_item_total_price);
        }
    }
}
