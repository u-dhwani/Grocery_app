package com.example.dhwanigrocerystore;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dhwanigrocerystore.activities.AddAddressActivity;
import com.example.dhwanigrocerystore.activities.OrderPlacedActivity;
import com.example.dhwanigrocerystore.adapters.MyCartAdapter;
import com.example.dhwanigrocerystore.models.AddressModel;
import com.example.dhwanigrocerystore.models.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyCartFragment extends Fragment {
    FirebaseFirestore db;
    TextView overTotalAmount;
    FirebaseAuth auth;
    RecyclerView recyclerView;
    MyCartAdapter cartAdapter;
    List<MyCartModel> cartModelList;
    ProgressBar progressBar;
    Button buynow;
    FirebaseFirestore firestore;

    public MyCartFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_my_cart, container, false);
        db=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        progressBar=root.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        buynow=root.findViewById(R.id.buy_now);
        recyclerView=root.findViewById(R.id.recyclerview);
        recyclerView.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        overTotalAmount=root.findViewById(R.id.textView5);

        cartModelList=new ArrayList<>();
        cartAdapter=new MyCartAdapter(getActivity(),cartModelList);
        recyclerView.setAdapter(cartAdapter);
        db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        String documentId=documentSnapshot.getId();
                        MyCartModel cartModel=documentSnapshot.toObject(MyCartModel.class);
                        cartModel.setDocumentId(documentId);
                        cartModelList.add(cartModel);
                        cartAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                    calculateTotalAmount(cartModelList);
                }
            }
        });
        buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(getContext(), AddAddressActivity.class));
                Intent intent=new Intent(getContext(), OrderPlacedActivity.class);
                intent.putExtra("itemlist",(Serializable) cartModelList);
               startActivity(intent);
            }
        });
        return root;
    }
    private void calculateTotalAmount(List<MyCartModel> cartModelList) {
        double totalAmount = 0.0;
        for (MyCartModel myCartModel : cartModelList) {
            totalAmount += myCartModel.getTotalPrice();
        }
        overTotalAmount.setText("Total Amount:" + totalAmount);

    }
}