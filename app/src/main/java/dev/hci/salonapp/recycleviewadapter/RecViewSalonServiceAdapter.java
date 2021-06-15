package dev.hci.salonapp.recycleviewadapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import dev.hci.salonapp.R;
import dev.hci.salonapp.dtos.Service;
import dev.hci.salonapp.dtos.ServiceDetail;

public class RecViewSalonServiceAdapter extends RecyclerView.Adapter<RecViewSalonServiceAdapter.ViewHolder> {

    private ArrayList<Service> serviceList;
    private Context context;
    private Activity activity;

    public RecViewSalonServiceAdapter(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_service_card, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public void setServiceList(ArrayList<Service> serviceList) {
        this.serviceList = serviceList;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtService.setText(serviceList.get(position).getService());
        holder.image.setImageResource(serviceList.get(position).getImageId());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TabLayout tabLayout = activity.findViewById(R.id.tabLayoutService);
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtService;
        private ImageView image;
        private CardView parent;

        public ViewHolder(View view) {
            super(view);
            txtService = view.findViewById(R.id.txtService);
            image = view.findViewById(R.id.imageService);
            parent = view.findViewById(R.id.serviceCard);
        }
    }

}
