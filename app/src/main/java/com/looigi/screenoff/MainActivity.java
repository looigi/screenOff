package com.looigi.screenoff;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button lock, disable, enable;
    public static final int RESULT_ENABLE = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VariabiliGlobali.getInstance().setContext(this);
        VariabiliGlobali.getInstance().setDevicePolicyManager((DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE));
        VariabiliGlobali.getInstance().setCompName(new ComponentName(this, MyAdmin.class));

        lock = (Button) findViewById(R.id.lock);
        enable = (Button) findViewById(R.id.enableBtn);
        disable = (Button) findViewById(R.id.disableBtn);
        lock.setOnClickListener(this);
        enable.setOnClickListener(this);
        disable.setOnClickListener(this);

        EnableLock();

        if (!VariabiliGlobali.getInstance().isGiaEntrato()) {
            VariabiliGlobali.getInstance().setGiaEntrato(true);

            moveTaskToBack(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        boolean isActive = VariabiliGlobali.getInstance().getDevicePolicyManager().isAdminActive(VariabiliGlobali.getInstance().getCompName());
        disable.setVisibility(isActive ? View.VISIBLE : View.GONE);
        enable.setVisibility(isActive ? View.GONE : View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        if (view == lock) {
            boolean active = VariabiliGlobali.getInstance().getDevicePolicyManager().isAdminActive(VariabiliGlobali.getInstance().getCompName());

            if (active) {
                VariabiliGlobali.getInstance().getDevicePolicyManager().lockNow();
            } else {
                Toast.makeText(this, "You need to enable the Admin Device Features", Toast.LENGTH_SHORT).show();
            }

        } else if (view == enable) {
            EnableLock();
        } else if (view == disable) {
            VariabiliGlobali.getInstance().getDevicePolicyManager().removeActiveAdmin(VariabiliGlobali.getInstance().getCompName());
            disable.setVisibility(View.GONE);
            enable.setVisibility(View.VISIBLE);
        }
    }

    private void EnableLock() {
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, VariabiliGlobali.getInstance().getCompName());
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Additional text explaining why we need this permission");
        startActivityForResult(intent, RESULT_ENABLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case RESULT_ENABLE :
                if (resultCode == Activity.RESULT_OK) {
                    Toast.makeText(MainActivity.this, "You have enabled the Admin Device features", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Problem to enable the Admin Device features", Toast.LENGTH_SHORT).show();
                }
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
