package dev.hci.manager.recycleviewadapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import dev.hci.manager.R;

import java.util.ArrayList;

import dev.hci.manager.activities.ServiceActivity;
import dev.hci.manager.dtos.Service;

public class RecViewServiceTypeAdapter extends RecyclerView.Adapter<RecViewServiceTypeAdapter.ViewHolder> {

    private ArrayList<Service> servicesList;
    private Context context;
    private Activity activity;
    private Intent intent;

    public RecViewServiceTypeAdapter(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public void setServicesList(ArrayList<Service> servicesList) {
        this.servicesList = servicesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_service_type_card, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecViewServiceTypeAdapter.ViewHolder holder, int position) {
        holder.txtService.setText(servicesList.get(position).getServiceName());
        int serviceCount = servicesList.get(position).getServiceCount();
        if (serviceCount == 0) {
            holder.txtCount.setText("No available service");
        } else if (serviceCount == 1) {
            holder.txtCount.setText("1 available service");
        } else {
            holder.txtCount.setText(serviceCount + " available services");
        }
        holder.image.setImageResource(servicesList.get(position).getImageId());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, ServiceActivity.class);
                intent.putExtra("SERVICE", servicesList.get(position));
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return servicesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtService, txtCount;
        private ImageView image;
        private ConstraintLayout parent;

        public ViewHolder(View view) {
            super(view);
            txtService = view.findViewById(R.id.txtServiceType);
            txtCount = view.findViewById(R.id.txtServiceCount);
            image = view.findViewById(R.id.imgServiceType);
            parent = view.findViewById(R.id.layoutServiceType);
        }
    }
}
