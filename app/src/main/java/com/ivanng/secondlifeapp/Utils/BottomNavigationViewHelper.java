package com.ivanng.secondlifeapp.Utils;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.ivanng.secondlifeapp.Home.HomeActivity;
import com.ivanng.secondlifeapp.Likes.LikesAcitivity;
import com.ivanng.secondlifeapp.Profile.ProfileAcitivity;
import com.ivanng.secondlifeapp.R;
import com.ivanng.secondlifeapp.Search.SearchAcitivity;
import com.ivanng.secondlifeapp.Share.ShareAcitivity;

public class BottomNavigationViewHelper {

    private static final String TAG = "BottomNavigationViewHel";

    public static void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx){
        Log.d(TAG, "setupBottomNavigationView: Setting up BottomNavigationView");
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(false);
    }

    public static void enableNavigation(final Context context, BottomNavigationViewEx view){
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.ic_house:
                        Intent intent1 = new Intent(context, HomeActivity.class); //ACTIVITY_NUM = 0
                        context.startActivity(intent1);
                        break;

                    case R.id.ic_search:
                        Intent intent2 = new Intent(context, SearchAcitivity.class); //ACTIVITY_NUM = 1
                        context.startActivity(intent2);
                        break;

                    case R.id.ic_add:
                        Intent intent3 = new Intent(context, ShareAcitivity.class); //ACTIVITY_NUM = 2
                        context.startActivity(intent3);
                        break;

                    case R.id.ic_alert:
                        Intent intent4 = new Intent(context, LikesAcitivity.class); //ACTIVITY_NUM = 3
                        context.startActivity(intent4);
                        break;

                    case R.id.ic_person:
                        Intent intent5 = new Intent(context, ProfileAcitivity.class); //ACTIVITY_NUM = 4
                        context.startActivity(intent5);
                        break;
                }



                return false;
            }
        });
    }

}
