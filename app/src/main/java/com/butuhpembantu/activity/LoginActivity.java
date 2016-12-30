package com.butuhpembantu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.butuhpembantu.R;
import com.butuhpembantu.util.Preferences;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by akm on 12/30/16.
 */

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.email_input)
    EditText emailInput;
    @BindView(R.id.password_input)
    EditText passwordInput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Preferences.setFirstAppRunningStatus(false);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.sign_in_button)
    public void onClick() {

        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }
}
