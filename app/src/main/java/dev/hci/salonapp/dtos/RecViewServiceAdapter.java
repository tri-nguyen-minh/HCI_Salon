package dev.hci.salonapp.dtos;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dev.hci.salonapp.R;

public class RecViewServiceAdapter  extends RecyclerView.Adapter<RecViewServiceAdapter.ViewHolder> {

    private ArrayList<Service> serviceList;
    private Context context;
    private Activity activity;

    public RecViewServiceAdapter(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    public void setServiceList(ArrayList<Service> serviceList) {
        this.serviceList = serviceList;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtService.setText(serviceList.get(position).getService());
        holder.image.setImageResource(serviceList.get(position).getImageId());
    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtService;
        private ImageView image;

        public ViewHolder(View view) {
            super(view);
            txtService = view.findViewById(R.id.txtService);
            image = view.findViewById(R.id.imageService);
        }
    }
}
