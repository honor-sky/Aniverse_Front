package org.gyeongsoton.aniverse_front;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MarketList extends AppCompatActivity {

    private static final int NUM_PAGES = 5;

    private ViewPager2 viewPager2;
    private FragmentStateAdapter pagerAdapter;

    MarketAll all_fragment = new MarketAll();
    MarketToy toy_fragment = new MarketToy();
    MarketSnack snack_fragment = new MarketSnack();
    MarketHygiene hygiene_fragment = new MarketHygiene();
    MarketClothes clothes_fragment = new MarketClothes();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_marketlist);

        ImageButton adopt_btn = (ImageButton)findViewById(R.id.adopt_btn);
        adopt_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AnimalList.class);
                startActivity(intent);
            }
        });
        ImageButton home_btn = (ImageButton)findViewById(R.id.home_btn);
        home_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main.class);
                startActivity(intent);
            }
        });
        ImageButton funding_btn = (ImageButton)findViewById(R.id.funding_btn);
        funding_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FundingList.class);
                startActivity(intent);
            }
        });
        ImageButton market_btn = (ImageButton)findViewById(R.id.market_btn);
        market_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MarketList.class);
                startActivity(intent);
            }
        });
        ImageButton mypage_btn = (ImageButton)findViewById(R.id.mypage_btn);
        mypage_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Mypage.class);
                startActivity(intent);
            }
        });
        Button add_btn = (Button)findViewById(R.id.item_add_btn);
        //AdoptList, ProtectList upload가 서로 다름
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdoptAnimalUpload.class);
                startActivity(intent);
            }
        });

        ArrayList<Fragment> f_items = new ArrayList<>();
        f_items.add(all_fragment);
        f_items.add(toy_fragment);
        f_items.add(snack_fragment);
        f_items.add(hygiene_fragment);
        f_items.add(clothes_fragment);

        // 뷰 페이저
        viewPager2 = findViewById(R.id.marketViewPager);
        pagerAdapter = new ScreenSlidePagerAdapter(this, f_items);
        viewPager2.setAdapter(pagerAdapter);

        final List<String> tabElement = Arrays.asList("전체","장난감","간식","위생\n용품","의류");
        TabLayout tabLayout = findViewById(R.id.tab);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                TextView textView = new TextView(MarketList.this);
                textView.setText(tabElement.get(position));
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textView.setTextSize(16);
                tab.setCustomView(textView);
            }
        }).attach();


    }


    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        ArrayList<Fragment> items ;
        public ScreenSlidePagerAdapter(FragmentActivity fa, ArrayList list) {
            super(fa);
            this.items=list;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return items.get(position);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

    }
}