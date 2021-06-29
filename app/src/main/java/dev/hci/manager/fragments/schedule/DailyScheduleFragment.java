package dev.hci.manager.fragments.schedule;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import dev.hci.manager.R;

import java.util.ArrayList;

public class DailyScheduleFragment extends Fragment {

    private TextView txtSave;
    private LinearLayout linearLayoutCommon;
    private int[] timeslotIdList = {R.id.btn00Daily, R.id.btn01Daily,
                                    R.id.btn02Daily, R.id.btn03Daily,
                                    R.id.btn04Daily, R.id.btn05Daily,
                                    R.id.btn06Daily, R.id.btn07Daily,
                                    R.id.btn08Daily, R.id.btn09Daily,
                                    R.id.btn10Daily, R.id.btn11Daily,
                                    R.id.btn12Daily, R.id.btn13Daily,
                                    R.id.btn14Daily, R.id.btn15Daily,
                                    R.id.btn16Daily, R.id.btn17Daily,
                                    R.id.btn18Daily, R.id.btn19Daily,
                                    R.id.btn20Daily, R.id.btn21Daily,
                                    R.id.btn22Daily, R.id.btn23Daily};
    private int[] timeslotCheckIdList = {R.id.imgTime00Daily, R.id.imgTime01Daily,
                                        R.id.imgTime02Daily, R.id.imgTime03Daily,
                                        R.id.imgTime04Daily, R.id.imgTime05Daily,
                                        R.id.imgTime06Daily, R.id.imgTime07Daily,
                                        R.id.imgTime08Daily, R.id.imgTime09Daily,
                                        R.id.imgTime10Daily, R.id.imgTime11Daily,
                                        R.id.imgTime12Daily, R.id.imgTime13Daily,
                                        R.id.imgTime14Daily, R.id.imgTime15Daily,
                                        R.id.imgTime16Daily, R.id.imgTime17Daily,
                                        R.id.imgTime18Daily, R.id.imgTime19Daily,
                                        R.id.imgTime20Daily, R.id.imgTime21Daily,
                                        R.id.imgTime22Daily, R.id.imgTime23Daily};
    private ArrayList<Boolean> timeslotCheckList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule_2_daily, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtSave = getView().findViewById(R.id.txtSaveDaily);
        txtSave.setVisibility(View.GONE);

        linearLayoutCommon = getView().findViewById(R.id.layoutWorkHourDaily);
        linearLayoutCommon.setVisibility(View.GONE);

        EditText txtTimeFromHourDaily = getView().findViewById(R.id.txtTimeFromHourDaily);
        EditText txtTimeFromMinuteDaily = getView().findViewById(R.id.txtTimeFromMinuteDaily);
        EditText txtTimeToHourDaily = getView().findViewById(R.id.txtTimeToHourDaily);
        EditText txtTimeToMinuteDaily = getView().findViewById(R.id.txtTimeToMinuteDaily);
        txtTimeFromHourDaily.addTextChangedListener(new TextWatcher() {
            int beforeCount = 0;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                beforeCount = count;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String hour = txtTimeFromHourDaily.getText().toString();
                if (hour.length() == 1) {
                    txtSave.setVisibility(View.VISIBLE);
                }
            }
        });
        txtTimeFromMinuteDaily.addTextChangedListener(new TextWatcher() {
            int beforeCount = 0;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                beforeCount = count;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String minute = txtTimeFromMinuteDaily.getText().toString();
                if (minute.length() == 1) {
                    txtSave.setVisibility(View.VISIBLE);
                }
            }
        });
        txtTimeToHourDaily.addTextChangedListener(new TextWatcher() {
            int beforeCount = 0;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                beforeCount = count;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String hour = txtTimeToHourDaily.getText().toString();
                if (hour.length() == 1) {
                    txtSave.setVisibility(View.VISIBLE);
                }
            }
        });
        txtTimeToMinuteDaily.addTextChangedListener(new TextWatcher() {
            int beforeCount = 0;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                beforeCount = count;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String minute = txtTimeToMinuteDaily.getText().toString();
                if (minute.length() == 1) {
                    txtSave.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}