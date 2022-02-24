package com.example.dhwanigrocerystore.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.dhwanigrocerystore.R;

public class DetailedActivity extends AppCompatActivity {
    ImageView detailedImg;
    TextView price,rating,description;
    Button addToCart;
    ImageView addItem,removeItem;
    Toolbar toolbar;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        detailedImg=findViewById(R.id.detailed_img);
        addItem=findViewById(R.id.add_item);
        removeItem=findViewById(R.id.remove_item);
        price=findViewById(R.id.detailed_price);
        rating=findViewById(R.id.detailed_rating_text);
        description=findViewById(R.id.detailed_description);
        addToCart=findViewById(R.id.add_to_cart);
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }
}