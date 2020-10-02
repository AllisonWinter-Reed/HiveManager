package com.example.hivemanager.ui.hivestatus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.hivemanager.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HiveStatusFragment extends Fragment {

    private static final int NUM_TABS = 3;
    private ViewPager2 mPager;
    private PagerAdapter pagerAdapter;
    private int apiaryPositon;
    private int hivePosition;

    public HiveStatusFragment() {

    }

    public HiveStatusFragment(int apiaryPositon, int hivePosition) {
        this.apiaryPositon = apiaryPositon;
        this.hivePosition = hivePosition;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hivestatus, container, false);
        mPager = (ViewPager2) view.findViewById(R.id.view_pager);
        mPager.setAdapter(new HiveStatusPagerAdapter(this, apiaryPositon, hivePosition));


        TabLayout tabLayout = view.findViewById(R.id.hiveStatusTabs);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, mPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch(position) {
                    case 0:
                        tab.setText("Health");
                        break;
                    case 1:
                        tab.setText("Equipment");
                        break;
                    case 2:
                        tab.setText("Metrics");
                        break;
                }

            }
        }
        );
        tabLayoutMediator.attach();
        return view;
    }


}