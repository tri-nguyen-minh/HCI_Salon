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

public class GeneralScheduleFragment extends Fragment {

    private TextView txtSave;
    private LinearLayout linearLayoutCommon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule_1_general, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtSave = getView().findViewById(R.id.txtSaveGeneral);
        txtSave.setVisibility(View.GONE);

        linearLayoutCommon = getView().findViewById(R.id.layoutWorkHourGeneral);
        linearLayoutCommon.setVisibility(View.GONE);

        EditText txtTimeFromHour = getView().findViewById(R.id.txtTimeFromHour);
        EditText txtTimeFromMinute = getView().findViewById(R.id.txtTimeFromMinute);
        EditText txtTimeToHour = getView().findViewById(R.id.txtTimeToHour);
        EditText txtTimeToMinute = getView().findViewById(R.id.txtTimeToMinute);
        txtTimeFromHour.addTextChangedListener(new TextWatcher() {
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
                String hour = txtTimeFromHour.getText().toString();
                if (hour.length() == 1) {
                    txtSave.setVisibility(View.VISIBLE);
                }
            }
        });
        txtTimeFromMinute.addTextChangedListener(new TextWatcher() {
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
                String minute = txtTimeFromMinute.getText().toString();
                if (minute.length() == 1) {
                    txtSave.setVisibility(View.VISIBLE);
                }
            }
        });
        txtTimeToHour.addTextChangedListener(new TextWatcher() {
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
                String hour = txtTimeToHour.getText().toString();
                if (hour.length() == 1) {
                    txtSave.setVisibility(View.VISIBLE);
                }
            }
        });
        txtTimeToMinute.addTextChangedListener(new TextWatcher() {
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
                String minute = txtTimeToMinute.getText().toString();
                if (minute.length() == 1) {
                    txtSave.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}