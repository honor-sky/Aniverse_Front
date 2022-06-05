package org.gyeongsoton.aniverse_front;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MarketList extends Fragment {

    private static final int NUM_PAGES = 5;

    private ViewPager2 viewPager2;
    private FragmentStateAdapter pagerAdapter;

    MarketAll all_fragment = new MarketAll();
    MarketToy toy_fragment = new MarketToy();
    MarketSnack snack_fragment = new MarketSnack();
    MarketHygiene hygiene_fragment = new MarketHygiene();
    MarketClothes clothes_fragment = new MarketClothes();

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_marketlist, container, false);


        ArrayList<Fragment> f_items = new ArrayList<>();
        f_items.add(all_fragment);
        f_items.add(toy_fragment);
        f_items.add(snack_fragment);
        f_items.add(hygiene_fragment);
        f_items.add(clothes_fragment);

        // 뷰 페이저
        viewPager2 = view.findViewById(R.id.marketViewPager);
        pagerAdapter = new ScreenSlidePagerAdapter(getActivity(), f_items);
        viewPager2.setAdapter(pagerAdapter);

        final List<String> tabElement = Arrays.asList("전체","장난감","간식","위생\n용품","의류");
        TabLayout tabLayout = view.findViewById(R.id.tab);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                TextView textView = new TextView(getActivity());
                textView.setText(tabElement.get(position));
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textView.setTextSize(16);
                tab.setCustomView(textView);
            }
        }).attach();

        return view;
    }

   //프래그먼트에서 백
    @Override
    public void onBackPressed() {
        if (viewPager2.getCurrentItem() == 0) {
            super.onBackPressed();
        }
        else {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() - 1);
        }
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
