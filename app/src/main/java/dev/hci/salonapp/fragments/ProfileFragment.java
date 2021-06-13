package dev.hci.salonapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

import dev.hci.salonapp.MainActivity;
import dev.hci.salonapp.R;

public class ProfileFragment extends Fragment {

    private Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        intent = getActivity().getIntent();
        if (!intent.getBooleanExtra("logged", false)) {
            return inflater.inflate(R.layout.fragment_main_5_login, container, false);
        }
        return inflater.inflate(R.layout.fragment_main_4_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (!intent.getBooleanExtra("logged", false)) {
            TextView btnLogin = getView().findViewById(R.id.btnLogin);
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView txtTag = getActivity().findViewById(R.id.txtTag);
                    txtTag.setVisibility(View.VISIBLE);
                    txtTag.setText("Welcome,");
                    TextView txtName = getActivity().findViewById(R.id.txtUsername);
                    txtName.setText("User Name");
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    intent.putExtra("logged", true);
                    getActivity().setIntent(intent);
                    TabLayout tabLayout = getActivity().findViewById(R.id.tabLayout);
                    tabLayout.selectTab(tabLayout.getTabAt(0));
                }
            });
        }
    }
}