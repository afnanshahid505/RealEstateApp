package com.example.gen69;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class BuyerPagerAdapter extends FragmentStateAdapter {

    private final Fragment plotsFragment;
    private final Fragment houseFragment;

    public BuyerPagerAdapter(@NonNull FragmentActivity fragmentActivity, Fragment plotsFragment, Fragment houseFragment) {
        super(fragmentActivity);
        this.plotsFragment = plotsFragment;
        this.houseFragment = houseFragment;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return position == 0 ? plotsFragment : houseFragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
