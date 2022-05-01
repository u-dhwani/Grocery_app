package com.example.dhwanigrocerystore.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dhwanigrocerystore.R;
import com.example.dhwanigrocerystore.activities.NavCategoryActivity;
import com.example.dhwanigrocerystore.models.NavCategoryDetailedModel;

import java.util.List;

public class NavCategoryDetailedAdapter extends RecyclerView.Adapter<NavCategoryDetailedAdapter.ViewHolder> {
    NavCategoryActivity context;
    List<NavCategoryDetailedModel> list;


    public NavCategoryDetailedAdapter(NavCategoryActivity context, List<NavCategoryDetailedModel> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_category_detailed_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       // Glide.with(context).load(list.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(list.get(position).getName());
        holder.price.setText(list.get(position).getPrice());
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        int total_Quantity=0;
        ImageView imageView;
        Button add_item_nav,remove_item_nav;
        TextView name,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           // add_item_nav=findViewById(R.id.add_item);
            //remove_item_nav=findViewById(R.id.remove_item);
            imageView=itemView.findViewById(R.id.cat_nav_img);
            name=itemView.findViewById(R.id.nav_cat_name);
            price=itemView.findViewById(R.id.price);
          /*  add_item_nav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(total_Quantity<10){
                        total_Quantity++;
                        //quantity.setText(String.valueOf(totalQuantity));
                        //totalPrice=list.getPrice()*totalQuantity;
                    }
                }
            });
            remove_item_nav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(total_Quantity>1){
                        total_Quantity--;
                        //quantity.setText(String.valueOf(totalQuantity));
                        //totalPrice=viewAllModel.getPrice()*totalQuantity;
                    }
                }
            });*/


        }
    }
}
