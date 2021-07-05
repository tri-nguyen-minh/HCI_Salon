package dev.hci.manager.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import dev.hci.manager.R;
import dev.hci.manager.dtos.Booking;
import dev.hci.manager.dtos.ServiceDetail;
import dev.hci.manager.recycleviewadapter.RecViewBookingAdapter;
import dev.hci.manager.recycleviewadapter.RecViewServiceAdapter;

public class BookingViewActivity extends AppCompatActivity {

    private ArrayList<Booking> bookingList;
    private Booking booking;
    private ArrayList<ServiceDetail> serviceDetailsList;
    private Intent intent, newIntent;
    private Bundle bundle;
    private int actionCode, position;
    private String pageIdentifier;
    private RecyclerView recViewCommon;
    private LinearLayout layoutNote;
    private EditText editNote;
    private ScrollView scroll;
    private TextView txtUserName, txtPhone, txtAppointmentCode, txtBookingDate, txtBookingTime,
            txtBookingCount, txtBookingTotalPrice, txtBookingStatus, txtBookingStatusNote;
    private CardView cardBookingNotFinished, cardBookingFinished, cardBookingCancel, cardConfirmAction;
    private String[] STATUS_NOTE_LIST = {"Appointment cancelled because Customer did not arrive",
                                        "Appointment completion confirmed",
                                        "Appointment cancelled by Staff",
                                        "Appointment cancelled by Customer"};
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_view);

        layoutNote = findViewById(R.id.layoutNote);
        editNote = findViewById(R.id.editNote);
        scroll = findViewById(R.id.scrollBooking);

        intent = getIntent();
        bundle = intent.getBundleExtra("BUNDLE");
        bookingList = (ArrayList<Booking>) bundle.getSerializable("LIST");
        actionCode = intent.getIntExtra("ACTION", 0);
        position = intent.getIntExtra("POSITION", 0);
        booking = bookingList.get(position);
        pageIdentifier = intent.getStringExtra("PAGE_IDENTIFIER");

        serviceDetailsList = booking.getServiceDetailsList();

        txtUserName = findViewById(R.id.txtUserName);
        txtPhone = findViewById(R.id.txtUserPhone);
        txtAppointmentCode = findViewById(R.id.txtAppointmentCode);
        txtBookingDate = findViewById(R.id.txtBookingDate);
        txtBookingTime = findViewById(R.id.txtBookingTime);
        txtBookingCount = findViewById(R.id.txtBookingCount);
        txtBookingTotalPrice = findViewById(R.id.txtBookingTotalPrice);
        txtBookingStatus = findViewById(R.id.txtBookingStatus);
        txtBookingStatusNote = findViewById(R.id.txtBookingStatusNote);
        cardBookingNotFinished = findViewById(R.id.cardBookingNotFinished);
        cardBookingFinished = findViewById(R.id.cardBookingFinished);
        cardBookingCancel = findViewById(R.id.cardBookingCancel);
        cardConfirmAction = findViewById(R.id.cardConfirmAction);

        setupBasedOnAction();

        txtUserName.setText(booking.getUser());
        txtPhone.setText(booking.getPhone());
        txtAppointmentCode.setText(booking.getAppointmentCode() + "");
        txtBookingDate.setText(booking.getAppointmentDate());
        txtBookingTime.setText(booking.getAppointmentTime());
        int total = 0, count = 0, remain = 0;
        for (ServiceDetail service : booking.getServiceDetailsList()) {
            total += Integer.parseInt(service.getPrice());
            count++;
        }
        if (count == 1) {
            txtBookingCount.setText("1 Service booked");
        } else {
            txtBookingCount.setText(count + " Services booked");
        }
        if (total >= 1000) {
            remain = total % 1000;
            total /= 1000;
        }
        txtBookingTotalPrice.setText(total + ((remain == 0) ? "" : ("," + remain)) + ",000d");

        cardBookingFinished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BookingViewActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Completion Confirmation");
                builder.setMessage("Confirm Appointment has been completed?");
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                actionCode = 2;
                                setupBasedOnAction();
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

        cardBookingNotFinished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BookingViewActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Confirmed Unfinished Appointment");
                builder.setMessage("Confirm Appointment not completed because the Customer did not arrive?");
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                actionCode = 1;
                                setupBasedOnAction();
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
        cardBookingCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BookingViewActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Cancel Appointment");
                builder.setMessage("Cancel Costumer's Appointment?");
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                actionCode = 3;
                                setupBasedOnAction();
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
        cardConfirmAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BookingViewActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Confirm Appointment Status");
                if (actionCode == 1) {
                    builder.setMessage("Confirm Costumer's Absence?");
                } else {
                    builder.setMessage("Cancel Costumer's Appointment?");
                }
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                booking.setActionCode(actionCode);
                                bookingList.set(position, booking);
                                Serializable obj = (Serializable) bookingList;
                                newIntent = new Intent(getApplicationContext(), HomeActivity.class);
                                bundle.putSerializable("LIST",(Serializable) bookingList);
                                newIntent.putExtra("BUNDLE", bundle);
                                newIntent.putExtra("PAGE_IDENTIFIER", pageIdentifier);
                                startActivity(newIntent);

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

        recViewCommon =findViewById(R.id.recViewServiceBooked);
        RecViewServiceAdapter adapter = new RecViewServiceAdapter(BookingViewActivity.this, BookingViewActivity.this, 1);
        adapter.setServiceDetailsList(serviceDetailsList);
        recViewCommon.setAdapter(adapter);
        recViewCommon.setLayoutManager(new LinearLayoutManager(BookingViewActivity.this, RecyclerView.VERTICAL,false));

        scroll.fullScroll(View.FOCUS_DOWN);
        this.getSupportActionBar().hide();
    }

    private void setupBasedOnAction() {
        if (booking.getActionCode() == 0) {
            if (actionCode != 0) {
                if (actionCode == 1) {
                    txtBookingStatusNote.setVisibility(View.VISIBLE);
                    layoutNote.setVisibility(View.GONE);
                    txtBookingStatusNote.setText(STATUS_NOTE_LIST[actionCode - 1]);
                    cardBookingNotFinished.setVisibility(View.GONE);
                    cardBookingFinished.setVisibility(View.GONE);
                    cardBookingCancel.setVisibility(View.GONE);
                    cardConfirmAction.setVisibility(View.VISIBLE);
                } else if (actionCode == 3) {
                    editNote.setHint("Reason for cancelling the Appointment");
                    txtBookingStatusNote.setVisibility(View.VISIBLE);
                    layoutNote.setVisibility(View.VISIBLE);
                    txtBookingStatusNote.setText(STATUS_NOTE_LIST[actionCode - 1]);
                    cardBookingNotFinished.setVisibility(View.GONE);
                    cardBookingFinished.setVisibility(View.GONE);
                    cardBookingCancel.setVisibility(View.GONE);
                    cardConfirmAction.setVisibility(View.VISIBLE);
                }
            } else {
                if (booking.isFinished()) {
                    cardBookingNotFinished.setVisibility(View.VISIBLE);
                    cardBookingFinished.setVisibility(View.VISIBLE);
                    cardBookingCancel.setVisibility(View.GONE);
                    txtBookingStatus.setVisibility(View.VISIBLE);
                } else {
                    cardBookingNotFinished.setVisibility(View.GONE);
                    cardBookingFinished.setVisibility(View.GONE);
                    cardBookingCancel.setVisibility(View.VISIBLE);
                    txtBookingStatus.setVisibility(View.GONE);
                }
                layoutNote.setVisibility(View.GONE);
                txtBookingStatusNote.setVisibility(View.GONE);
                cardConfirmAction.setVisibility(View.GONE);
            }
        } else {
            cardBookingNotFinished.setVisibility(View.GONE);
            cardBookingFinished.setVisibility(View.GONE);
            cardBookingCancel.setVisibility(View.GONE);
            cardConfirmAction.setVisibility(View.GONE);
            txtBookingStatusNote.setVisibility(View.VISIBLE);
            layoutNote.setVisibility(View.VISIBLE);
            txtBookingStatusNote.setText(STATUS_NOTE_LIST[booking.getActionCode() - 1]);
                if (booking.getActionCode() == 3) {
                    editNote.setText("Changed in Schedule");
                    editNote.setEnabled(false);
//                } else if (booking.getActionCode() == 1) {
//                    editNote.setText("Customer did not arrive for the Appointment");
//                    editNote.setEnabled(false);
                } else {
                    layoutNote.setVisibility(View.GONE);
                }
        }
    }

    public void onBackBooking(View view) {
        finish();
    }
}