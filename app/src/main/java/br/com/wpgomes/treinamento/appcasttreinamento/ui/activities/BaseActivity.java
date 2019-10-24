package br.com.wpgomes.treinamento.appcasttreinamento.ui.activities;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;

import br.com.wpgomes.treinamento.appcasttreinamento.broadcast.NetworkBroadcast;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by wgomes on 01/07/16.
 */

public class BaseActivity extends AppCompatActivity {

    public String sharedPreferencesFile = "MY_SHARED_PREFERENCES";

    protected FirebaseAnalytics mFirebaseAnalytics;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));

    }

    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(networkBroadcast,
                new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(networkBroadcast);
    }

    private NetworkBroadcast networkBroadcast = new NetworkBroadcast() {
        @Override
        public void onConnected(boolean isConnected) {
            if (isConnected) {
                Toast.makeText(BaseActivity.this, "Internet voltou!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(BaseActivity.this, "Internet caiu!", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
