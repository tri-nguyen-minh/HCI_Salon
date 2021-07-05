package dev.hci.manager.recycleviewadapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import dev.hci.manager.R;

import java.util.ArrayList;

import dev.hci.manager.activities.EditDiscountActivity;
import dev.hci.manager.activities.EditServiceActivity;
import dev.hci.manager.activities.ServiceActivity;
import dev.hci.manager.dtos.Service;
import dev.hci.manager.dtos.ServiceDetail;

public class RecViewServiceAdapter extends RecyclerView.Adapter<RecViewServiceAdapter.ViewHolder> {

    private ArrayList<ServiceDetail> serviceDetailsList;
    private Service service;
    private Intent intent;
    private Context context;
    private Activity activity;
    private int cardId;
    private int[] LAYOUT_LIST = {R.layout.recycle_view_service_card, R.layout.recycle_view_service_booked_card,
                            R.layout.recycle_view_discount_card, R.layout.recycle_view_discount_service_card};

    public void setServiceDetailsList(ArrayList<ServiceDetail> serviceDetailsList) {
        this.serviceDetailsList = serviceDetailsList;
    }

    public RecViewServiceAdapter(Context context, Activity activity, int cardId) {
        this.context = context;
        this.activity = activity;
        this.cardId = cardId;
        intent = activity.getIntent();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(LAYOUT_LIST[cardId], parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(RecViewServiceAdapter.ViewHolder holder, int position) {
        if (cardId == 0) {
            holder.txtServiceName.setText(serviceDetailsList.get(position).getName());
            holder.txtDuration.setText(serviceDetailsList.get(position).getDuration());
            holder.txtPrice.setText(serviceDetailsList.get(position).getPrice() + ",000d");
            holder.txtPrice.setTextColor(context.getResources().getColor(R.color.black));
            holder.txtOrgPrice.setTextColor(context.getResources().getColor(R.color.black));
            if(serviceDetailsList.get(position).getDiscount() == 0) {
                holder.layoutDiscount.setVisibility(View.GONE);
            } else {
                holder.txtPrice.setTextColor(context.getResources().getColor(R.color.red));
                holder.layoutDiscount.setVisibility(View.VISIBLE);
                holder.txtOrgPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                holder.txtOrgPrice.setText(serviceDetailsList.get(position).getOrgPrice() + ",000d");
                holder.txtDiscount.setText("-" + serviceDetailsList.get(position).getDiscount() + "%");
            }
            holder.txtBookCount.setText(serviceDetailsList.get(position).getBookCount() + "");
            service = (Service)intent.getSerializableExtra("SERVICE");
            holder.txtEditService.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent = new Intent(context, EditServiceActivity.class);
                    intent.putExtra("SERVICE", service);
                    intent.putExtra("POSITION", position);
                    activity.startActivity(intent);
                }
            });
            holder.txtDeleteService.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setCancelable(true);
                    builder.setTitle("Remove Service");
                    builder.setMessage("Do you want to remove this service?");
                    builder.setPositiveButton("Confirm",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    serviceDetailsList.remove(position);
//                                    service.getServiceDetailsList().remove(position);
//
                                    RecyclerView recViewService = activity.findViewById(R.id.recViewService);
                                    RecViewServiceAdapter adapter = new RecViewServiceAdapter(context, activity,0);
                                    adapter.setServiceDetailsList(serviceDetailsList);
                                    recViewService.setAdapter(adapter);
                                    recViewService.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL,false));

                                }
                            });
                    builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
        } else if (cardId == 1) {
            holder.txtServiceName.setText(serviceDetailsList.get(position).getName());
            holder.txtDuration.setText(serviceDetailsList.get(position).getDuration());
            holder.txtPrice.setText(serviceDetailsList.get(position).getPrice() + ",000d");
            holder.txtPrice.setTextColor(context.getResources().getColor(R.color.black));
            holder.txtOrgPrice.setTextColor(context.getResources().getColor(R.color.black));
            if(serviceDetailsList.get(position).getDiscount() == 0) {
                holder.layoutDiscount.setVisibility(View.GONE);
            } else {
                holder.txtPrice.setTextColor(context.getResources().getColor(R.color.red));
                holder.layoutDiscount.setVisibility(View.VISIBLE);
                holder.txtOrgPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                holder.txtOrgPrice.setText(serviceDetailsList.get(position).getOrgPrice() + ",000d");
                holder.txtDiscount.setText("-" + serviceDetailsList.get(position).getDiscount() + "%");
            }
        } else if (cardId == 2) {
            holder.txtServiceName.setText(serviceDetailsList.get(position).getName());
            holder.txtPrice.setText(serviceDetailsList.get(position).getDiscount() + "%");
            holder.txtBookCount.setText(serviceDetailsList.get(position).getList().size() + "");

            holder.txtEditService.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent = new Intent(context, EditDiscountActivity.class);
                    intent.putExtra("DISCOUNT", serviceDetailsList.get(position));
                    intent.putExtra("POSITION", position);
                    activity.startActivity(intent);
                }
            });
            holder.txtDeleteService.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setCancelable(true);
                    builder.setTitle("Remove Discount");
                    builder.setMessage("Do you want to remove this Discount?");
                    builder.setPositiveButton("Confirm",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    serviceDetailsList.remove(position);
                                    RecyclerView recViewService = activity.findViewById(R.id.recViewDiscount);
                                    RecViewServiceAdapter adapter = new RecViewServiceAdapter(context, activity,2);
                                    adapter.setServiceDetailsList(serviceDetailsList);
                                    recViewService.setAdapter(adapter);
                                    recViewService.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL,false));

                                }
                            });
                    builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
        } else {
            holder.txtServiceName.setText(serviceDetailsList.get(position).getName());
            holder.txtDuration.setText(serviceDetailsList.get(position).getDuration());
            holder.txtPrice.setText(serviceDetailsList.get(position).getPrice() + ",000d");
            holder.txtPrice.setTextColor(context.getResources().getColor(R.color.black));
            holder.txtOrgPrice.setTextColor(context.getResources().getColor(R.color.black));
            holder.txtPrice.setTextColor(context.getResources().getColor(R.color.red));
            holder.txtOrgPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            holder.txtOrgPrice.setText(serviceDetailsList.get(position).getOrgPrice() + ",000d");
            holder.imgDeleteDiscountService.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setCancelable(true);
                    builder.setTitle("Remove Service");
                    builder.setMessage("Do you want to remove this service?");
                    builder.setPositiveButton("Confirm",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    serviceDetailsList.remove(position);
                                    RecyclerView recViewService = activity.findViewById(R.id.recViewDiscountService);
                                    RecViewServiceAdapter adapter = new RecViewServiceAdapter(context, activity,3);
                                    adapter.setServiceDetailsList(serviceDetailsList);
                                    recViewService.setAdapter(adapter);
                                    recViewService.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL,false));

                                }
                            });
                    builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return serviceDetailsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtServiceName, txtDuration, txtPrice, txtOrgPrice, txtDiscount, txtBookCount;
        private TextView txtEditService, txtDeleteService;
        private LinearLayout parent, layoutDiscount;
        private ImageView imgDeleteDiscountService;

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
            imgDeleteDiscountService = view.findViewById(R.id.imgDeleteDiscountService);
//            imgDeleteService = view.findViewById(R.id.imgDeleteService);
            parent = view.findViewById(R.id.serviceDetailCard);
            layoutDiscount = view.findViewById(R.id.layoutDiscount);
        }
    }
}
