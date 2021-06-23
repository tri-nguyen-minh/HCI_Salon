package dev.hci.salonapp.recycleviewadapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dev.hci.salonapp.R;
import dev.hci.salonapp.dtos.Product;

public class RecViewProductAdapter extends RecyclerView.Adapter<RecViewProductAdapter.ViewHolder> {

    private ArrayList<Product> productsList;
    private Context context;
    private Activity activity;
    private Intent intent;

    public void setProductsList(ArrayList<Product> productsList) {
        this.productsList = productsList;
    }

    public RecViewProductAdapter(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
        this.intent = activity.getIntent();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_product_card, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtProductName.setText(productsList.get(position).getName());
        holder.txtProductBrand.setText(productsList.get(position).getBrand());
        holder.txtPrice.setText(productsList.get(position).getPrice() + ".000d");
        holder.txtPrice.setTextColor(context.getResources().getColor(R.color.dark_gold));
        holder.imgView.setImageResource(productsList.get(position).getImageId());
        int discount = productsList.get(position).getDiscount();
        if (discount > 0) {
            holder.cardDiscount.setVisibility(View.VISIBLE);
            holder.txtDiscount.setText("-" + discount + "%");
            holder.txtOrgPrice.setVisibility(View.VISIBLE);
            holder.txtOrgPrice.setText(productsList.get(position).getOrgPrice() + ".000d");
            holder.txtOrgPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        } else {
            holder.txtOrgPrice.setVisibility(View.GONE);
            holder.cardDiscount.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtProductName, txtProductBrand, txtPrice, txtOrgPrice, txtDiscount;
        private CardView parent, cardDiscount;
        private ImageView imgView;

        public ViewHolder(View view) {
            super(view);
            txtProductName = view.findViewById(R.id.txtProductName);
            txtProductBrand = view.findViewById(R.id.txtProductBrand);
            txtPrice = view.findViewById(R.id.txtProductPrice);
            txtOrgPrice = view.findViewById(R.id.txtProductPriceOrg);
            txtDiscount = view.findViewById(R.id.txtProductDiscount);
            parent = view.findViewById(R.id.productCard);
            cardDiscount = view.findViewById(R.id.cardDiscount);
            imgView = view.findViewById(R.id.imgProduct);
        }
    }
}
