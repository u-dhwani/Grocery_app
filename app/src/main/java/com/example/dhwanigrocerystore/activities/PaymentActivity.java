package com.example.dhwanigrocerystore.activities;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dhwanigrocerystore.R;
import com.example.dhwanigrocerystore.adapters.MyCartAdapter;
import com.example.dhwanigrocerystore.models.MyCartModel;

import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PaymentActivity extends AppCompatActivity {
    Toolbar toolbar;
    MyCartModel myCartModel;
    MyCartAdapter cartAdapter;
    List<MyCartModel> cartModelList;
    TextView subTotal,discount,shipping,total;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        cartModelList=new ArrayList<>();
//        cartAdapter=new MyCartAdapter(getActivity(),cartModelList);

        toolbar=findViewById(R.id.payment_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // double totalAmount=0.0;
       // bill=getIntent().getDoubleExtra("bill",0.0);
        subTotal=findViewById(R.id.sub_total);
        discount=findViewById(R.id.textView17);
        shipping=findViewById(R.id.textView18);
        total=findViewById(R.id.total_amt);
        double totalAmount = 0.0;



        subTotal.setText(totalAmount+"Rs."+ myCartModel.getOverTotalAmount());
    }
}
