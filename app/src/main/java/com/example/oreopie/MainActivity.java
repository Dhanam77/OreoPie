package com.example.oreopie;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private Toolbar mToolbar;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private FloatingActionButton myFloat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();


        mToolbar = (Toolbar) findViewById(R.id.maintoolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("PaisaManager");

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*myFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChatBotActivity.class);
                startActivity(intent);
            }
        });*/
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.logout) {
            DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
            drawerLayout.closeDrawer(GravityCompat.START);
            mAuth.signOut();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }
        if (menuItem.getItemId() == R.id.settings) {
            DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
            drawerLayout.closeDrawer(GravityCompat.START);
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }

        if(menuItem.getItemId() == R.id.pennymate)
        {
            DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
            drawerLayout.closeDrawer(GravityCompat.START);
            Intent intent = new Intent(MainActivity.this, ChatBotActivity.class);
            startActivity(intent);
        }


        return true;
    }


}

