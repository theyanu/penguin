package me.yanu.penguin;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class MainActivity extends AppCompatActivity {

    private static final String SELECTED_ITEM = "var_selected_item";

    private BottomNavigationViewEx mBottomNav;
    private int mSelectedItem;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            getFragment(item);
            return true;
        }

    };

    private void getFragment(MenuItem item) {
        Fragment frag = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                frag = PenguinFragment.newInstance("", R.color.colorPrimary, "logo.json", true);
                break;
            case R.id.navigation_panda:
                frag = PenguinFragment.newInstance("Press me!", R.color.colorPrimary, "panda.json", false);
                break;
            case R.id.navigation_photo:
                frag = PenguinFragment.newInstance("Photo", R.color.colorPrimary, "TwitterHeart.json", false);
                break;
            case R.id.navigation_apple:
                frag = PenguinFragment.newInstance("Apple", R.color.colorPrimary, "TwitterHeart.json", false);
                break;
        }

        mSelectedItem = item.getItemId();

        if (frag != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.slide_in_up, R.anim.slide_out_down);
            ft.replace(R.id.content, frag, frag.getTag());
            ft.addToBackStack(frag.getTag());
            ft.commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateToolbarText("");

        mBottomNav = (BottomNavigationViewEx) findViewById(R.id.navigation);

        mBottomNav.enableAnimation(false);
        mBottomNav.enableShiftingMode(false);
        mBottomNav.enableItemShiftingMode(false);
        mBottomNav.setTextVisibility(false);


        mBottomNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        MenuItem selectedItem;
        if (savedInstanceState != null) {
            mSelectedItem = savedInstanceState.getInt(SELECTED_ITEM, 0);
            selectedItem = mBottomNav.getMenu().findItem(mSelectedItem);
        } else {
            selectedItem = mBottomNav.getMenu().getItem(0);
        }
        getFragment(selectedItem);

        if (Build.VERSION.SDK_INT >= 21) {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null)
                actionBar.setElevation(0);
            //mBottomNav.setElevation(0);
        }

        /*ActionBar actionBar = getSupportActionBar();

        if (actionBar != null)
            actionBar.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
*/
    }

    private void updateToolbarText(CharSequence text) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(text);
        }
    }

}
