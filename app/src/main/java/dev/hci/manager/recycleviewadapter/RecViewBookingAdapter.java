package dev.hci.manager.recycleviewadapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import dev.hci.manager.R;
import dev.hci.manager.activities.BookingViewActivity;
import dev.hci.manager.activities.HomeActivity;
import dev.hci.manager.dtos.Booking;
import dev.hci.manager.dtos.ServiceDetail;

public class RecViewBookingAdapter  extends RecyclerView.Adapter<RecViewBookingAdapter.ViewHolder> {

    private ArrayList<Booking> bookingList;
    private Context context;
    private Activity activity;
    private Intent intent;
    private String pageIdentifier;
    private int recViewId, orientationId, layoutId;
    private RecyclerView recViewCommon;

    private String[] STATUS_NOTE_LIST = {"Appointment cancelled because Customer did not arrive",
                                        "Appointment completion confirmed",
                                        "Appointment cancelled by Staff",
                                        "Appointment cancelled by Customer"};

    public RecViewBookingAdapter(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public void setBookingList(ArrayList<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    public void setRecViewId(int recViewId) {
        this.recViewId = recViewId;
    }

    public void setOrientationId(int orientationId) {
        this.orientationId = orientationId;
    }

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    public void setPageIdentifier(String pageIdentifier) {
        this.pageIdentifier = pageIdentifier;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (layoutId == R.layout.recycle_view_booking_card) {
            if (bookingList.get(position).getActionCode() == 0) {
                if (bookingList.get(position).isFinished()) {
                    holder.cardBookingNotFinished.setVisibility(View.VISIBLE);
                    holder.cardBookingFinished.setVisibility(View.VISIBLE);
                    holder.cardBookingCancel.setVisibility(View.GONE);
                    holder.txtBookingStatus.setVisibility(View.VISIBLE);
                } else {
                    holder.cardBookingNotFinished.setVisibility(View.GONE);
                    holder.cardBookingFinished.setVisibility(View.GONE);
                    holder.cardBookingCancel.setVisibility(View.VISIBLE);
                    holder.txtBookingStatus.setVisibility(View.GONE);
                }
                holder.txtBookingStatusNote.setVisibility(View.GONE);
            } else {
                holder.cardBookingNotFinished.setVisibility(View.GONE);
                holder.cardBookingFinished.setVisibility(View.GONE);
                holder.cardBookingCancel.setVisibility(View.GONE);
                holder.txtBookingStatusNote.setVisibility(View.VISIBLE);
                holder.txtBookingStatusNote.setText(STATUS_NOTE_LIST[bookingList.get(position).getActionCode() - 1]);
            }
            holder.txtUserName.setText(bookingList.get(position).getUser());
            holder.txtPhone.setText(bookingList.get(position).getPhone());
            holder.txtAppointmentCode.setText(bookingList.get(position).getAppointmentCode() + "");
            holder.txtBookingDate.setText(bookingList.get(position).getAppointmentDate());
            holder.txtBookingTime.setText(bookingList.get(position).getAppointmentTime());
            int total = 0, count = 0, remain = 0;
            for (ServiceDetail service : bookingList.get(position).getServiceDetailsList()) {
                total += Integer.parseInt(service.getPrice());
                count++;
            }
            if (count == 1) {
                holder.txtBookingCount.setText("1 Service booked");
            } else {
                holder.txtBookingCount.setText(count + " Services booked");
            }
            if (total >= 1000) {
                remain = total % 1000;
                total /= 1000;
            }
            holder.txtBookingTotalPrice.setText(total + ((remain == 0) ? "" : ("," + remain)) + ",000d");

            holder.cardBookingFinished.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setCancelable(true);
                    builder.setTitle("Completion Confirmation");
                    builder.setMessage("Confirm Appointment has been completed?");
                    builder.setPositiveButton("Confirm",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    bookingList.get(position).setActionCode(2);
                                    recViewCommon = activity.findViewById(recViewId);
                                    RecViewBookingAdapter adapter = new RecViewBookingAdapter(context, activity);
                                    adapter.setBookingList(bookingList);
                                    adapter.setRecViewId(recViewId);
                                    adapter.setLayoutId(layoutId);
                                    recViewCommon.setLayoutManager(new LinearLayoutManager(context, orientationId,false));
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

            holder.cardBookingNotFinished.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setCancelable(true);
                    builder.setTitle("Confirmed Unfinished Appointment");
                    builder.setMessage("Confirm Appointment not completed because the Customer did not arrive?");
                    builder.setPositiveButton("Confirm",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    bookingList.get(position).setActionCode(1);
                                    recViewCommon = activity.findViewById(recViewId);
                                    RecViewBookingAdapter adapter = new RecViewBookingAdapter(context, activity);
                                    adapter.setBookingList(bookingList);
                                    adapter.setRecViewId(recViewId);
                                    adapter.setLayoutId(layoutId);
                                    recViewCommon.setLayoutManager(new LinearLayoutManager(context, orientationId,false));
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
            holder.cardBookingCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setCancelable(true);
                    builder.setTitle("Cancel Appointment");
                    builder.setMessage("Cancel Costumer's Appointment?");
                    builder.setPositiveButton("Confirm",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    intent = new Intent(context, BookingViewActivity.class);
                                    intent.putExtra("ACTION", 3);
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("LIST",(Serializable) bookingList);
                                    intent.putExtra("BUNDLE", bundle);
                                    intent.putExtra("POSITION", position);
                                    intent.putExtra("PAGE_IDENTIFIER", pageIdentifier);
                                    activity.startActivity(intent);
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
            holder.parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent = new Intent(context, BookingViewActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LIST",(Serializable) bookingList);
                    intent.putExtra("BUNDLE", bundle);
                    intent.putExtra("POSITION", position);
                    intent.putExtra("PAGE_IDENTIFIER", pageIdentifier);
                    activity.startActivity(intent);
                }
            });
        } else {
            holder.txtAppointmentCode.setText(bookingList.get(position).getAppointmentCode() + "");
            holder.txtBookingDate.setText(bookingList.get(position).getAppointmentDate());
            holder.txtBookingTime.setText(bookingList.get(position).getAppointmentTime());
            int total = 0, count = 0, remain = 0;
            for (ServiceDetail service : bookingList.get(position).getServiceDetailsList()) {
                total += Integer.parseInt(service.getPrice());
                count++;
            }
            if (count == 1) {
                holder.txtBookingCount.setText("1 Service");
            } else {
                holder.txtBookingCount.setText(count + " Services");
            }
            if (total >= 1000) {
                remain = total % 1000;
                total /= 1000;
            }
            holder.txtBookingTotalPrice.setText(total + ((remain == 0) ? "" : ("," + remain)) + ",000d");
            holder.txtBookingStatus.setVisibility(View.VISIBLE);
            if (bookingList.get(position).getActionCode() == 1) {
                holder.txtBookingStatus.setText("Customer's absence");
            } else if (bookingList.get(position).getActionCode() == 2) {
                holder.txtBookingStatus.setText("Appointment Completion Confirmed");
            } else if (bookingList.get(position).getActionCode() == 3) {
                holder.txtBookingStatus.setText("Cancelled by staff");
            } else if (bookingList.get(position).getActionCode() == 4) {
                holder.txtBookingStatus.setText("Cancelled by Customer");
            }  else {
                if (bookingList.get(position).isFinished()) {
                    holder.txtBookingStatus.setText("Awaiting Confirmation");
                } else {
                    holder.txtBookingStatus.setText("Upcoming");
                }
            }
            holder.bookingCardLinear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent = new Intent(context, BookingViewActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LIST",(Serializable) bookingList);
                    intent.putExtra("BUNDLE", bundle);
                    intent.putExtra("POSITION", position);
                    intent.putExtra("PAGE_IDENTIFIER", pageIdentifier);
                    activity.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtUserName, txtPhone, txtAppointmentCode, txtBookingDate, txtBookingTime,
                txtBookingCount, txtBookingTotalPrice, txtBookingStatus, txtBookingStatusNote;
        private CardView parent, cardBookingNotFinished, cardBookingFinished, cardBookingCancel;
        private LinearLayout bookingCardLinear;

        public ViewHolder(View view) {
            super(view);
            txtUserName = view.findViewById(R.id.txtUserName);
            txtPhone = view.findViewById(R.id.txtUserPhone);
            txtAppointmentCode = view.findViewById(R.id.txtAppointmentCode);
            txtBookingDate = view.findViewById(R.id.txtBookingDate);
            txtBookingTime = view.findViewById(R.id.txtBookingTime);
            txtBookingCount = view.findViewById(R.id.txtBookingCount);
            txtBookingTotalPrice = view.findViewById(R.id.txtBookingTotalPrice);
            txtBookingStatus = view.findViewById(R.id.txtBookingStatus);
            txtBookingStatusNote = view.findViewById(R.id.txtBookingStatusNote);
            cardBookingNotFinished = view.findViewById(R.id.cardBookingNotFinished);
            cardBookingFinished = view.findViewById(R.id.cardBookingFinished);
            cardBookingCancel = view.findViewById(R.id.cardBookingCancel);
            parent = view.findViewById(R.id.bookingCard);
            bookingCardLinear = view.findViewById(R.id.bookingCardLinear);

        }
    }
}
