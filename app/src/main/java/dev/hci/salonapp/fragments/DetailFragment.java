package dev.hci.salonapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.fragment.app.Fragment;

import dev.hci.salonapp.R;
import dev.hci.salonapp.dtos.Salon;

public class DetailFragment extends Fragment {

    private Intent intent;
    private ImageView imgCommon;
    private TextView txtCommon;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_salon_1_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        intent = getActivity().getIntent();
        Salon salon = (Salon)intent.getSerializableExtra("salon");

        ViewFlipper lookFlipper = getView().findViewById(R.id.lookFlipper);
        imgCommon = new ImageView(getContext());
        imgCommon.setImageResource(R.drawable.image_look_1);
        lookFlipper.addView(imgCommon);
        imgCommon = new ImageView(getContext());
        imgCommon.setImageResource(R.drawable.image_look_2);
        lookFlipper.addView(imgCommon);
        imgCommon = new ImageView(getContext());
        imgCommon.setImageResource(R.drawable.image_look_3);
        lookFlipper.addView(imgCommon);
        imgCommon = new ImageView(getContext());
        imgCommon.setImageResource(R.drawable.image_look_4);
        lookFlipper.addView(imgCommon);
        imgCommon = new ImageView(getContext());
        imgCommon.setImageResource(R.drawable.image_look_5);
        lookFlipper.addView(imgCommon);
        lookFlipper.setAutoStart(true);
        lookFlipper.setFlipInterval(2000);
        lookFlipper.setInAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.flipper_left_in));
        lookFlipper.setOutAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.flipper_left_out));
        lookFlipper.startFlipping();

        txtCommon = getView().findViewById(R.id.salonAddress);
        txtCommon.setText(salon.getAddress());

        txtCommon = getView().findViewById(R.id.salonDescription);
        TextView txtViewDescription = getView().findViewById(R.id.txtViewDescription);
        txtViewDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtViewDescription.getText().toString().equals("See more")) {
                    txtCommon.setEllipsize(null);
                    txtCommon.setMaxLines(50);
                    txtViewDescription.setText("See less");
                } else {
                    txtCommon.setEllipsize(TextUtils.TruncateAt.END);
                    txtCommon.setMaxLines(2);
                    txtViewDescription.setText("See more");
                }
            }
        });


    }
}