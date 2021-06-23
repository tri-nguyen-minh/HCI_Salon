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
import dev.hci.salonapp.dtos.Product;
import dev.hci.salonapp.dtos.Service;
import dev.hci.salonapp.recycleviewadapter.RecViewProductAdapter;
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
        servicesList.add(new Service("Combos", R.drawable.ic_product_combo));
        servicesList.add(new Service("Shampoo", R.drawable.ic_product_shampoo));
        servicesList.add(new Service("Conditioner", R.drawable.ic_product_conditioner));
        servicesList.add(new Service("Oils & Cream", R.drawable.ic_product_oil_cream));
        servicesList.add(new Service("Wax & Gel", R.drawable.ic_product_wax_gel));

        RecViewServiceAdapter serviceAdapter = new RecViewServiceAdapter(getContext(), getActivity());
        serviceAdapter.setServiceList(servicesList);
        recViewCommon.setAdapter(serviceAdapter);
        recViewCommon.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));


        recViewCommon = getView().findViewById(R.id.recViewProductRecommended);
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(new Product("PERFECT CLEANSE\nShampoo", "LAKME", "570", "0", false, 0, R.drawable.img_product_shampoo_1));
        productList.add(new Product("Hydra\nMoisturizing Shampoo", "Farmagan", "315", "420", false, 25, R.drawable.img_product_shampoo_2));
        productList.add(new Product("Teknia\nShampoo", "LAKME", "570", "0", false, 0, R.drawable.img_product_shampoo_3));
        productList.add(new Product("THAT'S IT Shampoo\nfor White & Grey Hair", "Alfaparf Milano", "420", "0", false, 0, R.drawable.img_product_shampoo_4));
        productList.add(new Product("Precious Nature\nShampoo for Curly Hair", "Alfaparf Milano", "810", "1.040", false, 25, R.drawable.img_product_shampoo_5));
        productList.add(new Product("FULL DEFENSE\nShampoo", "LAKME", "480", "600", false, 20, R.drawable.img_product_shampoo_6));
        productList.add(new Product("Orzen\nAnti-Dandruff Shampoo", "Obsidian", "355", "0", false, 0, R.drawable.img_product_shampoo_7));
        productList.add(new Product("Orzen\nHair Growth Shampoo", "Obsidian", "355", "0", false, 0, R.drawable.img_product_shampoo_8));

        RecViewProductAdapter productAdapter = new RecViewProductAdapter(getContext(), getActivity());
        productAdapter.setProductsList(productList);
        recViewCommon.setAdapter(productAdapter);
        recViewCommon.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));

    }
}