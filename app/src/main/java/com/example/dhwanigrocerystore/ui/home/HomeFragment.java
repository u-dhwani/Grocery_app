package com.example.dhwanigrocerystore.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.dhwanigrocerystore.R;
import com.example.dhwanigrocerystore.adapters.PopularAdapters;
import com.example.dhwanigrocerystore.databinding.FragmentHomeBinding;
import com.example.dhwanigrocerystore.models.PopularModel;

import java.util.List;

public class HomeFragment extends Fragment {
    List<PopularModel> popularModelList;
    PopularAdapters popularAdapters;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

}