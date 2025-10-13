package com.example.gen69;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class BuyerPagerAdapter extends FragmentStateAdapter {

    private final PlotsFragment plotsFragment;
    private final HouseFragment houseFragment;

    public BuyerPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        plotsFragment = new PlotsFragment();
        houseFragment = new HouseFragment();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return (position == 0) ? plotsFragment : houseFragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public PlotsFragment getPlotsFragment() {
        return plotsFragment;
    }

    public HouseFragment getHouseFragment() {
        return houseFragment;
    }
}
