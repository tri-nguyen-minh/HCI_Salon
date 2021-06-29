package dev.hci.manager.recycleviewadapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dev.hci.manager.R;
import dev.hci.manager.dtos.Notification;

public class RecViewNotificationAdapter extends RecyclerView.Adapter<RecViewNotificationAdapter.ViewHolder>{

    private ArrayList<Notification> notificationsList;
    private final String[] contentTypeList = {"booked a new Appointment",
                                            "rescheduled the Appointment",
                                            "wrote a review from the Appointment"};
    private Context context;
    private Activity activity;

    public RecViewNotificationAdapter(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public void setNotificationsList(ArrayList<Notification> notificationsList) {
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
        String content = user + " " + contentTypeList[contentType] + " (" + appointment + ")";
        switch (contentType) {
            case 0:
                content += " on " + appointmentTime;
            case 1:
                content += " to " + appointmentTime;
        }
        holder.txtContent.setText(content);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (contentType) {
                    case 0: {
                        System.out.printf("0");
                    }
                    case 1: {
                        System.out.printf("1");
                    }
                    case 2: {
                        System.out.println(2);
                    }
                }
            }
        });
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
