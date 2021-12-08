package com.example.studenthub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studenthub.ebook.EbookActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private NavController navController;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    private long backPressed;
    private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("Rd Engineering College");

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        navController = Navigation.findNavController(this,R.id.frame_layout);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.start,R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //this is used to show the menu

        navigationView.setNavigationItemSelectedListener(this);

        NavigationUI.setupWithNavController(bottomNavigationView,navController);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { // this method is used to show the nav drawer.
        if (toggle.onOptionsItemSelected(item))
            return true;
        switch (item.getItemId()){
            case R.id.option_share:
                try {

                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT,"Rd Engineering College");
                    i.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName() );
                    startActivity(Intent.createChooser(i,"Share with"));
                }catch (Exception e){
                    Toast.makeText(this, "Unable to share this app", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.option_rate:
                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName());
                Intent i = new Intent(Intent.ACTION_VIEW,uri);
                try {
                    startActivity(i);
                } catch (Exception e) {
                    Toast.makeText(this, "Unable to open\n"+ e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;

        }


        return super.onOptionsItemSelected(item);
    }



    @Override


    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.navigation_developer:
                setContentView(R.layout.develop_section);
                break;

            case R.id.navigation_ebook:
                startActivity(new Intent(this, EbookActivity.class));
                break;
            case R.id.navigation_website:
                Toast.makeText(this, "Website", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_share:
                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT,"Rd Engineering College");
                    i.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName() );
                    startActivity(Intent.createChooser(i,"Share with"));
                }catch (Exception e){
                    Toast.makeText(this, "Unable to share this app", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.navigation_rateus:
                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName());
                Intent i = new Intent(Intent.ACTION_VIEW,uri);
                try {
                    startActivity(i);
                } catch (Exception e) {
                    Toast.makeText(this, "Unable to open\n"+ e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {

        //backPressed = System.currentTimeMillis();

        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);

//        }else {
//
//            if (backPressed+3000 > System.currentTimeMillis()){
//
//                backToast.cancel();
//                return;
//            }else {
//                CharSequence text;
//                backToast = Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT);
//                backToast.show();
//            }

        }

    }

    //@Override
//    public void onBackPressed() {
//        new AlertDialog.Builder(HomeActivity.this)
//                .setIcon(R.drawable.warning)
//                .setTitle("Exit")
//                .setMessage("Are you sure want to exit the app")
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        }).show();
//    }
}