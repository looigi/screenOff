package com.looigi.screenoff;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;

public class VariabiliGlobali {
    private static final VariabiliGlobali ourInstance = new VariabiliGlobali();

    public static VariabiliGlobali getInstance() {
        return ourInstance;
    }

    private VariabiliGlobali() {
    }

    private DevicePolicyManager devicePolicyManager;
    private Context context;
    private ComponentName compName;
    private boolean giaEntrato = false;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public boolean isGiaEntrato() {
        return giaEntrato;
    }

    public void setGiaEntrato(boolean giaEntrato) {
        this.giaEntrato = giaEntrato;
    }

    public ComponentName getCompName() {
        return compName;
    }

    public void setCompName(ComponentName compName) {
        this.compName = compName;
    }

    public DevicePolicyManager getDevicePolicyManager() {
        return devicePolicyManager;
    }

    public void setDevicePolicyManager(DevicePolicyManager devicePolicyManager) {
        this.devicePolicyManager = devicePolicyManager;
    }
}
