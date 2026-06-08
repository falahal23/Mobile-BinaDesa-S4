package com.example.falahal_wrold.Home.pertemuan10;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TenthTabsAdapter extends FragmentStateAdapter {

    public TenthTabsAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new TabAFragment();
            case 1:
                return new TabBFragment();
            case 2:
                return new TabCFragment();
            default:
                throw new IllegalStateException("Posisi tidak valid");
        }
    }
}