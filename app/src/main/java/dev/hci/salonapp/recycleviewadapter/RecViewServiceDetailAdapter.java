package dev.hci.salonapp.recycleviewadapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dev.hci.salonapp.MainActivity;
import dev.hci.salonapp.R;
import dev.hci.salonapp.dtos.ServiceDetail;

public class RecViewServiceDetailAdapter extends RecyclerView.Adapter<RecViewServiceDetailAdapter.ViewHolder> {

    private ArrayList<ServiceDetail> serviceDetailsList;
    private Context context;
    private Activity activity;
    private Intent intent;

    public void setServiceDetailsList(ArrayList<ServiceDetail> serviceDetailsList) {
        this.serviceDetailsList = serviceDetailsList;
    }

    public RecViewServiceDetailAdapter(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_service_detail_card, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtCartStatus.setVisibility(View.GONE);
        holder.imgView.setVisibility(View.VISIBLE);
        holder.txtServiceName.setText(serviceDetailsList.get(position).getName());
        holder.txtDuration.setText(serviceDetailsList.get(position).getDuration());
        holder.txtPrice.setText(serviceDetailsList.get(position).getPrice() + ".000d");
        if(serviceDetailsList.get(position).getOrgPrice().equals("0")) {
            holder.txtOrgPrice.setVisibility(View.GONE);
        } else {
            holder.txtOrgPrice.setVisibility(View.VISIBLE);
            holder.txtOrgPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            holder.txtOrgPrice.setText(serviceDetailsList.get(position).getOrgPrice() + ".000d");
        }
        intent = activity.getIntent();
        holder.imgView.setImageResource(R.drawable.ic_add);
        holder.imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!intent.getBooleanExtra("logged", false)) {
                    intent = new Intent(context, MainActivity.class);
                    intent.putExtra("forced_logged", true);
                    activity.startActivity(intent);
                } else {
                    int cartCount = intent.getIntExtra("cartCount", 0) + 1;
                    intent.putExtra("cartCount", cartCount);
                    activity.setIntent(intent);
                    LinearLayout layoutServiceCart = activity.findViewById(R.id.layoutServiceCart);
                    layoutServiceCart.setVisibility(View.VISIBLE);
                    ((TextView)activity.findViewById(R.id.txtCartCount)).setText(cartCount + "");
                    holder.imgView.setVisibility(View.GONE);
                    holder.txtCartStatus.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return serviceDetailsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtServiceName, txtDuration, txtPrice, txtOrgPrice, txtCartStatus;
        private CardView parent;
        private ImageView imgView;

        public ViewHolder(View view) {
            super(view);
            txtServiceName = view.findViewById(R.id.txtServiceName);
            txtDuration = view.findViewById(R.id.txtDuration);
            txtPrice = view.findViewById(R.id.txtServicePrice);
            txtOrgPrice = view.findViewById(R.id.txtServiceOrgPrice);
            txtCartStatus = view.findViewById(R.id.txtCartStatus);
            imgView = view.findViewById(R.id.imgAddService);
            parent = view.findViewById(R.id.serviceCard);
        }
    }
}