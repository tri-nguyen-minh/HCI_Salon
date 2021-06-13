package dev.hci.salonapp.recycleviewadapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dev.hci.salonapp.R;
import dev.hci.salonapp.dtos.Salon;

public class RecViewSalonAdapter extends RecyclerView.Adapter<RecViewSalonAdapter.ViewHolder>{

    private Context context;
    private Activity activity;
    private ArrayList<Salon> salonList;
    private int layoutId;

    public RecViewSalonAdapter(Context context, Activity activity, int layoutId) {
        this.context = context;
        this.activity = activity;
        this.layoutId = layoutId;
    }

    public void setSalonList(ArrayList<Salon> salonList) {
        this.salonList = salonList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtSalonName.setText(salonList.get(position).getName());
        holder.txtAddress.setText(salonList.get(position).getAddress());
        holder.txtDistance.setText(salonList.get(position).getDistance() + "km");
        int discount = salonList.get(position).getDiscount();
        if (discount == 0) {
            holder.txtDiscount.setVisibility(View.GONE);
        } else {
            holder.txtDiscount.setVisibility(View.VISIBLE);
            holder.txtDiscount.setText("-" + discount + "%");
        }
        holder.txtReviewCount.setText("(" + salonList.get(position).getReviewCount() + ")");
        holder.rating.setRating(salonList.get(position).getRating());
        holder.image.setImageResource(salonList.get(position).getImageId());

    }

    @Override
    public int getItemCount() {
        return salonList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtSalonName, txtAddress, txtDistance, txtDiscount, txtReviewCount;
        private RatingBar rating;
        private ImageView image;

        public ViewHolder(View view) {
            super(view);
            txtSalonName = view.findViewById(R.id.txtSalonName);
            txtAddress = view.findViewById(R.id.txtSalonAddress);
            txtDistance = view.findViewById(R.id.txtSalonDistance);
            txtDiscount = view.findViewById(R.id.txtDiscount);
            txtReviewCount = view.findViewById(R.id.reviewCount);
            rating = view.findViewById(R.id.ratingSalon);
            image = view.findViewById(R.id.imgSalon);
        }
    }
}
