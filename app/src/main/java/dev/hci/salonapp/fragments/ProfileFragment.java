package dev.hci.salonapp.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

import dev.hci.salonapp.activities.MainActivity;
import dev.hci.salonapp.R;
import dev.hci.salonapp.activities.RegisterActivity;

public class ProfileFragment extends Fragment {

    private Intent intent;
    private boolean passwordDisplayed;

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
            passwordDisplayed = false;
            return inflater.inflate(R.layout.fragment_main_5_login, container, false);
        }
        return inflater.inflate(R.layout.fragment_main_4_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (!intent.getBooleanExtra("logged", false)) {
            TextView txtLoginError = getView().findViewById(R.id.txtLoginError);
            txtLoginError.setText("");
            EditText editPassword = getView().findViewById(R.id.editPassword);
            editPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            ImageView imgDisplayPassword = getView().findViewById(R.id.imgDisplayPassword);
            imgDisplayPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println(passwordDisplayed);
                    if (passwordDisplayed) {
                        editPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        passwordDisplayed = false;
                    } else {
                        editPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                        passwordDisplayed = true;
                    }
                }
            });
            TextView btnLogin = getView().findViewById(R.id.btnLogin);
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    TextView txtTag = getActivity().findViewById(R.id.txtTag);
//                    txtTag.setVisibility(View.VISIBLE);
//                    txtTag.setText("Welcome,");
//                    TextView txtName = getActivity().findViewById(R.id.txtUsername);
//                    txtName.setText("User Name");

                    Intent intent = new Intent(getContext(), MainActivity.class);
                    intent.putExtra("logged", true);
                    getActivity().setIntent(intent);
                    TabLayout tabLayout = getActivity().findViewById(R.id.tabLayout);
                    tabLayout.selectTab(tabLayout.getTabAt(0));
                }
            });
            TextView txtToRegister = getView().findViewById(R.id.txtToRegister);
            txtToRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), RegisterActivity.class);
                    startActivity(intent);
                }
            });
        } else {
            TextView btnLogout = getView().findViewById(R.id.btnLogout);
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
//                                    TextView txtTag = getActivity().findViewById(R.id.txtTag);
//                                    txtTag.setVisibility(View.GONE);
//                                    TextView txtName = getActivity().findViewById(R.id.txtUsername);
//                                    txtName.setText("You are not logged in!");
                                    Intent intent = new Intent(getContext(), MainActivity.class);
                                    intent.putExtra("logged", false);
                                    getActivity().setIntent(intent);
                                    TabLayout tabLayout = getActivity().findViewById(R.id.tabLayout);
                                    tabLayout.selectTab(tabLayout.getTabAt(0));

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
        }
    }
}