package dev.hci.manager.recycleviewadapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import dev.hci.manager.R;

import java.text.DecimalFormat;
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
    private ServiceDetail serviceDetail;
    private int cardId;
    private DecimalFormat decimalFormat = new DecimalFormat("#.##");
    private int[] LAYOUT_LIST = {R.layout.recycle_view_service_card, R.layout.recycle_view_service_booked_card,
                            R.layout.recycle_view_discount_card, R.layout.recycle_view_discount_service_card,
                            R.layout.recycle_view_add_discount_service_card};

    public void setServiceDetailsList(ArrayList<ServiceDetail> serviceDetailsList) {
        this.serviceDetailsList = serviceDetailsList;
    }

    public RecViewServiceAdapter(Context context, Activity activity, int cardId) {
        this.context = context;
        this.activity = activity;
        this.cardId = cardId;
        intent = activity.getIntent();
        decimalFormat.setGroupingUsed(true);
        decimalFormat.setGroupingSize(3);
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
            holder.txtDuration.setText(serviceDetailsList.get(position).getDuration());
            holder.txtBookCount.setText(serviceDetailsList.get(position).getList().size() + "");
            holder.txtDiscountStatus.setText(serviceDetailsList.get(position).getBookCount() == 0 ? "Ended" : "Ongoing");
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
        }  else {
            holder.txtServiceName.setText(serviceDetailsList.get(position).getName());
            holder.txtDuration.setText(serviceDetailsList.get(position).getDuration());
            if (cardId == 4) {
                holder.txtPrice.setText(serviceDetailsList.get(position).getPrice() + ",000d");
                holder.txtPrice.setTextColor(context.getResources().getColor(R.color.black));
                holder.txtOrgPrice.setTextColor(context.getResources().getColor(R.color.black));
                holder.txtOrgPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                if(serviceDetailsList.get(position).getDiscount() == 0) {
                    holder.layoutDiscount.setVisibility(View.GONE);
                } else {
                    holder.txtPrice.setTextColor(context.getResources().getColor(R.color.red));
                    holder.layoutDiscount.setVisibility(View.VISIBLE);
                    holder.txtOrgPrice.setText(serviceDetailsList.get(position).getOrgPrice() + ",000d");
                    holder.txtDiscount.setText("-" + serviceDetailsList.get(position).getDiscount() + "%");
                }
                holder.imgStatusDiscountService.setImageResource(R.drawable.ic_check_unchecked);
                if (serviceDetailsList.get(position).isDiscounted()) {
                    holder.imgStatusDiscountService.setImageResource(R.drawable.ic_check_checked);
                }
                holder.imgStatusDiscountService.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!serviceDetailsList.get(position).isDiscounted()) {
                            holder.imgStatusDiscountService.setImageResource(R.drawable.ic_check_checked);
                            serviceDetailsList.get(position).setDiscounted(true);
                            serviceDetail = (ServiceDetail)intent.getSerializableExtra("DISCOUNT");
                            serviceDetail.getList().add(serviceDetailsList.get(position));
                            intent.putExtra("DISCOUNT", serviceDetail);
                            activity.setIntent(intent);
                        }
                    }
                });
            } else {
                if (serviceDetailsList.get(position).getOrgPrice().equals("0")) {
                    holder.txtPrice.setText(serviceDetailsList.get(position).getPrice() + ",000d");
                    holder.txtOrgPrice.setText(serviceDetailsList.get(position).getPrice() + ",000d");
                } else {
                    holder.txtPrice.setText(serviceDetailsList.get(position).getPrice() + ",000d");
                    holder.txtOrgPrice.setText(serviceDetailsList.get(position).getOrgPrice() + ",000d");
                }
                holder.txtOrgPrice.setTextColor(context.getResources().getColor(R.color.black));
                holder.txtPrice.setTextColor(context.getResources().getColor(R.color.red));

                intent = activity.getIntent();
                ServiceDetail discount = (ServiceDetail) intent.getSerializableExtra("DISCOUNT");
                if (discount.isDiscounted()) {
                    holder.linearLayoutDiscountValueService.setVisibility(View.GONE);
                } else {
                    holder.linearLayoutDiscountValueService.setVisibility(View.VISIBLE);
                    holder.editDiscountValue.setText(serviceDetailsList.get(position).getDiscount() + "");
                    holder.editDiscountValue.addTextChangedListener(new TextWatcher() {
                        String beforeString;
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            beforeString = s.toString();
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            int discountValue = 0, price = 0;

                            String value = s.toString();
                            if (value.length() > 1 && value.indexOf("0") == 0) {
                                holder.editDiscountValue.setText(value.substring(1));
                            }
                            discountValue = checkDiscountValue(s);
                            if(discountValue > 100) {
                                discountValue = 100;
                                holder.editDiscountValue.setText(discountValue + "");
                            }

                            serviceDetail = serviceDetailsList.get(position);
                            if (serviceDetail.getDiscount() > 0) {
                                price = Integer.parseInt(checkPriceString(serviceDetail.getOrgPrice())) * 10;
                            } else {
                                price = Integer.parseInt(checkPriceString(serviceDetail.getPrice())) * 10;
                            }
                            serviceDetail.setOrgPrice(decimalFormat.format(price  / 10));
                            holder.txtOrgPrice.setText(decimalFormat.format(price * 100) + "d");
                            price = (price / 100) * (100 - discountValue);
                            serviceDetail.setPrice(decimalFormat.format(price  / 10));
                            holder.txtPrice.setText(decimalFormat.format(price * 100) + "d");
                            serviceDetail.setDiscount(checkDiscountValue(s));
                            serviceDetailsList.set(position, serviceDetail);
                            Intent newIntent = new Intent();
                            discount.getList().set(position, serviceDetail);
                            newIntent.putExtra("DISCOUNT", discount);
                            activity.setIntent(newIntent);
                        }
                    });
                }
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
    }

    @Override
    public int getItemCount() {
        return serviceDetailsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtServiceName, txtDuration, txtPrice, txtOrgPrice, txtDiscount, txtBookCount;
        private TextView txtEditService, txtDeleteService, txtDiscountStatus;
        private EditText editDiscountValue;
        private LinearLayout parent, layoutDiscount, linearLayoutDiscountValueService;
        private ImageView imgDeleteDiscountService, imgStatusDiscountService;

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
            txtDiscountStatus = view.findViewById(R.id.txtDiscountStatus);
            editDiscountValue = view.findViewById(R.id.editDiscountValue);
            imgDeleteDiscountService = view.findViewById(R.id.imgDeleteDiscountService);
            imgStatusDiscountService = view.findViewById(R.id.imgStatusDiscountService);
            parent = view.findViewById(R.id.serviceDetailCard);
            layoutDiscount = view.findViewById(R.id.layoutDiscount);
            linearLayoutDiscountValueService = view.findViewById(R.id.linearLayoutDiscountValueService);
        }
    }

    private int checkDiscountValue(Editable e) {
        return e.toString().isEmpty() ? 0 : Integer.parseInt(e.toString());
    }

    private String checkPriceString(String string) {
        if (string.indexOf(",") >= 0) {
            String start = string.substring(0, string.indexOf(","));
            String end = string.substring(string.indexOf(",") + 1);
            string =  start + end;
        }
        return string;
    }
}
