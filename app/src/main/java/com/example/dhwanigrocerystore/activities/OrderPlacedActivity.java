package com.example.dhwanigrocerystore.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dhwanigrocerystore.MyCartFragment;
import com.example.dhwanigrocerystore.R;
import com.example.dhwanigrocerystore.models.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderPlacedActivity extends AppCompatActivity {
    FirebaseAuth auth;
    Button add_address_order;
    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_placed);
        auth=FirebaseAuth.getInstance();
        add_address_order=findViewById(R.id.add_address_btn_order);
        firestore=FirebaseFirestore.getInstance();
        List<MyCartModel> list=(ArrayList<MyCartModel>)getIntent().getSerializableExtra("itemlist");
        if(list!=null && list.size()>0){
            for(MyCartModel model:list){
                final HashMap<String,Object> cartMap=new HashMap<>();
                cartMap.put("productName",model.getProductName());
                cartMap.put("productPrice",model.getProductPrice());
                cartMap.put("currentDate",model.getCurrentDate());
                cartMap.put("currentTime",model.getCurrentTime());
                cartMap.put("totalQuantity",model.getTotalQuantity());
                cartMap.put("totalPrice",model.getTotalPrice());
                add_address_order.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(OrderPlacedActivity.this,AddressActivity.class));
                    }
                });
                firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("MyOrder").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(OrderPlacedActivity.this,"Your Order Has Been Placed.", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }
    }
}