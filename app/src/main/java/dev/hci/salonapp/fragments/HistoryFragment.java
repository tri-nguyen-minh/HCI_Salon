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
import dev.hci.salonapp.navigationadapter.NavigationServiceAdapter;

public class HistoryFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_3_history, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        intent = getActivity().getIntent();

        tabLayout = getView().findViewById(R.id.tabLayoutHistory);
        viewPager = getView().findViewById(R.id.viewPagerHistory);

        tabLayout.addTab(tabLayout.newTab().setText("booking"));
        tabLayout.addTab(tabLayout.newTab().setText("shopping"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        NavigationServiceAdapter adapter = new NavigationServiceAdapter(getActivity().getSupportFragmentManager(), getContext(),
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

        TextView txtBooking = getView().findViewById(R.id.txtBookingHistory);
        TextView txtShopping = getView().findViewById(R.id.txtShoppingHistory);

        txtBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtBooking.setTextColor(getResources().getColor(R.color.white));
                txtBooking.setBackground(getResources().getDrawable(R.drawable.background_history_selected));

                txtShopping.setTextColor(getResources().getColor(R.color.black));
                txtShopping.setBackground(getResources().getDrawable(R.drawable.background_edit_text_general));
                tabLayout.selectTab(tabLayout.getTabAt(0));
            }
        });
        txtShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtShopping.setTextColor(getResources().getColor(R.color.white));
                txtShopping.setBackground(getResources().getDrawable(R.drawable.background_history_selected));

                txtBooking.setTextColor(getResources().getColor(R.color.black));
                txtBooking.setBackground(getResources().getDrawable(R.drawable.background_edit_text_general));
                tabLayout.selectTab(tabLayout.getTabAt(1));
            }
        });
    }
}