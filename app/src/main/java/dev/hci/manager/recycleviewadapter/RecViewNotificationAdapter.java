package dev.hci.manager.recycleviewadapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import dev.hci.manager.R;
import dev.hci.manager.dtos.Booking;

public class RecViewNotificationAdapter extends RecyclerView.Adapter<RecViewNotificationAdapter.ViewHolder>{

    private ArrayList<Booking> notificationsList;
    private final String[] contentTypeList = {"booked a new Appointment",
                                            "rescheduled the Appointment",
                                            "cancelled the Appointment",
                                            "wrote a review from the Appointment"};
    private Context context;
    private Activity activity;

    public RecViewNotificationAdapter(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public void setNotificationsList(ArrayList<Booking> notificationsList) {
        this.notificationsList = notificationsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_notification_card, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecViewNotificationAdapter.ViewHolder holder, int position) {
        holder.txtTime.setText(notificationsList.get(position).getTime());
        holder.txtReadTag.setVisibility(notificationsList.get(position).isRead() ? View.GONE : View.VISIBLE);
        int contentType = notificationsList.get(position).getContentTypeCode();
        String user = notificationsList.get(position).getUser();
        int appointment = notificationsList.get(position).getAppointmentCode();
        String appointmentTime = notificationsList.get(position).getAppointmentTime();
        String appointmentDate = notificationsList.get(position).getAppointmentDate();
        String content = user + " " + contentTypeList[contentType] + " (" + appointment + ")";
        switch (contentType) {
            case 1: {
                content += " to " + appointmentTime + " on " + appointmentDate;
                break;
            }
            default: {
                content += " at " + appointmentTime + " on " + appointmentDate;
                break;
            }
        }
        holder.txtContent.setText(content);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        holder.parent.setBackground(activity.getDrawable(R.drawable.background_separator_bottom));
        if (notificationsList.get(position).isRead()) {
            holder.parent.setBackground(activity.getDrawable(R.drawable.background_separator_bottom_gray));
        }
    }

    @Override
    public int getItemCount() {
        return notificationsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtContent, txtTime, txtReadTag;
        private ConstraintLayout parent;

        public ViewHolder(View view) {
            super(view);
            txtContent = view.findViewById(R.id.txtNotificationContent);
            txtTime = view.findViewById(R.id.txtNotificationTime);
            txtReadTag = view.findViewById(R.id.txtNotificationReadTag);
            parent = view.findViewById(R.id.notificationCard);
        }
    }
}
