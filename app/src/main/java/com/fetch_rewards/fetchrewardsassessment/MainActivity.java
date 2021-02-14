package com.fetch_rewards.fetchrewardsassessment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fetch_rewards.fetchrewardsassessment.adapter.ItemAdapter;
import com.fetch_rewards.fetchrewardsassessment.applications.MyApplication;
import com.fetch_rewards.fetchrewardsassessment.utils.ConnectivityReceiver;
import com.fetch_rewards.fetchrewardsassessment.utils.Util;
import com.fetch_rewards.fetchrewardsassessment.view_model.ItemViewModel;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {
    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private ProgressBar progressBar;
    private ConnectivityReceiver mConnectivityReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();


        mConnectivityReceiver = new ConnectivityReceiver();
        registerNetworkBroadcastForNougat();


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        checkConnection();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerview);
        progressBar = findViewById(R.id.progress_circular);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showList(isConnected);
    }

    private void checkConnection() {
        boolean isConnected = mConnectivityReceiver.isOnline(MainActivity.this);
        showList(isConnected);
    }

    private void showList(boolean isConnected) {

        if (isConnected) {
            if (progressBar.getVisibility() !=View.VISIBLE)
                progressBar.setVisibility(View.VISIBLE);

            //Initialize View Model and get data
            ItemViewModel model = ViewModelProviders.of(this).get(ItemViewModel.class);
            model.getItems().observe(this, itemList -> {

                adapter = new ItemAdapter(MainActivity.this, itemList);
                recyclerView.setAdapter(adapter);
                findViewById(R.id.card_view).setVisibility(View.VISIBLE);

                //set progress bar visible before loading the data
                progressBar.setVisibility(View.GONE);
            });
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.check_internet_connection), Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.VISIBLE);
            findViewById(R.id.card_view).setVisibility(View.GONE);
            recyclerView.setAdapter(null);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.getInstance().setConnectivityListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mConnectivityReceiver != null)
            unregisterReceiver(mConnectivityReceiver);
    }

    private void registerNetworkBroadcastForNougat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(mConnectivityReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(mConnectivityReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }
}