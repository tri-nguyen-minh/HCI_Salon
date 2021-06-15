package dev.hci.salonapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import dev.hci.salonapp.R;
import dev.hci.salonapp.activities.BookingCartActivity;
import dev.hci.salonapp.dtos.Salon;
import dev.hci.salonapp.navigationadapter.NavigationServiceAdapter;
import dev.hci.salonapp.dtos.Service;
import dev.hci.salonapp.recycleviewadapter.RecViewSalonServiceAdapter;

public class ServiceFragment extends Fragment {

    private RecyclerView recViewCommon;
    private RecViewSalonServiceAdapter serviceAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_salon_2_service, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        intent = getActivity().getIntent();
        int cartCount = intent.getIntExtra("cartCount", 0);

        LinearLayout layoutServiceCart = getView().findViewById(R.id.layoutServiceCart);
        if (cartCount == 0) {
            layoutServiceCart.setVisibility(View.GONE);
        } else {
            layoutServiceCart.setVisibility(View.VISIBLE);
            ((TextView)getView().findViewById(R.id.txtCartCount)).setText(cartCount + "");
        }
        layoutServiceCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(getContext(), BookingCartActivity.class);
                newIntent.putExtra("salon", (Salon)intent.getSerializableExtra("salon"));
                startActivity(newIntent);
            }
        });

        ArrayList<Service> servicesList = new ArrayList<>();
        servicesList.add(new Service("Discount", R.drawable.ic_service_discount));
        servicesList.add(new Service("Haircut", R.drawable.ic_service_cut));
        servicesList.add(new Service("Hair Wash", R.drawable.ic_service_wash));
        servicesList.add(new Service("Hair Treatment", R.drawable.ic_service_restore));
        servicesList.add(new Service("Coloring", R.drawable.ic_service_color));
        servicesList.add(new Service("Curling", R.drawable.ic_service_curling));
        servicesList.add(new Service("Straightening", R.drawable.ic_service_straight));
        servicesList.add(new Service("Hair Styling", R.drawable.ic_service_style));

        recViewCommon = getView().findViewById(R.id.recViewService);
        serviceAdapter = new RecViewSalonServiceAdapter(getContext(), getActivity());
        serviceAdapter.setServiceList(servicesList);
        recViewCommon.setAdapter(serviceAdapter);
        recViewCommon.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));

        tabLayout = getView().findViewById(R.id.tabLayoutService);
        viewPager = getView().findViewById(R.id.viewPagerService);

        tabLayout.addTab(tabLayout.newTab().setText("discount"));
        tabLayout.addTab(tabLayout.newTab().setText("cut"));
        tabLayout.addTab(tabLayout.newTab().setText("wash"));
        tabLayout.addTab(tabLayout.newTab().setText("treat"));
        tabLayout.addTab(tabLayout.newTab().setText("color"));
        tabLayout.addTab(tabLayout.newTab().setText("curl"));
        tabLayout.addTab(tabLayout.newTab().setText("straighten"));
        tabLayout.addTab(tabLayout.newTab().setText("style"));
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
    }
}