package com.example.dhwanigrocerystore.adapters;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dhwanigrocerystore.R;
import com.example.dhwanigrocerystore.models.MyCartModel;
import com.example.dhwanigrocerystore.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {
    Context context;
    List<MyCartModel> cartModelList;
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    ViewAllModel viewAllModel = null;
    public MyCartAdapter(Context context, List<MyCartModel> cartModelList) {
        this.context = context;
        this.cartModelList = cartModelList;
        firestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
    }
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_item,parent,false));
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(cartModelList.get(position).getProductName());
        holder.product_price.setText(cartModelList.get(position).getProductPrice());
        //holder.price.setText();
//        holder.price.setText(viewAllModel.getPrice());
        holder.date.setText(cartModelList.get(position).getCurrentDate());
        holder.time.setText(cartModelList.get(position).getCurrentTime());
        holder.quantity.setText(cartModelList.get(position).getTotalQuantity());
        holder.price.setText(String.valueOf(cartModelList.get(position).getTotalPrice()));
        holder.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("AddToCart")
                        .document(cartModelList.get(position).getDocumentId())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>(){
                            public void onComplete(Task<Void> task){
                                if(task.isSuccessful()){
                                    cartModelList.remove(cartModelList.get(position));
                                    notifyDataSetChanged();
                                    Toast.makeText(context,"Item Deleted",Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context,"Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                });
            }
        });

    }
    @Override
    public int getItemCount() {
        return cartModelList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,product_price,date,time,quantity,price;
        Button deleteItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            deleteItem=itemView.findViewById(R.id.delete);
            name=itemView.findViewById(R.id.product_name);
            product_price=itemView.findViewById(R.id.product_price);
            date=itemView.findViewById(R.id.current_date);
            time=itemView.findViewById(R.id.current_time);
            quantity=itemView.findViewById(R.id.total_quantity);
            price=itemView.findViewById(R.id.total_price);

        }
    }
}
