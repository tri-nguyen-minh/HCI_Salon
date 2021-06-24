package dev.hci.salonapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import dev.hci.salonapp.R;
import dev.hci.salonapp.navigationadapter.NavigationHistoryAdapter;

public class HistoryFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Intent intent;
    private TextView txtUpcoming, txtFinished, txtLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        intent = getActivity().getIntent();
        if (!intent.getBooleanExtra("logged", false)) {
            return inflater.inflate(R.layout.fragment_main_6_history_unknown, container, false);
        }
        return inflater.inflate(R.layout.fragment_main_3_history, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (!intent.getBooleanExtra("logged", false)) {
            System.out.println("in logg false");
            txtLogin = getView().findViewById(R.id.txtToLogin);
            txtLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tabLayout = getActivity().findViewById(R.id.tabLayout);
                    tabLayout.selectTab(tabLayout.getTabAt(3));
                }
            });
        } else {

            System.out.println("in logg true");
            tabLayout = getView().findViewById(R.id.tabLayoutHistory);
            viewPager = getView().findViewById(R.id.viewPagerHistory);

            tabLayout.addTab(tabLayout.newTab().setText("Upcoming"));
            tabLayout.addTab(tabLayout.newTab().setText("Finished"));
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

            NavigationHistoryAdapter adapter = new NavigationHistoryAdapter(getActivity().getSupportFragmentManager(), getContext(),
                    getActivity(), tabLayout.getTabCount());
            viewPager.setAdapter(adapter);
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
            });
            tabLayout.setVisibility(View.GONE);

            viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    if (position == 0) {
                        txtUpcoming.setTextColor(getResources().getColor(R.color.white));
                        txtUpcoming.setBackground(getResources().getDrawable(R.drawable.background_history_selected));

                        txtFinished.setTextColor(getResources().getColor(R.color.black));
                        txtFinished.setBackground(getResources().getDrawable(R.drawable.background_edit_text_general));
                    } else {
                        txtFinished.setTextColor(getResources().getColor(R.color.white));
                        txtFinished.setBackground(getResources().getDrawable(R.drawable.background_history_selected));

                        txtUpcoming.setTextColor(getResources().getColor(R.color.black));
                        txtUpcoming.setBackground(getResources().getDrawable(R.drawable.background_edit_text_general));
                    }

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

            txtUpcoming = getView().findViewById(R.id.txtUpcomingBooking);
            txtFinished = getView().findViewById(R.id.txtFinishedBooking);

            txtUpcoming.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    txtUpcoming.setTextColor(getResources().getColor(R.color.white));
                    txtUpcoming.setBackground(getResources().getDrawable(R.drawable.background_history_selected));

                    txtFinished.setTextColor(getResources().getColor(R.color.black));
                    txtFinished.setBackground(getResources().getDrawable(R.drawable.background_edit_text_general));
                    tabLayout.selectTab(tabLayout.getTabAt(0));
                }
            });
            txtFinished.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    txtFinished.setTextColor(getResources().getColor(R.color.white));
                    txtFinished.setBackground(getResources().getDrawable(R.drawable.background_history_selected));

                    txtUpcoming.setTextColor(getResources().getColor(R.color.black));
                    txtUpcoming.setBackground(getResources().getDrawable(R.drawable.background_edit_text_general));
                    tabLayout.selectTab(tabLayout.getTabAt(1));
                }
            });
        }
    }
}