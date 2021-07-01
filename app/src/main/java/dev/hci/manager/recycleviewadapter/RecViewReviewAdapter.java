package dev.hci.manager.recycleviewadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dev.hci.manager.R;
import dev.hci.manager.dtos.Review;

public class RecViewReviewAdapter extends RecyclerView.Adapter<RecViewReviewAdapter.ViewHolder> {

    private ArrayList<Review> reviewList = new ArrayList<>();

    private Context context;

    public RecViewReviewAdapter(Context context) {
        this.context = context;
    }
    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_review_card, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.user.setText(reviewList.get(position).getUser());
        holder.time.setText(reviewList.get(position).getTime());
        holder.appointmentCode.setText(reviewList.get(position).getAppointmentCode() + "");
        holder.appointmentTime.setText(reviewList.get(position).getAppointmentTime());
        holder.appointmentDate.setText(reviewList.get(position).getAppointmentDate());
        holder.ratingBar.setRating(reviewList.get(position).getRating());
        holder.content.setText(reviewList.get(position).getContent());

    }

    public void setReviewList(ArrayList<Review> reviewList) {
        this.reviewList = reviewList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView user, time, appointmentDate, content, appointmentCode, appointmentTime;
        private RatingBar ratingBar;

        public ViewHolder(View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.txtReviewer);
            time = itemView.findViewById(R.id.txtReviewTime);
            content = itemView.findViewById(R.id.txtReview);
            appointmentTime = itemView.findViewById(R.id.txtReviewAppointmentTime);
            appointmentDate = itemView.findViewById(R.id.txtReviewAppointmentDate);
            appointmentCode = itemView.findViewById(R.id.txtReviewAppointmentCode);
            ratingBar = itemView.findViewById(R.id.reviewBar);

        }
    }
}
