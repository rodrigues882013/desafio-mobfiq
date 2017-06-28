package com.example.felipe.desafiomobfiq;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.felipe.desafiomobfiq.utils.Utils;
import com.pushwoosh.fragment.PushEventListener;
import com.pushwoosh.fragment.PushFragment;

public class PushFragmentActivity extends AppCompatActivity implements PushEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_fragment);

        //Init Pushwoosh fragment
        PushFragment.init(this);


    }

    @Override
    public void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);

        //Check if we've got new intent with a push notification
        PushFragment.onNewIntent(this, intent);
    }

    @Override
    public void doOnRegistered(String registrationId)
    {
        Log.i(Utils.MOBIFQ, "Registered for pushes: " + registrationId);
    }

    @Override
    public void doOnRegisteredError(String errorId)
    {
        Log.e(Utils.MOBIFQ, "Failed to register for pushes: " + errorId);
    }

    @Override
    public void doOnMessageReceive(String message)
    {
        Log.i(Utils.MOBIFQ, "Notification opened: " + message);
    }

    @Override
    public void doOnUnregistered(final String message)
    {
        Log.i(Utils.MOBIFQ, "Unregistered from pushes: " + message);
    }

    @Override
    public void doOnUnregisteredError(String errorId)
    {
        Log.e(Utils.MOBIFQ, "Failed to unregister from pushes: " + errorId);
    }
}

