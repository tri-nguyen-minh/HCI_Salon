package dev.hci.manager.fragments.home;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import dev.hci.manager.R;
import dev.hci.manager.navigations.NavigationBookingAdapter;

public class BookingFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Intent intent;
    private String pageIdentifier;
    private NavigationBookingAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        intent = getActivity().getIntent();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_2_booking, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabLayout = getView().findViewById(R.id.tabLayoutAppointment);
        viewPager = getView().findViewById(R.id.viewPagerAppointment);

        tabLayout.addTab(tabLayout.newTab().setText("Upcoming\n(19)"));
        tabLayout.addTab(tabLayout.newTab().setText("Completed\n(166)"));
        tabLayout.addTab(tabLayout.newTab().setText("Cancelled\n(28)"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.setTabTextColors(getResources().getColor(R.color.white), getResources().getColor(R.color.gold));
        adapter = new NavigationBookingAdapter(getActivity().getSupportFragmentManager(), getContext(),
                getActivity(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getIcon() != null)
                    tab.getIcon().setColorFilter(getResources().getColor(R.color.gold), PorterDuff.Mode.SRC_IN);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab.getIcon() != null)
                    tab.getIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        pageIdentifier = intent.getStringExtra("PAGE_IDENTIFIER");
        if (pageIdentifier != null) {
            if (pageIdentifier.equals("BOOKING_UPCOMING")) {
                tabLayout.selectTab(tabLayout.getTabAt(0));
            } else if (pageIdentifier.equals("BOOKING_COMPLETED")) {
                tabLayout.selectTab(tabLayout.getTabAt(1));
            } else {
                tabLayout.selectTab(tabLayout.getTabAt(2));
            }
        } else {
            tabLayout.selectTab(tabLayout.getTabAt(0));
        }

    }
}