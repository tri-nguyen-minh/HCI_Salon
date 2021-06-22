package dev.hci.salonapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dev.hci.salonapp.R;
import dev.hci.salonapp.dtos.Service;
import dev.hci.salonapp.recycleviewadapter.RecViewServiceAdapter;

public class ProductFragment extends Fragment {

    private ImageView imgCommon;
    private RecyclerView recViewCommon;
    private Spinner spinnerService;
    private ArrayList<String> dataSpinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_2_product, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        ImageView imgSearchSalonName = getView().findViewById(R.id.imgSearchSalonName);
        imgSearchSalonName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        ImageView imgSearchSlider = getView().findViewById(R.id.imgSearchSliderMain);
        imgSearchSlider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RelativeLayout searchDrawerHandle = getView().findViewById(R.id.searchDrawerHandle);
                searchDrawerHandle.performClick();
            }
        });


        recViewCommon = getView().findViewById(R.id.recViewProductCategories);
        ArrayList<Service> servicesList = new ArrayList<>();
        servicesList.add(new Service("Discount", R.drawable.ic_service_discount));
        servicesList.add(new Service("Shampoo", R.drawable.ic_product_shampoo));
        servicesList.add(new Service("Conditioner", R.drawable.ic_product_conditioner));
        servicesList.add(new Service("Oils & Cream", R.drawable.ic_product_oil_cream));
        servicesList.add(new Service("Wax & Gel", R.drawable.ic_product_wax_gel));

        RecViewServiceAdapter serviceAdapter = new RecViewServiceAdapter(getContext(), getActivity());
        serviceAdapter.setServiceList(servicesList);
        recViewCommon.setAdapter(serviceAdapter);
        recViewCommon.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));

    }
}