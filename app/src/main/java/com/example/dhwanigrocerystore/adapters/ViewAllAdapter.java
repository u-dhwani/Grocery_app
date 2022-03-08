package com.example.dhwanigrocerystore.adapters;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.Model;
import com.example.dhwanigrocerystore.R;
import com.example.dhwanigrocerystore.activities.DetailedActivity;
import com.example.dhwanigrocerystore.activities.NavCategoryActivity;
import com.example.dhwanigrocerystore.activities.ViewAllActivity;
import com.example.dhwanigrocerystore.models.ViewAllModel;

import java.util.List;
public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.ViewHolder> {
    Context context;
    List<ViewAllModel> list;
    public ViewAllAdapter(Context context, List<ViewAllModel> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_item,parent,false));
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context).load(list.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(list.get(position).getName());
        holder.description.setText(list.get(position).getDescription());
        holder.rating.setText(list.get(position).getRating());
        holder.price.setText(list.get(position).getPrice() + "/kg");
        if (list.get(position).getType().equals("eggs")) {
            holder.price.setText(list.get(position).getPrice() + "/dozen");
        }
        if (list.get(position).getType().equals("milk")) {
            holder.price.setText(list.get(position).getPrice() + "/litre");
        }

       holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailedActivity.class);
                intent.putExtra("detail", list.get(position));
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
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
