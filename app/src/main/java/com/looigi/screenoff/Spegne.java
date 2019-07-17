package com.looigi.screenoff;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class Spegne extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
