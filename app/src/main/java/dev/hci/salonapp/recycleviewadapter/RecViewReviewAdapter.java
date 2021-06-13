package dev.hci.salonapp.recycleviewadapter;
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

import dev.hci.salonapp.R;
import dev.hci.salonapp.dtos.Review;

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
        holder.txtName.setText(reviewList.get(position).getUser());
        holder.txtReview.setText(reviewList.get(position).getReview());
        holder.txtTime.setText(reviewList.get(position).getTime());
        holder.txtLike.setText(reviewList.get(position).getLike() + "");
        holder.ratingBar.setRating(reviewList.get(position).getRating());
        if (!reviewList.get(position).isOwnReview()) {
            if (reviewList.get(position).isLiked()) {
                holder.imgLike.setImageResource(R.drawable.ic_like_selected);
                holder.layoutLike.setBackground(context.getResources().getDrawable(R.drawable.background_like_selected));
                holder.txtLike.setTextColor(context.getResources().getColor(R.color.gold));
            } else {
                holder.imgLike.setImageResource(R.drawable.ic_like_default);
                holder.layoutLike.setBackground(context.getResources().getDrawable(R.drawable.background_like));
                holder.txtLike.setTextColor(context.getResources().getColor(R.color.light_gray));
            }
            holder.layoutLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (reviewList.get(position).isLiked()) {
                        reviewList.get(position).setLike(reviewList.get(position).getLike() - 1);
                        holder.imgLike.setImageResource(R.drawable.ic_like_default);
                        holder.layoutLike.setBackground(context.getResources().getDrawable(R.drawable.background_like));
                        holder.txtLike.setTextColor(context.getResources().getColor(R.color.light_gray));
                        reviewList.get(position).setLiked(false);
                        holder.txtLike.setText(reviewList.get(position).getLike() + "");
                    } else {
                        reviewList.get(position).setLike(reviewList.get(position).getLike() + 1);
                        holder.imgLike.setImageResource(R.drawable.ic_like_selected);
                        holder.layoutLike.setBackground(context.getResources().getDrawable(R.drawable.background_like_selected));
                        holder.txtLike.setTextColor(context.getResources().getColor(R.color.gold));
                        reviewList.get(position).setLiked(true);
                        holder.txtLike.setText(reviewList.get(position).getLike() + "");
                    }
                }
            });
        } else {
            holder.layoutLike.setVisibility(View.GONE);
        }
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
        private TextView txtName, txtReview, txtTime, txtLike;
        private LinearLayout layoutLike;
        private  RatingBar ratingBar;
        private ImageView imgLike;

        public ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtReviewer);
            txtTime = itemView.findViewById(R.id.txtReviewTime);
            txtReview = itemView.findViewById(R.id.txtReview);
            txtLike = itemView.findViewById(R.id.txtLike);
            layoutLike = itemView.findViewById(R.id.layoutLike);
            ratingBar = itemView.findViewById(R.id.reviewBar);
            imgLike = itemView.findViewById(R.id.imgLike);

        }
    }
}