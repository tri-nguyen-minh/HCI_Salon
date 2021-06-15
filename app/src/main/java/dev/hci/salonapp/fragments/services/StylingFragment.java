package dev.hci.salonapp.fragments.services;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import dev.hci.salonapp.R;
import dev.hci.salonapp.dtos.ServiceDetail;
import dev.hci.salonapp.recycleviewadapter.RecViewServiceDetailAdapter;

public class StylingFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_service_8_styling, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = getView().findViewById(R.id.recViewServiceDetailStyle);
        ArrayList<ServiceDetail> serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Hair Styling", "30 minutes", "70", "0"));
        RecViewServiceDetailAdapter adapter = new RecViewServiceDetailAdapter(getContext(), getActivity());
        adapter.setServiceDetailsList(serviceDetailsList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
    }
}