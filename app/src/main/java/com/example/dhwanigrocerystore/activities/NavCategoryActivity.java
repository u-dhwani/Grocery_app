package com.example.dhwanigrocerystore.activities;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dhwanigrocerystore.R;
import com.example.dhwanigrocerystore.adapters.NavCategoryDetailedAdapter;
import com.example.dhwanigrocerystore.models.HomeCategory;
import com.example.dhwanigrocerystore.models.NavCategoryDetailedModel;
import com.example.dhwanigrocerystore.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class NavCategoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<NavCategoryDetailedModel> list;
    int total_Quantity=0;
    NavCategoryDetailedAdapter adapter;
    FirebaseFirestore db;
    ImageView add_item_nav,remove_item_nav;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_category);
        db=FirebaseFirestore.getInstance();
        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        //recyclerView.setVisibility(View.GONE);
        add_item_nav=findViewById(R.id.add_item);
        remove_item_nav=findViewById(R.id.remove_item);
        String type = getIntent().getStringExtra("type");
        recyclerView=findViewById(R.id.nav_cat_det_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();
        adapter=new NavCategoryDetailedAdapter(this,list);
        recyclerView.setAdapter(adapter);



        if (type != null && type.equalsIgnoreCase("drink")) {
            db.collection("NavCategoryDetailed").whereEqualTo("type", "drink").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        NavCategoryDetailedModel navCategoryDetailedModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        list.add(navCategoryDetailedModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
          /*  if (type != null && type.equalsIgnoreCase("vegetable")) {
                firestore.collection("AllProducts").whereEqualTo("type", "vegetable").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                            ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                            viewAllModelList.add(viewAllModel);
                            viewAllAdapter.notifyDataSetChanged();
                            progressBar.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                        }
                    }
                });

            }*/

           /* add_item_nav.setOnClickListener(new View.OnClickListener() {
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