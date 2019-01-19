package matthew.won.utoronto.prod;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import matthew.won.utoronto.prod.Adapters.Pager_Adapter;
import matthew.won.utoronto.prod.Database.Database;
import matthew.won.utoronto.prod.Database.Datatype_SQL;
import matthew.won.utoronto.prod.Database.SQL_Helper;
import matthew.won.utoronto.prod.Datatypes.Pomodoro_Data;
import matthew.won.utoronto.prod.Datatypes.Subject;
import matthew.won.utoronto.prod.Datatypes.Task;



/*TO DO:
 * Media Player
 * Look at brain focus settings layout
 * another bug: save changes button crashes when there is no input
 */

public class MainPage extends AppCompatActivity {
    private ViewPager view_pager;
    private Pager_Adapter pager_adapter;
    private Toolbar toolbar;
    private TabLayout tab_layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager_screen);


        pager_adapter = new Pager_Adapter(getSupportFragmentManager());
        view_pager = (ViewPager) findViewById(R.id.view_pager);
        view_pager.setAdapter(pager_adapter);

        tab_layout = (TabLayout) findViewById(R.id.tab_layout);

        tab_layout.setupWithViewPager(view_pager);
        view_pager.setCurrentItem(1);

        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        android.support.v7.app.ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


    }

    /*********************SETTINGS OPTIONS ON TOOLBAR************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflating menu items to toolbar - Attaching toolbar items to toolbar
        getMenuInflater().inflate(R.menu.menu_items, menu);

        return true;
    }


    /*********************SETTINGS OPTIONS ON TOOLBAR************************************/

    //Temporary backstack, need to implement the full version later
    @Override
    public void onBackPressed() {
        switch (view_pager.getCurrentItem()) {
            case 0:
                view_pager.setCurrentItem(1);
                break;
            case 1:
                super.onBackPressed();
            case 2:
                view_pager.setCurrentItem(1);
        }
    }

}
