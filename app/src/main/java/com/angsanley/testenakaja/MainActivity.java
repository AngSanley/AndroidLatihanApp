/*
 * Copyright (c) 2019 Stanley Ang
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.angsanley.testenakaja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Space;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView titleText, subtitleText;
    MaterialButton loginBtn;
    TextInputLayout staffIdLayout;
    MaterialToolbar toolbar;
    LinearLayout linearLayoutTitle;
    Space titleSpacing;
    TextInputEditText staffIdText;
    ProgressBar loadingProgress;
    CardView loginCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        DisplayMetrics displayMetrics = MainActivity.this.getResources().getDisplayMetrics();
        float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;

        titleText = findViewById(R.id.titleText);
        subtitleText = findViewById(R.id.subTitleText);
        loginBtn = findViewById(R.id.material_button);
        staffIdLayout = findViewById(R.id.textLayout);
        toolbar = findViewById(R.id.toolbar);
        linearLayoutTitle = findViewById(R.id.linearLayoutTitle);
        titleSpacing = findViewById(R.id.spacing);
        staffIdText = findViewById(R.id.staffIdTextEdit);
        loadingProgress = findViewById(R.id.login_progress);
        loginCard = findViewById(R.id.loginCard);

        if (dpHeight < 500) {
            linearLayoutTitle.setOrientation(LinearLayout.HORIZONTAL);
            titleSpacing.setVisibility(View.VISIBLE);

        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Typeface rubikReg = Typeface.createFromAsset(getAssets(), "Rubik-Regular.ttf");
        Typeface rubikBold = Typeface.createFromAsset(getAssets(), "Rubik-Bold.ttf");

        titleText.setTypeface(rubikBold);
        loginBtn.setTypeface(rubikBold);
        staffIdLayout.setTypeface(rubikReg);
        subtitleText.setTypeface(rubikReg);

        setTitle("Mi Account");
        setSubtitle("Please log in");

        loginBtn.setOnClickListener(this);
    }

    @Override
    public void setTitle(CharSequence title) {
        titleText.setText(title);
        super.setTitle(title);
    }

    public void setSubtitle(CharSequence subtitle) {
        subtitleText.setText(subtitle);
    }

    @Override
    public void onClick(View v) {
        final String staffId = staffIdText.getText().toString();

        if (staffId.isEmpty()) {
            staffIdText.setError("Please fill in");
            staffIdText.requestFocus();
            return;
        }

        loadingProgress.setVisibility(View.VISIBLE);
        staffIdLayout.setVisibility(View.GONE);
        loginBtn.setVisibility(View.GONE);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                //TODO your background code
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    intent.putExtra("staffId", staffId);
                    startActivity(intent);
                    MainActivity.this.finish();
                }
            }
        });



        //finish();
    }
}
