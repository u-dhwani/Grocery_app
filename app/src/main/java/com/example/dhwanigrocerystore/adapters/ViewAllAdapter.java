package com.example.dhwanigrocerystore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dhwanigrocerystore.R;
import com.example.dhwanigrocerystore.activities.ViewAllActivity;
import com.example.dhwanigrocerystore.models.ViewAllModel;

import java.util.List;

public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.ViewHolder> {
    Context context;
    List<ViewAllModel> list;

    public ViewAllAdapter(ViewAllActivity viewAllActivity, List<ViewAllModel> viewAllModelList) {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(list.get(position).getName());
        holder.description.setText(list.get(position).getDescription());
        holder.rating.setText(list.get(position).getRating());
        holder.price.setText(list.get(position).getPrice()+"/kg");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name,description,price,rating;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.view_all_item_img);
            name=itemView.findViewById(R.id.view_all_item_name);
            description=itemView.findViewById(R.id.view_all_item_description);
            price=itemView.findViewById(R.id.view_all_item_price);
            rating=itemView.findViewById(R.id.view_all_item_rating);
        }
    }
}
