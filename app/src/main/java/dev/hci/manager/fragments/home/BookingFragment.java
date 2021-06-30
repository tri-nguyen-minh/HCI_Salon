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
    private TextView txtUpcoming, txtFinished, txtLogin;
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

        tabLayout.addTab(tabLayout.newTab().setText("Upcoming (19)"));
        tabLayout.addTab(tabLayout.newTab().setText("Completed (156)"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.setTabTextColors(getResources().getColor(R.color.white), getResources().getColor(R.color.gold));
        adapter = new NavigationBookingAdapter(getActivity().getSupportFragmentManager(), getContext(),
                getActivity(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);

        tabLayout.selectTab(tabLayout.getTabAt(0));

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


//        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                if (position == 0) {
//                    txtUpcoming.setTextColor(getResources().getColor(R.color.white));
//                    txtUpcoming.setBackground(getResources().getDrawable(R.drawable.background_history_selected));
//
//                    txtFinished.setTextColor(getResources().getColor(R.color.black));
//                    txtFinished.setBackground(getResources().getDrawable(R.drawable.background_edit_text_general));
//                } else {
//                    txtFinished.setTextColor(getResources().getColor(R.color.white));
//                    txtFinished.setBackground(getResources().getDrawable(R.drawable.background_history_selected));
//
//                    txtUpcoming.setTextColor(getResources().getColor(R.color.black));
//                    txtUpcoming.setBackground(getResources().getDrawable(R.drawable.background_edit_text_general));
//                }
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });

    }
}