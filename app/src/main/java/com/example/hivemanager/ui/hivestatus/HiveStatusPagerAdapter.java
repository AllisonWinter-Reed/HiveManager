package com.example.hivemanager.ui.hivestatus;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class HiveStatusPagerAdapter extends FragmentStateAdapter {
    private int apiaryPosition;
    private int hivePosition;


    public HiveStatusPagerAdapter(@NonNull Fragment fragment, int apiaryPosition, int hivePosition) {
        super(fragment);

        this.apiaryPosition = apiaryPosition;
        this.hivePosition = hivePosition;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
       switch(position) {
           case 1:
               return new EquipmentFragment(apiaryPosition, hivePosition);
//           case 2:
//               return new MetricsFragment(apiaryPosition, hivePosition);
           default:
               return new HealthFragment(apiaryPosition, hivePosition);

       }
    }

    @Override
    public int getItemCount() {
        return 2;
    }


}
