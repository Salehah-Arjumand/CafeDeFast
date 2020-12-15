package com.example.myproject.drawerslider;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myproject.Cart;
import com.example.myproject.DRVinterface.LoadMore;
import com.example.myproject.DynamicRvAdapter;
import com.example.myproject.DynamicRvModel;
import com.example.myproject.Login;
import com.example.myproject.R;
import com.example.myproject.StaticRvAdapter;
import com.example.myproject.StaticRvModel;
import com.example.myproject.UserProfile;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Sliding extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener, View.OnClickListener {

    private RecyclerView recyclerView;
    private StaticRvAdapter staticRvAdapter;

    Button cart;

    List<DynamicRvModel> items = new ArrayList();
    DynamicRvAdapter dynamicRvAdapter;

    private static final int POS_DASHBOARD = 0;
    private static final int POS_ACCOUNT = 1;
    private static final int POS_CART = 2;
    private static final int POS_LOGOUT = 3;

    private String[] screenTitles;
    private Drawable[] screenIcons;

    private SlidingRootNav slidingRootNav;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding);

        //to remove "information bar" above the action bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        cart = findViewById(R.id.cart);
        cart.setOnClickListener(this);

        //dashboard
        final ArrayList<StaticRvModel> item = new ArrayList<>();
        item.add(new StaticRvModel(R.drawable.burgers, "Burger"));
        item.add(new StaticRvModel(R.drawable.fries, "Fries"));
        item.add(new StaticRvModel(R.drawable.desi, "Desi"));
        item.add(new StaticRvModel(R.drawable.pasta, "Pasta"));
        item.add(new StaticRvModel(R.drawable.burgers, "Burger"));
        item.add(new StaticRvModel(R.drawable.burgers, "Burger"));

        recyclerView = findViewById(R.id.rv_1);
        staticRvAdapter = new StaticRvAdapter(item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(staticRvAdapter);


        items.add(new DynamicRvModel("Burger"));
        items.add(new DynamicRvModel("Burger"));
        items.add(new DynamicRvModel("Burger"));
        items.add(new DynamicRvModel("Burger"));
        items.add(new DynamicRvModel("Burger"));
        items.add(new DynamicRvModel("Burger"));
        items.add(new DynamicRvModel("Burger"));
        items.add(new DynamicRvModel("Burger"));
        items.add(new DynamicRvModel("Burger"));
        items.add(new DynamicRvModel("Burger"));
        items.add(new DynamicRvModel("Burger"));
        items.add(new DynamicRvModel("Burger"));
        items.add(new DynamicRvModel("Burger"));
        items.add(new DynamicRvModel("Burger"));
        items.add(new DynamicRvModel("Burger"));

        RecyclerView drv = findViewById(R.id.rv_2);
        //?????????????????????????????????????????????????????????????????????????????????????????
        drv.setLayoutManager(new LinearLayoutManager(this));
        dynamicRvAdapter = new DynamicRvAdapter(this, items, drv);
        //?????????????????????????????????????????????????????????????????????????????????????????
        drv.setAdapter(dynamicRvAdapter);
        dynamicRvAdapter.setLoadMore(new LoadMore() {
            @Override
            public void onLoadMore() {
                if (items.size() <= 10) {
                    item.add(null);
                    dynamicRvAdapter.notifyItemInserted(items.size() - 1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            items.remove(items.size() - 1);
                            dynamicRvAdapter.notifyItemRemoved(items.size());

                            int index = item.size();
                            int end = index + 10;
                            for (int i = index; i < end; i++) {
                                String name = UUID.randomUUID().toString();
                                DynamicRvModel item = new DynamicRvModel(name);
                                items.add(item);
                            }
                            dynamicRvAdapter.notifyDataSetChanged();
                            dynamicRvAdapter.setLoaded();
                        }
                    }, 4000);
                } else {
                    Toast.makeText(Sliding.this, "Data completed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.drawer_menu_layout)
                .inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_DASHBOARD).setChecked(true),
                createItemFor(POS_ACCOUNT),
                createItemFor(POS_CART),
                new SpaceItem(46),
                createItemFor(POS_LOGOUT)));
        adapter.setListener(this);

        RecyclerView list = findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        adapter.setSelected(POS_DASHBOARD);
    }

    @Override
    public void onItemSelected(int position) {
        if (position == POS_LOGOUT) {
            Intent i = new Intent(Sliding.this, Login.class);
            startActivity(i);
        }
        else if(position == POS_CART){
            Intent i = new Intent(Sliding.this, Cart.class);
            startActivity(i);
        }
        else if(position == POS_ACCOUNT){
            Intent i = new Intent(Sliding.this, UserProfile.class);
            startActivity(i);
        }

        slidingRootNav.closeMenu();
        Fragment selectedScreen = CenteredTextFragment.createFor(screenTitles[position]);
        showFragment(selectedScreen);
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    @SuppressWarnings("rawtypes")
    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withIconTint(color(R.color.light))
                .withTextTint(color(R.color.light))
                .withSelectedIconTint(color(R.color.black))
                .withSelectedTextTint(color(R.color.black));
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.ld_activityScreenTitles);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.ld_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }

    @ColorInt
    private int color(@ColorRes int res) {
        return ContextCompat.getColor(this, res);
    }


    @Override
    public void onClick(View v) {
        Intent i = new Intent(Sliding.this, Cart.class);
        startActivity(i);
    }
}