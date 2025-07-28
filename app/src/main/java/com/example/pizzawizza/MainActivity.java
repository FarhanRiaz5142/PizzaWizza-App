package com.example.pizzawizza;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizzawizza.adapter.ProductAdapter;
import com.example.pizzawizza.data.Product;
import com.example.pizzawizza.data.User;
import com.example.pizzawizza.data.room.AppDatabase;
import com.example.pizzawizza.fragments.DiscoverFragment;
import com.example.pizzawizza.fragments.FvtFragment;
import com.example.pizzawizza.fragments.HomeFragment;
import com.example.pizzawizza.fragments.ProfileFragment;
import com.example.pizzawizza.retrofit.APIClient;
import com.example.pizzawizza.retrofit.APIInterface;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static String session = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User user = AppDatabase.getDatabase(this).userDao().getUser();
        session = user.getSession();
        APIClient.retrofit = null;


        findViewById(R.id.cartFab).setOnClickListener(v -> {
            startActivity(new Intent(this, CartActivity.class));
            overridePendingTransition(R.anim.up, R.anim.stay);
        });
   //FOR Share Text
//        Intent shareIntent = new Intent();
//        shareIntent.setAction(Intent.ACTION_SEND);
//        shareIntent.putExtra(Intent.EXTRA_TEXT,"Farhan");
//        shareIntent.setType("text/plain");
//        startActivity(Intent.createChooser(shareIntent,"Share via?"));
//
//        //For SMS
//        Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
//        smsIntent.setData(Uri.parse("smsto:" + Uri.encode("number")));
//        smsIntent.putExtra("sms_body", "message");
//        startActivity(smsIntent);
//
//
//        //FOR URL OPEN
//        Intent browseIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("Url"));
//        startActivity(browseIntent);
//
//        //open whats app chat
//        String phonenumberwithcountrycode = "923123456789";
//        Intent whatsappIntent = new Intent(Intent.ACTION_VIEW);
//        whatsappIntent.setData(Uri.parse(String.format("https://api.whatsapp.com/send?phone=%s" + phonenumberwithcountrycode)));
//        startActivity(whatsappIntent);
//
//
//        //For Call
//
//        Intent callIntent = new Intent(Intent.ACTION_DIAL);
//        callIntent.setData(Uri.parse("tel:" + "Phone Number"));
//        startActivity(callIntent);
//
//        //For Email
//        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
//        emailIntent.setData(Uri.parse("mailto:" + "Email Address"));
//        emailIntent.putExtra(Intent.EXTRA_EMAIL,new String[]{"recipent@gmail.com"});
//        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "subject");
//        emailIntent.putExtra(Intent.EXTRA_TEXT, "body");
//        startActivity(emailIntent);


        getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new HomeFragment()).commit();


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(menuItem -> {
            for (Fragment fragment : getSupportFragmentManager().getFragments())
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            if (menuItem.getItemId() == R.id.home) {
                getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new HomeFragment()).commit();
            }
            if (menuItem.getItemId() == R.id.fvt) {
                getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new FvtFragment()).commit();
            }
            if (menuItem.getItemId() == R.id.discover) {
                getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new DiscoverFragment()).commit();
            }
            if (menuItem.getItemId() == R.id.profile) {
                getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new ProfileFragment()).commit();
            }

            return true;
        });

    }

}