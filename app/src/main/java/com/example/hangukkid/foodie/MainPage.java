package com.example.hangukkid.foodie;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.hangukkid.foodie.API.GetFoodData;

import java.util.List;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_page);

        //---------------------Tab------------------------------//
        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.recipe_viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        MainPageTabAdapter adapter = new MainPageTabAdapter(this, getSupportFragmentManager());

        //set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        //Find the tab layout that shows the tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.recipe_tabs);

        // Connect the tab layout with the view pager. This will
        //   1. Update the tab layout when the view pager is swiped
        //   2. Update the view pager when a tab is selected
        //   3. Set the tab layout's tab names with the view pager's adapter's titles
        //      by calling onPageTitle()
        tabLayout.setupWithViewPager(viewPager);

        //---------------------Scroll------------------------------//
        Intent intent = getIntent();

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        List<String> ingredients = new List<String>;
        ingredients.add("apples");
//        GetFoodData.searchRecipes();
    }
}
