package com.example.javaviikko12;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

public class TabActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private InfoListAdapter adapter;
    private Storage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        // initialize tab layout, view pager, and text view
        TabPagerAdapter tabPagerAdapter = new TabPagerAdapter(this);
        ViewPager2 fragmentArea = findViewById(R.id.fragmentArea);
        TextView bottomTextView = findViewById(R.id.bottomTextView);
        fragmentArea.setAdapter(tabPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tabArea);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                fragmentArea.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 3) {
                    bottomTextView.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.GONE);
                } else {
                    bottomTextView.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        fragmentArea.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        recyclerView = findViewById(R.id.rvSuperImportant);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        storage = Storage.getInstance();
        storage.loadProducts(getApplicationContext());
        adapter = new InfoListAdapter(getApplicationContext(), storage.getImportantProducts());
        recyclerView.setAdapter(adapter);
        adapter.setProducts(storage.getImportantProducts());
        adapter.notifyDataSetChanged();
    }
}