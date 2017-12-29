package com.admiralfivetigers.fiveghost;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AndFixActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView text;
    private Button bu_AndFix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_and_fix);
        initView();
    }

    private void initView() {
        text = (TextView) findViewById(R.id.text);
        bu_AndFix = (Button) findViewById(R.id.bu_AndFix);

        bu_AndFix.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bu_AndFix:
                text.setText("Hello World");
                break;
        }
    }
}
