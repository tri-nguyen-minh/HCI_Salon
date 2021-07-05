package dev.hci.manager.fragments.statistics;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;

import dev.hci.manager.R;

public class TodayStatisticFragment extends Fragment {

    private TextView txtLabel, txtRevenue, txtAppointment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistics, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtLabel = getView().findViewById(R.id.txtStatisticsLabel);
        txtRevenue = getView().findViewById(R.id.txtStatisticsRevenue);
        txtAppointment = getView().findViewById(R.id.txtStatisticsAppointment);
        txtLabel.setText("TODAY REVENUE");
        txtRevenue.setText("1,350,000d");
        txtAppointment.setText("1");
    }
}
