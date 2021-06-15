package dev.hci.salonapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import dev.hci.salonapp.R;

public class BookingCartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_cart);
    }

    public void onBackBooking(View view) {
        finish();
    }
}