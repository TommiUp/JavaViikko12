package com.example.javaviikko12;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.javaviikko12.fragments.AddFragment;
import com.example.javaviikko12.fragments.InfoFragment;
import com.example.javaviikko12.fragments.ListFragment;
import com.example.javaviikko12.fragments.MainFragment;

public class TabPagerAdapter extends FragmentStateAdapter {
    public TabPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new MainFragment();
            case 1:
                return new ListFragment();
            case 2:
                return new AddFragment();
            case 3:
                // Create a new InfoFragment and set its arguments
                InfoFragment infoFragment = new InfoFragment();
                Bundle info = new Bundle();
                info.putString("infoID", "Tekstiä TabActivitystä!");
                infoFragment.setArguments(info);
                return infoFragment;
            default:
                return new MainFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
