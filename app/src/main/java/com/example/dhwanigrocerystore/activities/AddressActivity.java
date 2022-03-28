
package com.example.dhwanigrocerystore.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//import android.widget.Toolbar;

import com.example.dhwanigrocerystore.R;
import com.example.dhwanigrocerystore.adapters.AddressAdapter;
import com.example.dhwanigrocerystore.models.AddressModel;
import com.example.dhwanigrocerystore.models.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.razorpay.Checkout;

import java.util.ArrayList;
import java.util.List;
import androidx.appcompat.widget.Toolbar;
public class AddressActivity extends AppCompatActivity implements  AddressAdapter.SelectedAddress{
    Button addAddres;
    RecyclerView recyclerView;
    private List<AddressModel> addressModelList;
    private AddressAdapter addressAdapter;
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    Button paymentBtn;
    Toolbar toolbar;
    String mAddress="";
    MyCartModel myCartModel=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        addAddres=findViewById(R.id.add_address_btn);
        toolbar=findViewById(R.id.address_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //get data from detailed activity
        List<MyCartModel> list=(ArrayList<MyCartModel>)getIntent().getSerializableExtra("itemlist");
        firestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        recyclerView=findViewById(R.id.address_recycler);
        paymentBtn=findViewById(R.id.payment_btn);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        addressModelList=new ArrayList<>();
        addressAdapter=new AddressAdapter(getApplicationContext(),addressModelList,this);
        recyclerView.setAdapter(addressAdapter);
        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("Address").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
               if(task.isSuccessful()){
                   for(DocumentSnapshot doc: task.getResult().getDocuments()){
                       AddressModel addressModel=doc.toObject(AddressModel.class);
                       addressModelList.add(addressModel);
                       addressAdapter.notifyDataSetChanged();
                   }
               }
            }
        });
        paymentBtn.setOnClickListener(new View.OnClickListener() {

           // @Override
           public void onClick(View view) {
              //  double bill=0.0;
                //Checkout checkout=new Checkout();
               // checkout.setKeyID("rzp_test_3PxOJDxFuweb7h");
                //Object obj = null;
                //if(obj instanceof MyCartModel){
                  //  MyCartModel myCartModel=(MyCartModel) obj;
                   // bill=MyCartModel.getTotalQuantity();
                    // bill=myCartModel.getTotalPrice();
              //  }
                Intent intent=new Intent(AddressActivity.this,PaymentActivity.class);
                //intent.putExtra("bill",bill);
                startActivity(intent);

            }
        });
        addAddres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddressActivity.this,AddAddressActivity.class));
            }
        });
    }

    @Override
    public void setAddress(String address) {
        mAddress=address;
    }
}