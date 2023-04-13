package com.example.javaviikko12.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.javaviikko12.InfoListAdapter;
import com.example.javaviikko12.R;
import com.example.javaviikko12.Storage;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoFragment extends Fragment {


    private TextView txtImportant;
    private Storage storage;
    private Context context;
    private RecyclerView recyclerView;
    private InfoListAdapter adapter;

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
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        txtImportant = view.findViewById(R.id.txtImportant);
        storage = Storage.getInstance();
        recyclerView = view.findViewById(R.id.rvImportantList);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new InfoListAdapter(getActivity(), storage.getImportantProducts());
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        adapter.setProducts(storage.getImportantProducts());
        adapter.notifyDataSetChanged();
    }
}