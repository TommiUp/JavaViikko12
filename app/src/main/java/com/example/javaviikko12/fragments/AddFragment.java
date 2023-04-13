package com.example.javaviikko12.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.javaviikko12.Product;
import com.example.javaviikko12.InfoListAdapter;
import com.example.javaviikko12.R;
import com.example.javaviikko12.Storage;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFragment extends Fragment {

    private EditText productName;
    private EditText productNotes;
    private Button addButton;

    private CheckBox checkImportant;
    private Context context;

    private InfoListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        productName = view.findViewById(R.id.productNameText);
        productNotes = view.findViewById(R.id.productNotesText);
        checkImportant = view.findViewById(R.id.checkImportant);

        addButton = view.findViewById(R.id.btnAddProduct);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addOstos(view);
            }
        });
        return view;
    }

    public void addOstos(View view) {
        String addProductName = productName.getText().toString();
        String addProductNotes = productNotes.getText().toString();
        Product newProduct = new Product(addProductName, addProductNotes);
        if (checkImportant.isChecked()){
            Storage.getInstance().addImportantProduct(newProduct);
        }
        Storage.getInstance().addProduct(newProduct);
        Storage.getInstance().saveProducts(context);
        checkImportant.setChecked(false);
        productName.setText("");
        productNotes.setText("");
    }
}