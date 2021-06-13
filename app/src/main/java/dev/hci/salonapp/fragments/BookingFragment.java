package dev.hci.salonapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dev.hci.salonapp.R;
import dev.hci.salonapp.recycleviewadapter.RecViewSalonAdapter;
import dev.hci.salonapp.recycleviewadapter.RecViewServiceAdapter;
import dev.hci.salonapp.dtos.Salon;
import dev.hci.salonapp.dtos.Service;

public class BookingFragment extends Fragment {

    private RecyclerView recViewCommon;
    private ArrayList<Salon> salonList;
    private RecViewSalonAdapter salonAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_1_booking, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recViewCommon = getView().findViewById(R.id.recViewService);
        ArrayList<Service> servicesList = new ArrayList<>();
        servicesList.add(new Service("Discount", R.drawable.ic_service_discount));
        servicesList.add(new Service("Haircut", R.drawable.ic_service_cut));
        servicesList.add(new Service("Hair\nWashing", R.drawable.ic_service_wash));
        servicesList.add(new Service("Hair\nRestore", R.drawable.ic_service_restore));
        servicesList.add(new Service("Coloring", R.drawable.ic_service_color));
        servicesList.add(new Service("Curling", R.drawable.ic_service_curling));
        servicesList.add(new Service("Straightening", R.drawable.ic_service_straight));
        servicesList.add(new Service("Hair\nStyling", R.drawable.ic_service_style));

        RecViewServiceAdapter serviceAdapter = new RecViewServiceAdapter(getContext(), getActivity());
        serviceAdapter.setServiceList(servicesList);
        recViewCommon.setAdapter(serviceAdapter);
        recViewCommon.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));

        recViewCommon = getView().findViewById(R.id.recViewNearSalon);
        salonList = new ArrayList<>();

        salonList.add(new Salon("Tuan Tay Salon", "Số 578 Sư Vạn Hạnh, Phường 10, Quận 10, Thành phố Hồ Chí Minh",
                1.5, Float.parseFloat("3.5"), 56, 75, R.drawable.salon_tuan_tay));
        salonList.add(new Salon("Phuong Tokyo Salon", "686 Cách Mạng Tháng 8, Phường 05, Quận Tân Bình, Thành phố Hồ Chí Minh",
                2.9, Float.parseFloat("4"), 78, 0, R.drawable.salon_phuong_tokyo));
        salonList.add(new Salon("Mervyn Hair Salon", "Số 16, Cao Bá Nhạ, Phường Nguyễn Cư Trinh, Quận 1, Thành phố Hồ Chí Minh",
                3.3, Float.parseFloat("4"), 126, 0, R.drawable.salon_mervyn_art));
        salonList.add(new Salon("Nguyen Duy Salon", "183E Trần Quốc Thảo, Phường 09, Quận 3, Thành phố Hồ Chí Minh",
                3.6, Float.parseFloat("3.5"), 21, 20, R.drawable.salon_nguyen_duy));
        salonList.add(new Salon("Linh R Hair & Salon", "263 Dương Bá Trạc, Phường 01, Quận 8, Thành phố Hồ Chí Minh",
                3.7, Float.parseFloat("3.5"), 44, 20, R.drawable.salon_linh_r_hair));

        salonAdapter = new RecViewSalonAdapter(getContext(), getActivity());
        salonAdapter.setSalonList(salonList);
        recViewCommon.setAdapter(salonAdapter);
        recViewCommon.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));

        recViewCommon = getView().findViewById(R.id.recViewDiscountSalon);
        salonList = new ArrayList<>();
        salonList.add(new Salon("Do Tuan Salon", "314 Lê Quang Định, Phường 11, Quận Bình Thạnh, Thành phố Hồ Chí Minh",
                6.5, Float.parseFloat("4"), 197, 75, R.drawable.image_default));
        salonList.add(new Salon("Nha Ca Hair Salon", "282 Lê Văn Thọ, Phường 11, Quận Gò Vấp, Thành phố Hồ Chí Minh",
                9.3, Float.parseFloat("4"), 126, 75, R.drawable.image_default));
        salonList.add(new Salon("Nguyen Tuan Salon", "183E Trần Quốc Thảo, Phường 09, Quận 3, Thành phố Hồ Chí Minh",
                6.6, Float.parseFloat("3.5"), 21, 60, R.drawable.image_default));
        salonList.add(new Salon("Tuan Tay Salon", "Số 578 Sư Vạn Hạnh, Phường 10, Quận 10, Thành phố Hồ Chí Minh",
                1.5, Float.parseFloat("3.5"), 56, 75, R.drawable.salon_tuan_tay));
        salonList.add(new Salon("Tay & Tony Salon", "24 Đình Phong Phú, Phường Tăng Nhơn Phú B, Quận 9, Thành phố Hồ Chí Minh",
                16.1, Float.parseFloat("3.5"), 44, 20, R.drawable.image_default));

        salonAdapter = new RecViewSalonAdapter(getContext(), getActivity());
        salonAdapter.setSalonList(salonList);
        recViewCommon.setAdapter(salonAdapter);
        recViewCommon.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));


        recViewCommon = getView().findViewById(R.id.recViewPopularSalon);
        salonList = new ArrayList<>();
        salonList.add(new Salon("Do Tuan Salon", "314 Lê Quang Định, Phường 11, Quận Bình Thạnh, Thành phố Hồ Chí Minh",
                6.5, Float.parseFloat("4"), 197, 75, R.drawable.image_default));
        salonList.add(new Salon("Nha Ca Hair Salon", "282 Lê Văn Thọ, Phường 11, Quận Gò Vấp, Thành phố Hồ Chí Minh",
                9.3, Float.parseFloat("4"), 126, 75, R.drawable.image_default));
        salonList.add(new Salon("Nguyen Tuan Salon", "183E Trần Quốc Thảo, Phường 09, Quận 3, Thành phố Hồ Chí Minh",
                6.6, Float.parseFloat("3.5"), 21, 60, R.drawable.image_default));
        salonList.add(new Salon("Tuan Tay Salon", "Số 578 Sư Vạn Hạnh, Phường 10, Quận 10, Thành phố Hồ Chí Minh",
                1.5, Float.parseFloat("3.5"), 56, 75, R.drawable.salon_tuan_tay));
        salonList.add(new Salon("Tay & Tony Salon", "24 Đình Phong Phú, Phường Tăng Nhơn Phú B, Quận 9, Thành phố Hồ Chí Minh",
                16.1, Float.parseFloat("3.5"), 44, 20, R.drawable.image_default));

        salonAdapter = new RecViewSalonAdapter(getContext(), getActivity());
        salonAdapter.setSalonList(salonList);
        recViewCommon.setAdapter(salonAdapter);
        recViewCommon.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));
    }
}