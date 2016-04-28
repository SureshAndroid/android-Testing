package app.testingappnew;

import android.content.res.Configuration;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

/**
 * Created by ASUS on 20-Apr-16.
 */
public class FragmentActivity extends AppCompatActivity
{

    private DrawerLayout mDrawerLayout;
    private RelativeLayout mDrawer;

    ListView mDrawerList;
    ActionBarDrawerToggle mDrawerToggle;
    HomeMenuListAdapter mMenuAdapter;

    private CharSequence mDrawerTitle, mTitle;

    Fragment fragment1 =new Fragment_1();
    Fragment fragment2 =new Fragment_2();
    Fragment fragment3 =new Fragment_3();
    Fragment fragment4 =new Fragment_4();

    ColorDrawable colorDrawable = new ColorDrawable();
    static String[] title;
    int[] icon;
    ActionBar actionBar;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//		getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.fragment_homepage);

        actionBar = getSupportActionBar();
        actionBar.show();
        colorDrawable.setColor(0xffffffff);
//        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        init();
        mTitle = mDrawerTitle = getTitle();

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mMenuAdapter = new HomeMenuListAdapter(FragmentActivity.this, title, icon);
        mDrawerList.setAdapter(mMenuAdapter);
        mMenuAdapter.notifyDataSetChanged();
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open,R.string.drawer_close)
        {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            public void onDrawerOpened(View drawerView) {
//				getSupportActionBar().setTitle(mDrawerTitle);

                super.onDrawerOpened(drawerView);
            }
        };
        setupDrawer();

        if (savedInstanceState == null) {
            selectItem(0);
        }
    }
    private void init() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.drawer_list);
        mDrawer = (RelativeLayout) findViewById(R.id.drawer);
        title = new String[] { "Fragment_1", "Fragment_2", "Fragment_3" , "Db Fetch"};
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (mDrawerLayout.isDrawerOpen(mDrawer)) {
                mDrawerLayout.closeDrawer(mDrawer);
            } else {
                mDrawerLayout.openDrawer(mDrawer);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                ft.replace(R.id.content_frame, fragment1);

                break;
            case 1:
                ft.replace(R.id.content_frame, fragment2);

                break;
            case 2:
                ft.replace(R.id.content_frame, fragment3);

                break;
            case 3:
                ft.replace(R.id.content_frame, fragment4);

                break;
        }
        ft.commit();
        mDrawerList.setItemChecked(position, true);
        setTitle(title[position]);
        mDrawerLayout.closeDrawer(mDrawer);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        actionBar.setTitle(mTitle);
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //getSupportActionBar().setTitle("Navigation!");
//                actionBar.setIcon(R.drawable.ic_drawer);
                //invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()

                supportInvalidateOptionsMenu();
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                //getSupportActionBar().setTitle(mActivityTitle);
//                actionBar.setIcon(R.drawable.ic_drawer);
                //invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                supportInvalidateOptionsMenu();
            }
        };
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
        actionBar.setHomeAsUpIndicator(upArrow);

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }
}
