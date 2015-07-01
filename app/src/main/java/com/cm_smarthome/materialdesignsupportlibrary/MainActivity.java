package com.cm_smarthome.materialdesignsupportlibrary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private FloatingActionButton fabBtn;
    TextView tvEmail;
    TextView tvName;
    CircleImageView circleImageViewProfile;

    Context context = this;

    private boolean Count = false;

    @Override
    public void onBackPressed() {
        if (Count) {
            super.onBackPressed();
            android.os.Process.killProcess(android.os.Process.myPid());
            return;
        }
        Count = true;
        Toast.makeText(context, "Press Again to Exit..", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        Count = false;
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String name = getIntent().getStringExtra("name");
        String user_id = getIntent().getStringExtra("user_id");


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvEmail = (TextView) findViewById(R.id.email);
        tvEmail.setText("by PondThaiTay");

        tvName = (TextView) findViewById(R.id.username);
        tvName.setText(name);

        circleImageViewProfile = (CircleImageView) findViewById(R.id.profile_image);

        Picasso.with(context).load("https://graph.facebook.com/" + user_id + "/picture?type=large")
                .into(circleImageViewProfile);

        First_Fragment fragment = new First_Fragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }

                drawerLayout.closeDrawers();

                switch (menuItem.getItemId()) {

                    case R.id.first:
                        Toast.makeText(getApplicationContext(), "First Selected", Toast.LENGTH_SHORT).show();
                        First_Fragment first_fragment = new First_Fragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame, first_fragment);
                        fragmentTransaction.commit();
                        return true;
                    case R.id.second:
                        Toast.makeText(getApplicationContext(), "Second Selected", Toast.LENGTH_SHORT).show();
                        Second_Fragment second_fragment = new Second_Fragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction1.replace(R.id.frame, second_fragment);
                        fragmentTransaction1.commit();
                        return true;
                    case R.id.third:
                        Toast.makeText(getApplicationContext(), "Third Selected", Toast.LENGTH_SHORT).show();
                        Third_Fragment third_fragment = new Third_Fragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction2.replace(R.id.frame, third_fragment);
                        fragmentTransaction2.commit();
                        return true;
                    case R.id.fourth:
                        Toast.makeText(getApplicationContext(), "Fourth Selected", Toast.LENGTH_SHORT).show();
                        Fourth_Fragment fourth_fragment = new Fourth_Fragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction3.replace(R.id.frame, fourth_fragment);
                        fragmentTransaction3.commit();
                        return true;
                    case R.id.fifth:
                        Toast.makeText(getApplicationContext(), "Fifth Selected", Toast.LENGTH_SHORT).show();
                        Fifth_Fragment fifth_fragment = new Fifth_Fragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction4 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction4.replace(R.id.frame, fifth_fragment);
                        fragmentTransaction4.commit();
                        return true;
                    case R.id.sixth:
                        Toast.makeText(getApplicationContext(), "Sixth Selected", Toast.LENGTH_SHORT).show();
                        Sixth_Fragment sixth_fragment = new Sixth_Fragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction5 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction5.replace(R.id.frame, sixth_fragment);
                        fragmentTransaction5.commit();
                        return true;
                    case R.id.seventh:
                        Toast.makeText(getApplicationContext(), "Exit...", Toast.LENGTH_SHORT).show();
                        Seventh_Fragment seventh_fragment = new Seventh_Fragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction6 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction6.replace(R.id.frame, seventh_fragment);
                        fragmentTransaction6.commit();
                        return true;
                    default:
                        return true;
                }
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();

        fabBtn = (FloatingActionButton) findViewById(R.id.fabBtn);
        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "FloatingActionButton...", Toast.LENGTH_SHORT).show();
                Floating_Action_Button floating_action_button = new Floating_Action_Button();
                android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame, floating_action_button);
                transaction.commit();
            }
        });
    }
}