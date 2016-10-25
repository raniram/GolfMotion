package com.ramesh.sujata.golfmotion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "com.ramesh.sujata.golfmotion.MainActivity";

    private WakeLock mWakeLock;
    private SimulationView mSimulationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PowerManager mPowerManager = (PowerManager) getSystemService(POWER_SERVICE);
        mWakeLock = mPowerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, TAG);

        mSimulationView = new SimulationView(this);
        setContentView(mSimulationView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWakeLock.acquire();
        mSimulationView.startSimulation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSimulationView.stopSimulation();
        mWakeLock.release();
    }
}
