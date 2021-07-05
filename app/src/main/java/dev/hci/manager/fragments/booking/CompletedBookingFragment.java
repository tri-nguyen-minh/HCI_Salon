package dev.hci.manager.fragments.booking;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import dev.hci.manager.R;
import dev.hci.manager.dtos.Booking;
import dev.hci.manager.dtos.ServiceDetail;
import dev.hci.manager.recycleviewadapter.RecViewBookingAdapter;

public class CompletedBookingFragment extends Fragment {

    private ArrayList<Booking> bookingList;
    private Booking booking;
    private Intent intent;
    private Bundle bundle;
    private String pageIdentifier;
    private ArrayList<ServiceDetail> serviceDetailsList;
    private RecyclerView recViewCommon;
    private RecViewBookingAdapter adapter;
    private String bookingDate, earlierBookingDate, evenEarlierBookingDate;
    private Calendar calendar;
    private final DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        calendar = Calendar.getInstance();
        bookingDate = format.format(calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        earlierBookingDate = format.format(calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        evenEarlierBookingDate = format.format(calendar.getTime());
        return inflater.inflate(R.layout.fragment_booking_2_completed, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recViewCommon = getView().findViewById(R.id.recViewAppointmentCompleted);

        intent = getActivity().getIntent();
        pageIdentifier = intent.getStringExtra("PAGE_IDENTIFIER");
        if (pageIdentifier != null) {
            if (pageIdentifier.equals("BOOKING_COMPLETED")) {
                bundle = intent.getBundleExtra("BUNDLE");
                bookingList = (ArrayList<Booking>) bundle.getSerializable("LIST");
            } else {
                setupList();
            }
        } else {
            setupList();
        }

        adapter = new RecViewBookingAdapter(getContext(), getActivity());
        adapter.setBookingList(bookingList);
        adapter.setRecViewId(R.id.recViewAppointmentCompleted);
        adapter.setOrientationId(RecyclerView.VERTICAL);
        adapter.setPageIdentifier("BOOKING_COMPLETED");
        adapter.setLayoutId(R.layout.recycle_view_booking_simple_card);
        recViewCommon.setAdapter(adapter);

        recViewCommon.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
    }

    private void setupList() {

        bookingList = new ArrayList<>();

        booking = new Booking("Le Xuan","0933921237","10:00", bookingDate, 178,
                0, 1);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Hair Loss Treatment", "60 - 120 minutes", "800", "1200",6,30));
        serviceDetailsList.add(new ServiceDetail("Coloring (Short)", "60 - 90 minutes", "550", "0",24,0));
        booking.setServiceDetailsList(serviceDetailsList);
        bookingList.add(booking);

        booking = new Booking("Quoc Trung","0264987564","18:00", earlierBookingDate, 167,
                0, 2);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Male Haircut", "30 - 40 minutes", "50", "0",35,0));
        serviceDetailsList.add(new ServiceDetail("Hair Styling", "30 minutes", "70", "0",87,0));
        serviceDetailsList.add(new ServiceDetail("Coloring (Short)", "60 - 90 minutes", "550", "0",24,0));
        booking.setServiceDetailsList(serviceDetailsList);
        bookingList.add(booking);

        booking = new Booking("Manh Duc","097986121","16:00", earlierBookingDate, 182,
                0, 2);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Hair Styling", "30 minutes", "70", "0",87,0));
        booking.setServiceDetailsList(serviceDetailsList);
        bookingList.add(booking);

        booking = new Booking("Thanh Hao","013449879","13:00", earlierBookingDate, 190,
                0, 2);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Male Haircut", "30 - 40 minutes", "50", "0",35,0));
        serviceDetailsList.add(new ServiceDetail("Female Haircut", "30 - 45 minutes", "100", "0",61,0));
        serviceDetailsList.add(new ServiceDetail("Wash + Massage", "60 minutes", "42", "70",75,40));
        serviceDetailsList.add(new ServiceDetail("Hair Straightening", "60 - 120 minutes", "450", "0",23,0));
        serviceDetailsList.add(new ServiceDetail("Hair Loss Treatment", "60 - 120 minutes", "800", "1,200",6,30));
        booking.setServiceDetailsList(serviceDetailsList);
        bookingList.add(booking);

        booking = new Booking("Trung Tin","0943157985","12:00", earlierBookingDate, 175,
                0, 2);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Male Haircut", "30 - 40 minutes", "50", "0",35,0));
        serviceDetailsList.add(new ServiceDetail("Wash + Massage", "60 minutes", "42", "70",75,40));
        booking.setServiceDetailsList(serviceDetailsList);
        bookingList.add(booking);

        booking = new Booking("My Hanh","013479884","9:00", earlierBookingDate, 170,
                0, 2);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Female Haircut", "30 - 45 minutes", "100", "0",61,0));
        serviceDetailsList.add(new ServiceDetail("Wash + Massage", "60 minutes", "42", "70",75,40));
        serviceDetailsList.add(new ServiceDetail("Hair Curling", "30 - 60 minutes", "400", "0",21,0));
        serviceDetailsList.add(new ServiceDetail("Hair Loss Treatment", "60 - 120 minutes", "800", "1,200",6,30));
        booking.setServiceDetailsList(serviceDetailsList);
        bookingList.add(booking);

        booking = new Booking("Nhu Linh","013467964","19:00", evenEarlierBookingDate, 162,
                0, 2);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Male Haircut", "30 - 40 minutes", "50", "0",35,0));
        booking.setServiceDetailsList(serviceDetailsList);
        bookingList.add(booking);

        booking = new Booking("Thanh Tam","0132469752","15:00", evenEarlierBookingDate, 158,
                0, 2);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Female Haircut", "30 - 45 minutes", "100", "0",61,0));
        serviceDetailsList.add(new ServiceDetail("Wash + Massage", "60 minutes", "42", "70",75,40));
        serviceDetailsList.add(new ServiceDetail("Hair Curling", "30 - 60 minutes", "400", "0",21,0));
        serviceDetailsList.add(new ServiceDetail("Coloring (Long)", "60 - 90 minutes", "700", "0",45,0));
        booking.setServiceDetailsList(serviceDetailsList);
        bookingList.add(booking);

        booking = new Booking("Minh Hang","0134267941","11:00", evenEarlierBookingDate, 157,
                0, 2);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Wash + Massage", "60 minutes", "42", "70",75,40));
        serviceDetailsList.add(new ServiceDetail("Coloring (Long)", "60 - 90 minutes", "700", "0",45,0));
        serviceDetailsList.add(new ServiceDetail("Hair Styling", "30 minutes", "70", "0",87,0));
        booking.setServiceDetailsList(serviceDetailsList);
        bookingList.add(booking);

        booking = new Booking("Lan Tan","0613459422","10:00", evenEarlierBookingDate, 184,
                0, 2);
        serviceDetailsList = new ArrayList<>();
        serviceDetailsList.add(new ServiceDetail("Male Haircut", "30 - 40 minutes", "50", "0",35,0));
        booking.setServiceDetailsList(serviceDetailsList);
        bookingList.add(booking);
    }
}