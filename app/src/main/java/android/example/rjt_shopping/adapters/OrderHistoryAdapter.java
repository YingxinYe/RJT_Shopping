package android.example.rjt_shopping.adapters;

import android.content.Context;
import android.example.rjt_shopping.R;
import android.example.rjt_shopping.model.OrderConfirm;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.MyViewHolder> {

    Context context;
    ArrayList<OrderConfirm> mlist;
    LayoutInflater inflater;
    onClickListener myListener;

    public OrderHistoryAdapter(Context context,ArrayList<OrderConfirm> list){
        this.context=context;
        mlist=list;
        inflater=LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=inflater.inflate(R.layout.row_order_history_list,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.name.setText(mlist.get(i).getItemname());
        myViewHolder.date.setText(mlist.get(i).getPlacedon());
        switch (mlist.get(i).getOrderstatus()){
            case "1":
                myViewHolder.status.setText("Order Confirm");
                break;
            case "2":
                myViewHolder.status.setText("Order Dispatch");
                break;
            case "3":
                myViewHolder.status.setText("Order On the Way");
                break;
            case "4":
                myViewHolder.status.setText("Order Delivered");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public void setData(ArrayList<OrderConfirm> list) {
        mlist=list;
        notifyDataSetChanged();
    }

    public interface onClickListener{
        void setOnClickListner(View v,int position);
    }

    public void initOnClickListener(onClickListener a){
        myListener=a;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name,date,status;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.history_item);
            date=itemView.findViewById(R.id.history_date);
            status=itemView.findViewById(R.id.history_status);
            name.setOnClickListener(this);
            date.setOnClickListener(this);
            status.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myListener.setOnClickListner(v,getAdapterPosition());
        }
    }
}
