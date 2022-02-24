package com.example.dhwanigrocerystore.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dhwanigrocerystore.R;

public class DetailedActivity extends AppCompatActivity {
    ImageView detailedImg;
    TextView price,rating,description;
    Button addToCart;
    ImageView addItem,removeItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        ImageView=findViewById(R.id.detailed_img);
        addItem=findViewById(R.id.add_item);
        removeItem=findViewById(R.id.remove_item);
        price=findViewById(R.id.detailed_price);
        rating=findViewById(R.id.detailed_img);
        ImageView=findViewById(R.id.detailed_img);
        ImageView=findViewById(R.id.detailed_img);
    }
}