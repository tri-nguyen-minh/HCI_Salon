package dev.hci.manager.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.hci_salon_manager.R;

public class ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);


        this.getSupportActionBar().hide();
    }

    public void onBackService(View view) {
        startActivity(new Intent(ServiceActivity.this, HomeActivity.class));
    }
}