package com.looigi.screenoff;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class Spegne extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (VariabiliGlobali.getInstance().getDevicePolicyManager() == null) {
            VariabiliGlobali.getInstance().setDevicePolicyManager((DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE));
            VariabiliGlobali.getInstance().setCompName(new ComponentName(this, MyAdmin.class));
        }
        boolean active = VariabiliGlobali.getInstance().getDevicePolicyManager().isAdminActive(VariabiliGlobali.getInstance().getCompName());

        if (active) {
            VariabiliGlobali.getInstance().getDevicePolicyManager().lockNow();
        } else {
            Toast.makeText(this,
                    "You need to enable the Admin Device Features", Toast.LENGTH_SHORT).show();
        }

        finish();
    }
}
