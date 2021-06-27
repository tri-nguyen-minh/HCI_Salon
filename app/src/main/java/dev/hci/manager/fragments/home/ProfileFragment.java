package dev.hci.manager.fragments.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hci_salon_manager.R;

import dev.hci.manager.activities.LoginActivity;
import dev.hci.manager.activities.ScheduleActivity;
import dev.hci.manager.activities.ServiceActivity;

public class ProfileFragment extends Fragment {

    private Intent intent, newIntent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_3_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ConstraintLayout btnLogout = getView().findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setCancelable(true);
                builder.setTitle("Log out of your Account?");
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                newIntent = new Intent(getContext(), LoginActivity.class);
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

        ConstraintLayout btnChangeSchedule = getView().findViewById(R.id.btnChangeSchedule);
        btnChangeSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newIntent = new Intent(getContext(), ScheduleActivity.class);
                startActivity(newIntent);
            }
        });

        ConstraintLayout btnChangeServices = getView().findViewById(R.id.btnChangeServices);
        btnChangeServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newIntent = new Intent(getContext(), ServiceActivity.class);
                startActivity(newIntent);
            }
        });
    }
}