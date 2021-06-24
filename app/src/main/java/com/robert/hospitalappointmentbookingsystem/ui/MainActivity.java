package com.robert.hospitalappointmentbookingsystem.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.os.Handler;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.robert.hospitalappointmentbookingsystem.FragmentTransition;
import com.robert.hospitalappointmentbookingsystem.R;
import com.robert.hospitalappointmentbookingsystem.appointment.AppointmentFragment;
import com.robert.hospitalappointmentbookingsystem.doctor.DoctorAvailabilityFragment;
import com.robert.hospitalappointmentbookingsystem.doctor.DoctorDetailsFragment;
import com.robert.hospitalappointmentbookingsystem.home.HomeFragment;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private AppBarConfiguration mAppBarConfiguration;
    private  DrawerLayout drawer;

    private boolean home = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_camera);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        if(getIntent() != null){
           home = getIntent().getBooleanExtra("Home", false);
        }

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.nav_icon4);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final  View view =  navigationView.getHeaderView(0);
        CircularImageView profile = view.findViewById(R.id.profile_photo);

        TextView phonenumber = view.findViewById(R.id.phonenumber);
        TextView doctorname = view.findViewById(R.id.doctor_name);
        TextView usertype = view.findViewById(R.id.usertype);
        ToggleButton toggleButton = view.findViewById(R.id.notify_toggle);
        TextView dashboard_title = findViewById(R.id.dashboard_title);

        if(home){

            dashboard_title.setText("DOCTOR DASHBOARD");
            profile.setImageDrawable(getResources().getDrawable(R.drawable.a));
            toggleButton.setVisibility(View.GONE);
            doctorname.setText("Dr. David Mbogo");
            usertype.setText("Doctor");
            phonenumber.setText("0712345678");
        }else{
            dashboard_title.setText("PATIENT DASHBOARD");
           profile.setImageDrawable(getResources().getDrawable(R.drawable.patient));
            toggleButton.setVisibility(View.VISIBLE);
            doctorname.setText("Robert Mwangi");
            usertype.setText("Patient");
            phonenumber.setText("0715090835");
        }


        backToHome();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }



    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch(id) {
            //handle actions
            case R.id.nav_home:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    boolean doubleBackToExitPressedOnce = false;


    @Override
    public void onBackPressed() {

        //its not working
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else {

            for (Fragment fragment : getSupportFragmentManager().getFragments()) {

                 if (fragment instanceof HomeFragment) {

                    //prompt the user if he she wants to exit or not
                    //check if the sheet is expanded, if yes, collapse
                    //if no, just exit, if back is clicked twice

                    if (doubleBackToExitPressedOnce) {
                        //                moveTaskToBack(true);
                        finish();
                        // System.exit(0);
                        // moveTaskToBack(true);

                        return;
                    }

                    this.doubleBackToExitPressedOnce = true;
                    Toast.makeText(this, "Back again to exit!" , Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            doubleBackToExitPressedOnce = false;
                        }
                    }, 2000);

                    //super.onBackPressed();
                    break;
                } else{
                     backToHome();
                 }

            }
        }

    }



    public  void backToHome() {
        if(home){
            FragmentTransition.displayThePassedFragment(DoctorAvailabilityFragment.class,null, getSupportFragmentManager());
        }else{
            FragmentTransition.displayThePassedFragment(HomeFragment.class,null, getSupportFragmentManager());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}
