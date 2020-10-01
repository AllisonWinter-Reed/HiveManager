package com.example.hivemanager.ui.hivestatus;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class HiveStatusPagerAdapter extends FragmentStateAdapter {
    public HiveStatusPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
       switch(position) {
           case 1:
               return new EquipmentFragment();
           case 2:
               return new MetricsFragment();
           default:
               return new HealthFragment();

       }
    }

    @Override
    public int getItemCount() {
        return 3;
    }


}
