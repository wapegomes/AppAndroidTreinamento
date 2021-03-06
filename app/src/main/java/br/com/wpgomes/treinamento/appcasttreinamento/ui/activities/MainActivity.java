package br.com.wpgomes.treinamento.appcasttreinamento.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.analytics.FirebaseAnalytics;

import br.com.wpgomes.treinamento.appcasttreinamento.R;
import br.com.wpgomes.treinamento.appcasttreinamento.ui.fragments.CharacterFragment;
import br.com.wpgomes.treinamento.appcasttreinamento.ui.fragments.EventFragment;
import br.com.wpgomes.treinamento.appcasttreinamento.ui.fragments.MapsFragment;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences(sharedPreferencesFile,
                MODE_PRIVATE);
        boolean visited = prefs.getBoolean(getString(R.string.visited_splash), false);
        if (!visited) {
            Intent splash = new Intent(MainActivity.this, SplashScreenActivity.class);
            startActivity(splash);
            finish();
        }

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = null;

        int id = item.getItemId();
        Bundle params = new Bundle();

        if (id == R.id.nav_personagens) {
            fragment = CharacterFragment.newInstance();
            params.putString(getResources().getString(R.string.menu_clicado), getResources().getString(R.string.personagens));
        } else if (id == R.id.nav_evento) {
            fragment = EventFragment.newInstance();
            params.putString(getResources().getString(R.string.menu_clicado), getResources().getString(R.string.eventos));
        } else if (id == R.id.nav_mapa) {
            fragment = MapsFragment.newInstance();
            params.putString(getResources().getString(R.string.menu_clicado), getResources().getString(R.string.maps));
        } else if (id == R.id.nav_blue) {
            params.putString(getResources().getString(R.string.menu_clicado), getResources().getString(R.string.bluetooth));
            Intent intent = new Intent(this, BluetoothActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_camera) {
            params.putString(getResources().getString(R.string.menu_clicado), getResources().getString(R.string.camera));
            Intent intent = new Intent(this, CameraActivity.class);
            startActivity(intent);
        }

        if (fragment != null) {
            beginTransaction
                    .replace(R.id.content_main, fragment)
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        mFirebaseAnalytics.logEvent("main_navegation", params);

        return true;
    }
}
