package dev.hci.manager.recycleviewadapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dev.hci.manager.R;
import dev.hci.manager.dtos.Booking;
import dev.hci.manager.dtos.ServiceDetail;

public class RecViewBookingAdapter  extends RecyclerView.Adapter<RecViewBookingAdapter.ViewHolder> {

    private ArrayList<Booking> bookingList;
    private Context context;
    private Activity activity;
    private Intent intent;
    private String[] statusNoteList = {"Appointment cancelled because Customer did not arrive",
    "Appointment completion confirmed",
    "Appointment cancelled by Staff"};

    public RecViewBookingAdapter(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public void setBookingList(ArrayList<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_booking_card, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (bookingList.get(position).getActionCode() == 0) {
            if (bookingList.get(position).isFinished()) {
                holder.cardBookingNotFinished.setVisibility(View.VISIBLE);
                holder.cardBookingFinished.setVisibility(View.VISIBLE);
                holder.cardBookingCancel.setVisibility(View.GONE);
                holder.txtBookingStatus.setVisibility(View.VISIBLE);
                holder.txtBookingStatusNote.setVisibility(View.GONE);
            } else {
                holder.cardBookingNotFinished.setVisibility(View.GONE);
                holder.cardBookingFinished.setVisibility(View.GONE);
                holder.cardBookingCancel.setVisibility(View.VISIBLE);
                holder.txtBookingStatus.setVisibility(View.GONE);
                holder.txtBookingStatusNote.setVisibility(View.GONE);
            }
        } else {
            holder.cardBookingNotFinished.setVisibility(View.GONE);
            holder.cardBookingFinished.setVisibility(View.GONE);
            holder.cardBookingCancel.setVisibility(View.GONE);
            holder.txtBookingStatusNote.setVisibility(View.VISIBLE);
            holder.txtBookingStatusNote.setText(statusNoteList[bookingList.get(position).getActionCode() - 1]);
        }
        holder.txtUserName.setText(bookingList.get(position).getUser());
        holder.txtPhone.setText(bookingList.get(position).getPhone());
        holder.txtAppointmentCode.setText(bookingList.get(position).getAppointmentCode() + "");
        holder.txtBookingDate.setText(bookingList.get(position).getAppointmentDate());
        holder.txtBookingTime.setText(bookingList.get(position).getAppointmentTime());
        int total = 0, count = 0, remain = 0;
        for (ServiceDetail service : bookingList.get(position).getServiceDetailsList()) {
            total += Integer.parseInt(service.getPrice());
            count++;
        }
        if (count == 1) {
            holder.txtBookingCount.setText("1 Service booked");
        } else {
            holder.txtBookingCount.setText(count + " Services booked");
        }
        if (total >= 1000) {
            remain = total % 1000;
            total /= 1000;
        }
        holder.txtBookingTotalPrice.setText(total + ((remain == 0) ? "" : ("." + remain)) + ".000d");
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtUserName, txtPhone, txtAppointmentCode, txtBookingDate, txtBookingTime,
                txtBookingCount, txtBookingTotalPrice, txtBookingStatus, txtBookingStatusNote;
        private CardView parent, cardBookingNotFinished, cardBookingFinished, cardBookingCancel;

        public ViewHolder(View view) {
            super(view);
            txtUserName = view.findViewById(R.id.txtUserName);
            txtPhone = view.findViewById(R.id.txtUserPhone);
            txtAppointmentCode = view.findViewById(R.id.txtAppointmentCode);
            txtBookingDate = view.findViewById(R.id.txtBookingDate);
            txtBookingTime = view.findViewById(R.id.txtBookingTime);
            txtBookingCount = view.findViewById(R.id.txtBookingCount);
            txtBookingTotalPrice = view.findViewById(R.id.txtBookingTotalPrice);
            txtBookingStatus = view.findViewById(R.id.txtBookingStatus);
            txtBookingStatusNote = view.findViewById(R.id.txtBookingStatusNote);
            cardBookingNotFinished = view.findViewById(R.id.cardBookingNotFinished);
            cardBookingFinished = view.findViewById(R.id.cardBookingFinished);
            cardBookingCancel = view.findViewById(R.id.cardBookingCancel);
            parent = view.findViewById(R.id.bookingCard);

        }
    }
}
