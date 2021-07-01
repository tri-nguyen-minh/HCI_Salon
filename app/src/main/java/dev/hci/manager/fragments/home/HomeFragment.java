package dev.hci.manager.fragments.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import dev.hci.manager.R;
import dev.hci.manager.activities.ServiceTypeActivity;
import dev.hci.manager.dtos.Booking;
import dev.hci.manager.dtos.ServiceDetail;
import dev.hci.manager.recycleviewadapter.RecViewBookingAdapter;

public class HomeFragment extends Fragment {

    private ArrayList<Booking> bookingListToday, bookingListUpcoming;
    private Booking booking;
    private Intent intent;
    private Bundle bundle;
    private String pageIdentifier;
    private ArrayList<ServiceDetail> serviceDetailsList;
    private RecyclerView recViewCommon;
    private RecViewBookingAdapter adapter;
    private String bookingDate, laterBookingDate, evenLaterBookingDate;
    private Calendar calendar;
    private final DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        intent = getActivity().getIntent();
        calendar = Calendar.getInstance();
        bookingDate = format.format(calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        laterBookingDate = format.format(calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        evenLaterBookingDate = format.format(calendar.getTime());
        return inflater.inflate(R.layout.fragment_main_1_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pageIdentifier = intent.getStringExtra("PAGE_IDENTIFIER");
        if (pageIdentifier != null) {
            bundle = intent.getBundleExtra("BUNDLE");
            if (pageIdentifier.equals("HOME_TODAY")) {
                bookingListToday = (ArrayList<Booking>) bundle.getSerializable("LIST");
            } else if (pageIdentifier.equals("HOME_UPCOMING")) {
                bookingListUpcoming = (ArrayList<Booking>) bundle.getSerializable("LIST");
            } else {
                setupList();
            }
        } else {
            setupList();
        }

        recViewCommon = getView().findViewById(R.id.recViewBookingToday);

        adapter = new RecViewBookingAdapter(getContext(), getActivity());
        adapter.setBookingList(bookingListToday);
        adapter.setRecViewId(R.id.recViewBookingToday);
        adapter.setOrientationId(RecyclerView.HORIZONTAL);
        adapter.setLayoutId(R.layout.recycle_view_booking_card);
        adapter.setPageIdentifier("HOME_TODAY");
        recViewCommon.setAdapter(adapter);

        recViewCommon.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));

        recViewCommon = getView().findViewById(R.id.recViewBookingUpcoming);

        adapter = new RecViewBookingAdapter(getContext(), getActivity());
        adapter.setBookingList(bookingListUpcoming);
        adapter.setRecViewId(R.id.recViewBookingUpcoming);
        adapter.setLayoutId(R.layout.recycle_view_booking_card);
        adapter.setOrientationId(RecyclerView.HORIZONTAL);
        adapter.setPageIdentifier("HOME_UPCOMING");
        recViewCommon.setAdapter(adapter);

        recViewCommon.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));

        TextView txtView = getView().findViewById(R.id.txtViewMore);
        txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TabLayout tab = getActivity().findViewById(R.id.tabLayout);
                tab.selectTab(tab.getTabAt(1));
                tab = getActivity().findViewById(R.id.tabLayoutAppointment);
                tab.selectTab(tab.getTabAt(0));
            }
        });
    }

    private void setupList() {
        bookingListToday = new ArrayList<>();
        booking = new Booking("Van Kien","0913921731","08:00", bookingDate, 172,
                0, 2);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Male Haircut", "30 - 40 minutes", "50", "0",35,0));
        serviceDetailsList.add(new ServiceDetail("Hair Styling", "30 minutes", "70", "0",87,0));
        booking.setServiceDetailsList(serviceDetailsList);
        bookingListToday.add(booking);
        booking = new Booking("Le Xuan","0933921237","10:00", bookingDate, 178,
                0, 1);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Hair Loss Treatment", "60 - 120 minutes", "800", "1.200",6,30));
        serviceDetailsList.add(new ServiceDetail("Coloring (Short)", "60 - 90 minutes", "550", "0",24,0));
        booking.setServiceDetailsList(serviceDetailsList);
        bookingListToday.add(booking);
        booking = new Booking("Quoc Trung","0264987564","11:00", bookingDate, 168,
                0, 0);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Male Haircut", "30 - 40 minutes", "50", "0",35,0));
        serviceDetailsList.add(new ServiceDetail("Hair Styling", "30 minutes", "70", "0",87,0));
        serviceDetailsList.add(new ServiceDetail("Coloring (Short)", "60 - 90 minutes", "550", "0",24,0));
        booking.setServiceDetailsList(serviceDetailsList);
        bookingListToday.add(booking);
        booking = new Booking("Minh Thong","017964331","13:00", bookingDate, 188,
                0, 0);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Male Haircut", "30 - 40 minutes", "50", "0",35,0));
        serviceDetailsList.add(new ServiceDetail("Hair Wash", "15 minutes", "30", "0",91,0));
        booking.setServiceDetailsList(serviceDetailsList);
        bookingListToday.add(booking);

        booking = new Booking("Thao Van","097612823","16:00", bookingDate, 153,
                0, 0);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Female Haircut", "30 - 45 minutes", "100", "0",61,0));
        serviceDetailsList.add(new ServiceDetail("Wash + Massage", "60 minutes", "42", "70",75,40));
        serviceDetailsList.add(new ServiceDetail("Hair Keratin Treatment", "30 - 60 minutes", "350", "500",29,30));
        serviceDetailsList.add(new ServiceDetail("Hair Straightening", "60 - 120 minutes", "450", "0",23,0));
        booking.setServiceDetailsList(serviceDetailsList);
        bookingListToday.add(booking);

        booking = new Booking("Thang Loi","0213497632","19:00", bookingDate, 180,
                0, 3);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Male Haircut", "30 - 40 minutes", "50", "0",35,0));
        booking.setServiceDetailsList(serviceDetailsList);
        bookingListToday.add(booking);


        bookingListUpcoming = new ArrayList<>();

        booking = new Booking("Thao Van","097612823","16:00", bookingDate, 153,
                0, 0);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Female Haircut", "30 - 45 minutes", "100", "0",61,0));
        serviceDetailsList.add(new ServiceDetail("Wash + Massage", "60 minutes", "42", "70",75,40));
        serviceDetailsList.add(new ServiceDetail("Hair Keratin Treatment", "30 - 60 minutes", "350", "500",29,30));
        serviceDetailsList.add(new ServiceDetail("Hair Straightening", "60 - 120 minutes", "450", "0",23,0));
        booking.setServiceDetailsList(serviceDetailsList);
        bookingListUpcoming.add(booking);

        booking = new Booking("Van Anh","0216597896","09:00", laterBookingDate, 171,
                0, 0);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Female Haircut", "30 - 45 minutes", "100", "0",61,0));
        serviceDetailsList.add(new ServiceDetail("Wash + Massage", "60 minutes", "42", "70",75,40));
        serviceDetailsList.add(new ServiceDetail("Hair Straightening", "60 - 120 minutes", "450", "0",23,0));
        booking.setServiceDetailsList(serviceDetailsList);
        bookingListUpcoming.add(booking);

        booking = new Booking("Manh Duc","097986121","10:00", laterBookingDate, 176,
                0, 0);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Hair Styling", "30 minutes", "70", "0",87,0));
        booking.setServiceDetailsList(serviceDetailsList);
        bookingListUpcoming.add(booking);

        booking = new Booking("Thanh Dat","013449879","11:00", laterBookingDate, 186,
                0, 0);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Male Haircut", "30 - 40 minutes", "50", "0",35,0));
        serviceDetailsList.add(new ServiceDetail("Female Haircut", "30 - 45 minutes", "100", "0",61,0));
        serviceDetailsList.add(new ServiceDetail("Wash + Massage", "60 minutes", "42", "70",75,40));
        serviceDetailsList.add(new ServiceDetail("Hair Straightening", "60 - 120 minutes", "450", "0",23,0));
        serviceDetailsList.add(new ServiceDetail("Hair Loss Treatment", "60 - 120 minutes", "800", "1.200",6,30));
        booking.setServiceDetailsList(serviceDetailsList);
        bookingListUpcoming.add(booking);

        booking = new Booking("Trung Nghia","0943157985","14:00", laterBookingDate, 181,
                0, 0);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Male Haircut", "30 - 40 minutes", "50", "0",35,0));
        serviceDetailsList.add(new ServiceDetail("Wash + Massage", "60 minutes", "42", "70",75,40));
        booking.setServiceDetailsList(serviceDetailsList);
        bookingListUpcoming.add(booking);

        booking = new Booking("My Tam","013479884","17:00", laterBookingDate, 166,
                0, 0);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Female Haircut", "30 - 45 minutes", "100", "0",61,0));
        serviceDetailsList.add(new ServiceDetail("Wash + Massage", "60 minutes", "42", "70",75,40));
        serviceDetailsList.add(new ServiceDetail("Hair Curling", "30 - 60 minutes", "400", "0",21,0));
        serviceDetailsList.add(new ServiceDetail("Hair Loss Treatment", "60 - 120 minutes", "800", "1.200",6,30));
        booking.setServiceDetailsList(serviceDetailsList);
        bookingListUpcoming.add(booking);

        booking = new Booking("Nhu Y","013467964","08:00", evenLaterBookingDate, 177,
                0, 0);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Female Haircut", "30 - 45 minutes", "100", "0",61,0));
        serviceDetailsList.add(new ServiceDetail("Wash + Massage", "60 minutes", "42", "70",75,40));
        serviceDetailsList.add(new ServiceDetail("Hair Curling", "30 - 60 minutes", "400", "0",21,0));
        serviceDetailsList.add(new ServiceDetail("Coloring (Long)", "60 - 90 minutes", "700", "0",45,0));
        booking.setServiceDetailsList(serviceDetailsList);
        bookingListUpcoming.add(booking);

        booking = new Booking("Thanh Nhan","013497985","11:00", evenLaterBookingDate, 165,
                0, 0);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Male Haircut", "30 - 40 minutes", "50", "0",35,0));
        serviceDetailsList.add(new ServiceDetail("Coloring (Short)", "60 - 90 minutes", "550", "0",24,0));
        booking.setServiceDetailsList(serviceDetailsList);
        bookingListUpcoming.add(booking);

        booking = new Booking("Minh Hang","0946131879","12:00", evenLaterBookingDate, 185,
                0, 0);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Wash + Massage", "60 minutes", "42", "70",75,40));
        serviceDetailsList.add(new ServiceDetail("Coloring (Long)", "60 - 90 minutes", "700", "0",45,0));
        serviceDetailsList.add(new ServiceDetail("Hair Styling", "30 minutes", "70", "0",87,0));
        booking.setServiceDetailsList(serviceDetailsList);
        bookingListUpcoming.add(booking);

        booking = new Booking("Lan Tan","01287946135","13:00", evenLaterBookingDate, 193,
                0, 0);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Male Haircut", "30 - 40 minutes", "50", "0",35,0));
        booking.setServiceDetailsList(serviceDetailsList);
        bookingListUpcoming.add(booking);
    }
}