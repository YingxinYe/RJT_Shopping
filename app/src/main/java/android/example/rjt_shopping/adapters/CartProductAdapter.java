package android.example.rjt_shopping.adapters;

import android.content.Context;
import android.example.rjt_shopping.R;
import android.example.rjt_shopping.database.DBHelper;
import android.example.rjt_shopping.model.Product;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CartProductAdapter extends RecyclerView.Adapter<CartProductAdapter.MyViewHolder> {

    ArrayList<Product> mlist;
    Context context;
    LayoutInflater layoutInflater;
    setOnClickListener MyOnClickListener;

    public CartProductAdapter(Context context,ArrayList<Product> list){
        this.context=context;
        mlist=list;
        layoutInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=layoutInflater.inflate(R.layout.row_cart_list,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Glide.with(context).load(mlist.get(i).getImage()).into(myViewHolder.product_image);
        myViewHolder.procut_name.setText(mlist.get(i).getPname());
        myViewHolder.product_price.setText("$ "+mlist.get(i).getPrize());
        myViewHolder.product_quantity.setText(mlist.get(i).getQuantity());
        myViewHolder.single_item_total_price.setText("Total: $ "+String.valueOf(Integer.parseInt(mlist.get(i).getQuantity())*Integer.parseInt(mlist.get(i).getPrize())));

    }

    public void setData(ArrayList<Product> mlist){
        this.mlist=mlist;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public interface setOnClickListener{
        void onClickListener(View v, int position);
    }

    public void initOnClickListener(setOnClickListener a){
        MyOnClickListener=a;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView product_image,add,minus;
        TextView procut_name,product_price,product_inshock,product_quantity,single_item_total_price;
        Spinner quantity_spinner;
        Button btn_delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            product_image=itemView.findViewById(R.id.cart_product_image);
            procut_name=itemView.findViewById(R.id.cart_product_name);
            product_price=itemView.findViewById(R.id.cart_product_price);
            product_inshock=itemView.findViewById(R.id.cart_product_is_in_shock);
            product_quantity=itemView.findViewById(R.id.cart_product_quantity);
            quantity_spinner=itemView.findViewById(R.id.cart_product_quantity_spinner);
            btn_delete=itemView.findViewById(R.id.cart_product_delete_button);
            single_item_total_price=itemView.findViewById(R.id.cart_single_item_total_price);
            minus=itemView.findViewById(R.id.cart_minus);
            add=itemView.findViewById(R.id.cart_plus);
            add.setOnClickListener(this);
            minus.setOnClickListener(this);
            btn_delete.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            MyOnClickListener.onClickListener(v,getAdapterPosition());
        }
    }
}
