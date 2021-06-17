package dev.hci.salonapp.recycleviewadapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dev.hci.salonapp.R;
import dev.hci.salonapp.activities.BookingCartActivity;
import dev.hci.salonapp.dtos.ServiceDetail;

public class RecViewServiceBookedAdapter extends RecyclerView.Adapter<RecViewServiceBookedAdapter.ViewHolder> {

    private ArrayList<ServiceDetail> serviceDetailsList;
    private Context context;
    private Activity activity;
    private Intent intent;

    public void setServiceDetailsList(ArrayList<ServiceDetail> serviceDetailsList) {
        this.serviceDetailsList = serviceDetailsList;
    }

    public RecViewServiceBookedAdapter(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_service_booked_card, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtServiceName.setText(serviceDetailsList.get(position).getName());
        holder.txtDuration.setText(serviceDetailsList.get(position).getDuration());
        holder.txtPrice.setText(serviceDetailsList.get(position).getPrice() + ".000d");
        holder.imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serviceDetailsList.remove(position);
                RecyclerView recViewService = activity.findViewById(R.id.recViewServiceBooked);
                RecViewServiceBookedAdapter adapter = new RecViewServiceBookedAdapter(context, activity);
                adapter.setServiceDetailsList(serviceDetailsList);
                recViewService.setAdapter(adapter);
                recViewService.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL,false));
                if (serviceDetailsList.size() == 0) {
                    recViewService.setVisibility(View.GONE);
                    LinearLayout layoutNoService = activity.findViewById(R.id.layoutNoService);
                    layoutNoService.setVisibility(View.VISIBLE);
                }
                TextView txtTotal = activity.findViewById(R.id.txtServicePriceTotal);
                int total = 0;
                for (ServiceDetail serviceDetail : serviceDetailsList) {
                    total += Integer.parseInt(serviceDetail.getPrice());
                }
                txtTotal.setText(total + ".000d");
            }
        });
    }

    @Override
    public int getItemCount() {
        return serviceDetailsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtServiceName, txtDuration, txtPrice;
        private CardView parent;
        private ImageView imgView;

        public ViewHolder(View view) {
            super(view);
            txtServiceName = view.findViewById(R.id.txtServiceBookedName);
            txtDuration = view.findViewById(R.id.txtServiceBookedDuration);
            txtPrice = view.findViewById(R.id.txtServiceBookedPrice);
            imgView = view.findViewById(R.id.imgDeleteService);
            parent = view.findViewById(R.id.serviceBookedCard);
        }
    }
}
