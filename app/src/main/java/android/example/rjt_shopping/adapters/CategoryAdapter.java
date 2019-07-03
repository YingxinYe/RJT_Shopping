package android.example.rjt_shopping.adapters;

import android.content.Context;
import android.content.Intent;
import android.example.rjt_shopping.R;
import android.example.rjt_shopping.activities.MainActivity;
import android.example.rjt_shopping.activities.SubCategoryActivity;
import android.example.rjt_shopping.model.Category;
import android.example.rjt_shopping.model.SubCategory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    Context context;
    ArrayList<Category> mlist;
    LayoutInflater inflater;


    public CategoryAdapter(Context context, ArrayList<Category> list) {
        this.context = context;
        mlist = list;
        inflater = LayoutInflater.from(context);

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.row_category, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.title.setText(mlist.get(i).getCname());
        myViewHolder.description.setText(mlist.get(i).getCdiscription());
        Glide.with(context).load(mlist.get(i).getCimagerl()).into(myViewHolder.image);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        TextView description;
        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.category_title);
            description = itemView.findViewById(R.id.category_description);
            image = itemView.findViewById(R.id.category_image);
            title.setOnClickListener(this);
            image.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
                Intent i = new Intent(context, SubCategoryActivity.class);
                i.putExtra("CAT_ID", mlist.get(getAdapterPosition()).getCid());
                i.putExtra("CAT_NAME", mlist.get(getAdapterPosition()).getCname());
                context.startActivity(i);
        }
    }
}

