package com.example.dhwanigrocerystore.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.dhwanigrocerystore.R;
import com.example.dhwanigrocerystore.models.ViewAllModel;

public class DetailedActivity extends AppCompatActivity {
    TextView quantity;
    int totalQuantity=1;
    ImageView detailedImg;
    int totalPrice=0;
    TextView price,rating,description;
    Button addToCart;
    ImageView addItem,removeItem;
    Toolbar toolbar;
    ViewAllModel viewAllModel =null;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Object object=getIntent().getSerializableExtra("detail");
        if(object instanceof ViewAllModel){
            viewAllModel=(ViewAllModel) object;
        }
        quantity=findViewById(R.id.quantity);
        detailedImg=findViewById(R.id.detailed_img);
        addItem=findViewById(R.id.add_item);
        removeItem=findViewById(R.id.remove_item);
        price=findViewById(R.id.detailed_price);
        rating=findViewById(R.id.detailed_rating_text);
        description=findViewById(R.id.detailed_long_description);
        if(viewAllModel!=null){
            Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(detailedImg);
            rating.setText(viewAllModel.getRating());
            description.setText(viewAllModel.getDescription());
            price.setText("Price :Rs."+viewAllModel.getPrice()+"/kg");
            totalPrice=viewAllModel.getPrice()*totalQuantity;
            if(viewAllModel.getType().equals("eggs")){
                price.setText("Price :Rs."+viewAllModel.getPrice()+"/dozen");
                totalPrice=viewAllModel.getPrice()*totalQuantity;
            }
            if(viewAllModel.getType().equals("milk")){
                price.setText("Price :Rs."+viewAllModel.getPrice()+"/litre");
                totalPrice=viewAllModel.getPrice()*totalQuantity;
            }

        }
        addToCart=findViewById(R.id.add_to_cart);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalQuantity<10){
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));
                }
            }
        });
        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalQuantity>1){
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                }
            }
        });
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }
}