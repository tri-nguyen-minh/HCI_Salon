package dev.hci.manager.recycleviewadapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hci_salon_manager.R;

import java.util.ArrayList;

import dev.hci.manager.dtos.ServiceDetail;

public class RecViewServiceAdapter extends RecyclerView.Adapter<RecViewServiceAdapter.ViewHolder> {

    private ArrayList<ServiceDetail> serviceDetailsList;
    private Context context;
    private Activity activity;

    public void setServiceDetailsList(ArrayList<ServiceDetail> serviceDetailsList) {
        this.serviceDetailsList = serviceDetailsList;
    }

    public RecViewServiceAdapter(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_service_card, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(RecViewServiceAdapter.ViewHolder holder, int position) {
        holder.txtServiceName.setText(serviceDetailsList.get(position).getName());
        holder.txtDuration.setText(serviceDetailsList.get(position).getDuration());
        holder.txtPrice.setText(serviceDetailsList.get(position).getPrice() + ".000d");
        holder.txtPrice.setTextColor(context.getResources().getColor(R.color.black));
        holder.txtOrgPrice.setTextColor(context.getResources().getColor(R.color.black));
        if(serviceDetailsList.get(position).getDiscount() == 0) {
            holder.layoutDiscount.setVisibility(View.GONE);
        } else {
            holder.txtPrice.setTextColor(context.getResources().getColor(R.color.red));
            holder.layoutDiscount.setVisibility(View.VISIBLE);
            holder.txtOrgPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            holder.txtOrgPrice.setText(serviceDetailsList.get(position).getOrgPrice() + ".000d");
            holder.txtDiscount.setText("-" + serviceDetailsList.get(position).getDiscount() + "%");
        }
        holder.txtBookCount.setText(serviceDetailsList.get(position).getBookCount() + "");
        holder.txtEditService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Edit");
            }
        });
        holder.txtDeleteService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Delete");
            }
        });
    }

    @Override
    public int getItemCount() {
        return serviceDetailsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtServiceName, txtDuration, txtPrice, txtOrgPrice, txtDiscount, txtBookCount;
        private TextView txtEditService, txtDeleteService;
        private CardView parent;
        private LinearLayout layoutDiscount;

        public ViewHolder(View view) {
            super(view);
            txtServiceName = view.findViewById(R.id.txtServiceName);
            txtDuration = view.findViewById(R.id.txtDuration);
            txtPrice = view.findViewById(R.id.txtServicePrice);
            txtOrgPrice = view.findViewById(R.id.txtServiceOrgPrice);
            txtDiscount = view.findViewById(R.id.txtServiceDiscount);
            txtBookCount = view.findViewById(R.id.txtServiceBookCount);
            txtEditService = view.findViewById(R.id.txtEditService);
            txtDeleteService = view.findViewById(R.id.txtDeleteService);
            parent = view.findViewById(R.id.serviceDetailCard);
            layoutDiscount = view.findViewById(R.id.layoutDiscount);
        }
    }
}
