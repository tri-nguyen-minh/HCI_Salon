package dev.hci.salonapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import dev.hci.salonapp.R;

public class RegisterActivity extends AppCompatActivity {

    private boolean passwordDisplayed;
    private boolean confirmPasswordDisplayed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        passwordDisplayed = false;
        ImageView imgDisplayPasswordRegister = findViewById(R.id.imgDisplayPasswordRegister);
        TextView editPasswordRegister = findViewById(R.id.editPasswordRegister);
        editPasswordRegister.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        imgDisplayPasswordRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editPasswordRegister.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
                    editPasswordRegister.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                    imgDisplayPasswordRegister.setImageResource(R.drawable.ic_eye_view);
                } else {
                    editPasswordRegister.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    imgDisplayPasswordRegister.setImageResource(R.drawable.ic_eye);
                }
            }
        });
        ImageView imgDisplayConfirmPasswordRegister = findViewById(R.id.imgDisplayConfirmPasswordRegister);
        TextView editConfirmPasswordRegister = findViewById(R.id.editConfirmPasswordRegister);
        editConfirmPasswordRegister.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        imgDisplayConfirmPasswordRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editConfirmPasswordRegister.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
                    editConfirmPasswordRegister.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                    imgDisplayConfirmPasswordRegister.setImageResource(R.drawable.ic_eye_view);
                } else {
                    editConfirmPasswordRegister.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    imgDisplayConfirmPasswordRegister.setImageResource(R.drawable.ic_eye);
                }
            }
        });


        this.getSupportActionBar().hide();
    }

    public void onBackRegister(View view) {
        finish();
    }
}