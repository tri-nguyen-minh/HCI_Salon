package dev.hci.salonapp.fragments.history;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dev.hci.salonapp.R;
import dev.hci.salonapp.dtos.BookingHistory;
import dev.hci.salonapp.dtos.ServiceDetail;
import dev.hci.salonapp.recycleviewadapter.RecViewBookingHistoryAdapter;
import dev.hci.salonapp.recycleviewadapter.RecViewServiceAdapter;

public class UpcomingHistoryFragment extends Fragment {

    private Intent intent;
    private BookingHistory bookingHistory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getActivity().getIntent();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history_1_upcoming, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recViewBookingHistoryUpcoming = getView().findViewById(R.id.recViewBookingHistoryUpcoming);

        ArrayList<BookingHistory> bookingHistoryList = new ArrayList<>();

        bookingHistory = new BookingHistory();
        bookingHistory.setSalonName("Phuong Tokyo Salon");
        bookingHistory.setSalonAddress("686 Cách Mạng Tháng 8, Phường 05, Quận Tân Bình, Thành phố Hồ Chí Minh");
        ArrayList<ServiceDetail> serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Male Haircut", "30 - 40 minutes", "30", "60", 50));
        serviceDetailsList.add(new ServiceDetail("Hair Collagen Treatment", "30 - 60 minutes", "300", "400", 25));
        bookingHistory.setServiceList(serviceDetailsList);
        bookingHistory.setDate("Sat, July 10, 2021");
        bookingHistory.setTime("10:00");
        bookingHistoryList.add(bookingHistory);

        System.out.println("int upcoming");
        bookingHistory = (BookingHistory)intent.getSerializableExtra("booking");
        if (bookingHistory != null) {
            bookingHistoryList.add(0, bookingHistory);
        }

        RecViewBookingHistoryAdapter historyAdapter = new RecViewBookingHistoryAdapter(getContext(), getActivity());
        historyAdapter.setEmptyTextId(R.id.txtNoHistoryUpcoming);
        historyAdapter.setRecViewId(R.id.recViewBookingHistoryUpcoming);
        historyAdapter.setBookingHistoryList(bookingHistoryList);
        recViewBookingHistoryUpcoming.setAdapter(historyAdapter);
        recViewBookingHistoryUpcoming.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));

    }
}