package dev.hci.salonapp.recycleviewadapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dev.hci.salonapp.R;
import dev.hci.salonapp.activities.BookingCartActivity;
import dev.hci.salonapp.dtos.BookingHistory;
import dev.hci.salonapp.dtos.ServiceDetail;

public class RecViewBookingHistoryAdapter extends RecyclerView.Adapter<RecViewBookingHistoryAdapter.ViewHolder> {

    private ArrayList<BookingHistory> bookingHistoryList;
    private Context context;
    private Activity activity;

    public void setBookingHistoryList(ArrayList<BookingHistory> bookingHistoryList) {
        this.bookingHistoryList = bookingHistoryList;
    }

    public RecViewBookingHistoryAdapter(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_booking_history_card, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtSalonName.setText(bookingHistoryList.get(position).getSalonName());
        holder.txtSalonAddress.setText(bookingHistoryList.get(position).getSalonAddress());
        holder.txtDate.setText(bookingHistoryList.get(position).getDate());
        holder.txtTime.setText(bookingHistoryList.get(position).getTime());
        ArrayList<ServiceDetail> servicesList = bookingHistoryList.get(position).getServiceList();
        int total = 0;
        for (ServiceDetail serviceDetail : servicesList) {
            total += Integer.parseInt(serviceDetail.getPrice());
        }
        holder.txtTotal.setText(total + ".000d");
        if (servicesList.size() == 1) {
            holder.txtServiceCount.setText("1 Service Booked");
        } else {
            holder.txtServiceCount.setText(servicesList.size() + " Services Booked");
        }
        RecViewServiceBookedAdapter adapter = new RecViewServiceBookedAdapter(context, activity);
        adapter.setServiceDetailsList(servicesList);
        holder.recViewService.setAdapter(adapter);
        holder.recViewService.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL,false));
        holder.layout.setVisibility(View.GONE);
        holder.imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bookingHistoryList.get(position).isServiceViewed()) {
                    holder.imgView.setImageResource(R.drawable.ic_keyboard_arrow_down);
                    holder.layout.setVisibility(View.GONE);
                    bookingHistoryList.get(position).setServiceViewed(false);
                } else {
                    holder.imgView.setImageResource(R.drawable.ic_keyboard_arrow_up);
                    holder.layout.setVisibility(View.VISIBLE);
                    bookingHistoryList.get(position).setServiceViewed(true);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return bookingHistoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtSalonName, txtSalonAddress, txtDate, txtTime, txtTotal, txtServiceCount;
        private RecyclerView recViewService;
        private ImageView imgView;
        private ConstraintLayout layout;

        public ViewHolder(View view) {
            super(view);
            txtSalonName = view.findViewById(R.id.txtSalonNameHistory);
            txtSalonAddress = view.findViewById(R.id.txtSalonAddressHistory);
            txtDate = view.findViewById(R.id.txtSelectedDateHistory);
            txtTime = view.findViewById(R.id.txtSelectedTimeslotHistory);
            txtTotal = view.findViewById(R.id.txtServicePriceTotalHistory);
            txtServiceCount = view.findViewById(R.id.txtServiceCountHistory);
            imgView = view.findViewById(R.id.imgViewMoreService);
            recViewService = view.findViewById(R.id.recViewServiceBookedHistory);
            layout = view.findViewById(R.id.layoutServiceHistory);
        }
    }
}
