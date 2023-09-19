package com.example.pethome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.io.*;

public class BaseApplication extends AppCompatActivity
        implements BottomNavigationView
        .OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    MaterialToolbar myToolbar;
    LikesFragment likesFragment = new LikesFragment();
    ChatFragment chatFragment = new ChatFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    VetFragment vetFragment = new VetFragment();
    SwipeFragment swipeFragment = new SwipeFragment();
    Button AB_Settings;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_application);
        myToolbar = (MaterialToolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setLogo(R.drawable.logocomplete_small);// set drawable icon
        bottomNavigationView
                = findViewById(R.id.bottomNavigationView);

        bottomNavigationView
                .setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);

        SharedPreferences preferences = this.getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String uid = preferences.getString("uid","");
        Log.d("Session", "onCreate: uid = " + uid);
//        String userEmail = preferences.getString("useremail", "");
    }

    @Override
    public boolean
    onNavigationItemSelected(@NonNull MenuItem item)
    {

        switch (item.getItemId()) {
            case R.id.likes:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, likesFragment)
                        .commit();
                return true;

            case R.id.home:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, swipeFragment)
                        .commit();
                return true;

            case R.id.chat:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, chatFragment)
                        .commit();
                return true;
            case R.id.profile:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, profileFragment)
                        .commit();
                return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    //actionbar item onClick()
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.AB_Settings:
                    FirebaseAuth.getInstance().signOut();
                    goToMainActivity();
                return true;
//
//            case R.id.action_favorite:
//                // User chose the "Favorite" action, mark the current item
//                // as a favorite...
//                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private void goToMainActivity() {
        startActivity(new Intent(BaseApplication.this, MainActivity.class));
    }
}
