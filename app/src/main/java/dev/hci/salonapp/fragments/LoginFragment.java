package dev.hci.salonapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import dev.hci.salonapp.MainActivity;
import dev.hci.salonapp.R;

public class LoginFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_5_login, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btn = getView().findViewById(R.id.btnLogin);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtTag = getActivity().findViewById(R.id.txtTag);
                txtTag.setText("Welcome,");
                TextView txtName = getActivity().findViewById(R.id.txtName);
                txtName.setText("Random User");
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.putExtra("user", "Random User");
                getActivity().setIntent(intent);
                TabLayout tabLayout = getActivity().findViewById(R.id.tabLayout);
                tabLayout.selectTab(tabLayout.getTabAt(0));
            }
        });
    }
}