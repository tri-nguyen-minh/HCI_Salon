package dev.hci.salonapp.recycleviewadapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import dev.hci.salonapp.activities.MainActivity;
import dev.hci.salonapp.dtos.BookingHistory;
import dev.hci.salonapp.dtos.Salon;
import dev.hci.salonapp.dtos.ServiceDetail;

public class RecViewBookingHistoryAdapter extends RecyclerView.Adapter<RecViewBookingHistoryAdapter.ViewHolder> {

    private ArrayList<BookingHistory> bookingHistoryList;
    private Context context;
    private Activity activity;
    private int emptyTextId, recViewId;

    public void setBookingHistoryList(ArrayList<BookingHistory> bookingHistoryList) {
        this.bookingHistoryList = bookingHistoryList;
    }

    public RecViewBookingHistoryAdapter(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public void setEmptyTextId(int emptyTextId) {
        this.emptyTextId = emptyTextId;
    }

    public void setRecViewId(int recViewId) {
        this.recViewId = recViewId;
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
        adapter.setConfirmed(true);
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
        if (bookingHistoryList.get(position).isPassed()) {
            holder.layoutWriteReview.setVisibility(View.VISIBLE);
            holder.txtReschedule.setVisibility(View.GONE);
            holder.txtCancel.setVisibility(View.GONE);
        } else if (bookingHistoryList.get(position).isCancelled()) {
            holder.layoutWriteReview.setVisibility(View.GONE);
            holder.txtReschedule.setVisibility(View.VISIBLE);
            holder.txtCancel.setVisibility(View.GONE);
        } else {
            holder.layoutWriteReview.setVisibility(View.GONE);
            holder.txtReschedule.setVisibility(View.VISIBLE);
            holder.txtReschedule.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Salon salon = new Salon();
                    salon.setName(bookingHistoryList.get(position).getSalonName());
                    salon.setAddress(bookingHistoryList.get(position).getSalonAddress());
                    Intent newIntent = new Intent(context, BookingCartActivity.class);
                    newIntent.putExtra("salon", salon);
                    activity.startActivity(newIntent);
                }
            });
            holder.txtCancel.setVisibility(View.VISIBLE);
            holder.txtCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setCancelable(true);
                    builder.setTitle("Cancel the Appointment?");
                    builder.setMessage("Are you sure you want to cancel this appointment?");
                    builder.setPositiveButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    bookingHistoryList.remove(position);
                                    RecyclerView recViewBookingHistoryUpcoming = activity.findViewById(recViewId);
                                    RecViewBookingHistoryAdapter historyAdapter = new RecViewBookingHistoryAdapter(context, activity);
                                    historyAdapter.setEmptyTextId(emptyTextId);
                                    historyAdapter.setRecViewId(recViewId);
                                    historyAdapter.setBookingHistoryList(bookingHistoryList);
                                    recViewBookingHistoryUpcoming.setAdapter(historyAdapter);
                                    recViewBookingHistoryUpcoming.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL,false));
                                    setEmptyText();
                                }
                            });
                    builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
        }
        setEmptyText();
    }

    private void setEmptyText() {
        TextView textView = activity.findViewById(emptyTextId);
        if (bookingHistoryList.size() == 0) {
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return bookingHistoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtSalonName, txtSalonAddress, txtDate, txtTime, txtTotal, txtServiceCount,
                        txtReschedule, txtCancel;
        private RecyclerView recViewService;
        private ImageView imgView;
        private ConstraintLayout layout;
        private LinearLayout layoutWriteReview;

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
            txtReschedule = view.findViewById(R.id.txtRescheduleBookingHistory);
            txtCancel = view.findViewById(R.id.txtCancelBookingHistory);
            layoutWriteReview = view.findViewById(R.id.layoutWriteReview);
        }
    }
}
