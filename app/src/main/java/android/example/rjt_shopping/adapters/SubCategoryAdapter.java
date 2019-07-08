package android.example.rjt_shopping.adapters;

import android.content.Context;
import android.content.Intent;
import android.example.rjt_shopping.R;
import android.example.rjt_shopping.activities.ProductListActivity;
import android.example.rjt_shopping.activities.SubCategoryActivity;
import android.example.rjt_shopping.model.SubCategory;
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

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.MyViewHolder> {

    Context context;
    ArrayList<SubCategory> mlist;
    LayoutInflater inflater;
    String sid;

    public SubCategoryAdapter(Context context,ArrayList<SubCategory> list,String sid){
        this.context=context;
        mlist=list;
        inflater=LayoutInflater.from(context);
        this.sid=sid;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=inflater.inflate(R.layout.row_sub_category,viewGroup,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.title.setText(mlist.get(i).getScname());
        myViewHolder.description.setText(mlist.get(i).getScdiscription());
        Glide.with(context).load(mlist.get(i).getScimagerl()).into(myViewHolder.image);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public void setData(ArrayList<SubCategory> mlist) {
        this.mlist=mlist;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title;
        TextView description;
        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.subcategory_title);
            description = itemView.findViewById(R.id.subcategory_description);
            image = itemView.findViewById(R.id.subcategory_image);
            title.setOnClickListener(this);
            image.setOnClickListener(this);
            description.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent(context, ProductListActivity.class);
            i.putExtra("CAT_ID", sid);
            i.putExtra("SUB_CAT_ID", mlist.get(getAdapterPosition()).getScid());
            i.putExtra("SUB_CAT_NAME", mlist.get(getAdapterPosition()).getScname());
            context.startActivity(i);
        }
    }
}
