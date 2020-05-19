package com.program.myocabona;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.program.myocabona.Adapters.ViewPagerAdapter;
import com.program.myocabona.Fragments.FourthFragment;
import com.program.myocabona.Fragments.HomeFragment;
import com.program.myocabona.Fragments.SecondFragment;
import com.program.myocabona.Fragments.ThirdFragment;
import com.program.myocabona.Utils.SlidingTabLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import androidx.appcompat.widget.Toolbar;

public class MenuActivity extends AppCompatActivity {
    @BindView(R.id.toolbar_main) Toolbar toolbarMain;
    @BindView(R.id.tab_main) SlidingTabLayout tabMain;
    @BindView(R.id.vpg_main)
    ViewPager viewPagerMain;

    static final int CART_AMOUNT = 3;

    private void initializeComponent() {
     //  setSupportActionBar(toolbarMain);
        setupTabLayout(viewPagerMain);
        tabMain.setDistributeEvenly(true);
        tabMain.setViewPager(viewPagerMain);
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ButterKnife.bind(this);
        initializeComponent();

    }


    @Override public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.mn_cart);
        menuItem.setIcon(buildCounterDrawable(CART_AMOUNT, R.drawable.ic_action));

        return super.onCreateOptionsMenu(menu);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mn_cart:
                Toast.makeText(this, "Ada " + CART_AMOUNT + " Belanjaan di Keranjang.", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupTabLayout(ViewPager v) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(), "HOME");
        adapter.addFragment(new SecondFragment(), "SECOND");
        adapter.addFragment(new ThirdFragment(), "THIRD");
        adapter.addFragment(new FourthFragment(), "FOURTH");
        v.setAdapter(adapter);
    }

    private Drawable buildCounterDrawable(int count, int backgroundImageId) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.custom_notification_icon, null);
        view.setBackgroundResource(backgroundImageId);

        if (count == 0) {
            View counterTextPanel = view.findViewById(R.id.counterValuePanel);
            counterTextPanel.setVisibility(View.GONE);
        } else {
            TextView textView = (TextView) view.findViewById(R.id.txt_cart_count);
            textView.setText(String.valueOf(count));
        }

        view.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);

        return new BitmapDrawable(getResources(), bitmap);
    }

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Yakin akan keluar?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Jika ditekan ya, maka tutup aplikasi
                finish();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //jika ditekan no, maka kembali ke aplikasi
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
