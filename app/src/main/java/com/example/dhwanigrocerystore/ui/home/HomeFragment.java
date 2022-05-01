package com.example.dhwanigrocerystore.ui.home;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dhwanigrocerystore.R;
import com.example.dhwanigrocerystore.adapters.HomeAdapter;
import com.example.dhwanigrocerystore.adapters.PopularAdapters;
import com.example.dhwanigrocerystore.adapters.RecommendedAdapter;
//import com.example.dhwanigrocerystore.databinding.FragmentHomeBinding;
import com.example.dhwanigrocerystore.adapters.ViewAllAdapter;
import com.example.dhwanigrocerystore.models.HomeCategory;
import com.example.dhwanigrocerystore.models.PopularModel;
import com.example.dhwanigrocerystore.models.RecommendedModel;
import com.example.dhwanigrocerystore.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import androidx.recyclerview.widget.RecyclerView;
public class HomeFragment extends Fragment {
    ScrollView scrollView;
    ProgressBar progressBar;
    RecyclerView popularRec,homeCatRec,recommendedRec;
    FirebaseFirestore db;
    //Popular Items
    List<PopularModel> popularModelList;
    PopularAdapters popularAdapters;
    //Search view
    EditText search_box;
    private List<ViewAllModel> viewAllModelList;
    private RecyclerView recyclerViewSearch;
    private ViewAllAdapter viewAllAdapter;
    //Home category
    List<HomeCategory> categoryList;
    HomeAdapter homeAdapter;
    //Recommended
    List<RecommendedModel> recommendedModelList;
    RecommendedAdapter recommendedAdapter;
    @SuppressLint("NotifyDataSetChanged")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        db=FirebaseFirestore.getInstance();
        //popular items
        popularRec=root.findViewById(R.id.pop_rec);
        homeCatRec=root.findViewById(R.id.exp_rec);
        recommendedRec=root.findViewById(R.id.recommended);
        scrollView=root.findViewById(R.id.scroll_view);
        progressBar=root.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);
        //Popular Items

        popularRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        popularModelList=new ArrayList<>();
        popularAdapters=new PopularAdapters(getActivity(),popularModelList);
        popularRec.setAdapter(popularAdapters);
        db.collection("PopularProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d(TAG, document.getId() + " => " + document.getData());
                                PopularModel popularModel=document.toObject(PopularModel.class);
                                popularModelList.add(popularModel);
                                popularAdapters.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                                scrollView.setVisibility(View.VISIBLE);
                            }
                        } else {
                            //Log.w(TAG, "Error getting documents.", task.getException());
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        //Home Category
        homeCatRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        categoryList=new ArrayList<>();
        homeAdapter=new HomeAdapter(getActivity(),categoryList);
        homeCatRec.setAdapter(homeAdapter);
        db.collection("HomeCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d(TAG, document.getId() + " => " + document.getData());
                                HomeCategory homeCategory=document.toObject(HomeCategory.class);
                                categoryList.add(homeCategory);
                                homeAdapter.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                                scrollView.setVisibility(View.VISIBLE);
                            }
                        } else {
                            //Log.w(TAG, "Error getting documents.", task.getException());
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        //Recommended
       recommendedRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        recommendedModelList=new ArrayList<>();
        recommendedAdapter=new RecommendedAdapter(getActivity(),recommendedModelList);
        recommendedRec.setAdapter(recommendedAdapter);

       db.collection("Recommended")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                          //  Log.d(TAG, document.getId() + " => " + document.getData());
                            RecommendedModel recommendedModel=document.toObject(RecommendedModel.class);
                            recommendedModelList.add(recommendedModel);
                            recommendedAdapter.notifyDataSetChanged();
                        }
                    } else {
                       // Log.w(TAG, "Error getting documents.", task.getException());
                        Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                });
       //Search view
        recyclerViewSearch=root.findViewById(R.id.search_rec);
        search_box=root.findViewById(R.id.search_box);
        viewAllModelList=new ArrayList<>();
        viewAllAdapter=new ViewAllAdapter(getContext(),viewAllModelList);
        recyclerViewSearch.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewSearch.setAdapter(viewAllAdapter);
        recyclerViewSearch.setHasFixedSize(true);
        search_box.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().isEmpty()){
                    viewAllModelList.clear();
                    viewAllAdapter.notifyDataSetChanged();

                }else{
                    searchProduct(s.toString());
                }
            }
        });
        return root;
    }
    private void searchProduct(String type){
        if(!type.isEmpty()){
            db.collection("AllProducts").whereEqualTo("type",type).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful() && task.getResult()!=null){
                                viewAllModelList.clear();
                                viewAllAdapter.notifyDataSetChanged();
                                for(DocumentSnapshot doc:task.getResult().getDocuments()){
                                    ViewAllModel viewAllModel=doc.toObject(ViewAllModel.class);
                                    viewAllModelList.add(viewAllModel);
                                    viewAllAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });
        }
    }

}