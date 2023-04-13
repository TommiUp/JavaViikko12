package com.example.javaviikko12.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.javaviikko12.ProductListAdapter;
import com.example.javaviikko12.R;
import com.example.javaviikko12.Storage;

import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {

    private Storage storage;
    private Context context;
    private RecyclerView recyclerView;
    private ProductListAdapter adapter;
    private ImageView imgCalendar, imgABC;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        storage = Storage.getInstance();
        storage.loadProducts(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        storage = Storage.getInstance();
        recyclerView = view.findViewById(R.id.rvProductList);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new ProductListAdapter(getActivity(), storage.getProducts());
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        adapter.setProducts(storage.getProducts());
        adapter.notifyDataSetChanged();
    }
}