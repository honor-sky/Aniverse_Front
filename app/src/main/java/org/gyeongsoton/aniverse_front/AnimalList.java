
package org.gyeongsoton.aniverse_front;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// 동물 리스트
// 뷰페이저2 사용, 프래그먼트, 리사이클러뷰 사용
public class AnimalList extends Fragment implements OnPageChangeCallback {

    private static final int NUM_PAGES = 3;

    private ViewPager2 viewPager2;
    private FragmentStateAdapter pagerAdapter;

    AdoptList adopt_fragment = new AdoptList();
    ProtectList protect_fragment = new ProtectList();
    CompleteList complete_fragment = new CompleteList();

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.activity_animallist, container, false);


        // 탭 레이아웃
        TabLayout tabLayout = view.findViewById(R.id.tab);
        //기본색상, 선택시 색상 설정
        //tabLayout.setTabTextColors(Color.rgb(0,0,0),Color.rgb(150,0,0));

        ArrayList<Fragment> f_items = new ArrayList<>();

        f_items.add(adopt_fragment);
        f_items.add(protect_fragment);
        f_items.add(complete_fragment);

        // 뷰 페이저
        viewPager2 = view.findViewById(R.id.animalViewpager);
        pagerAdapter = new ScreenSlidePagerAdapter(getActivity(), f_items);
        viewPager2.setAdapter(pagerAdapter);

        //탭 레이아웃에 넣을 문자 리스트
        final List<String> tabElement = Arrays.asList("입양","임시보호","완료");

        // 뷰페이저와 탭을 연결
        // 첫번째 인자 : 탭 레이아웃, 두번째 인자 : 뷰페이저, 세번째 인자: 탭레이아웃의 각 탭의 구성값을 설정할 수 있는 메서드
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                TextView textView = new TextView(getContext());
                textView.setText(tabElement.get(position));
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textView.setTextSize(20);
                tab.setCustomView(textView);
            }
        }).attach();

        Button add_btn = (Button)view.findViewById(R.id.add_btn);
        //AdoptList, ProtectList upload가 서로 다름
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewPager2.getCurrentItem()==0){
                    Intent intent = new Intent(getContext(), AdoptAnimalUpload.class);
                    startActivity(intent);
                }
                else if (viewPager2.getCurrentItem()==1){
                    Intent intent = new Intent(getContext(), ProtectAnimalUpload.class);
                    startActivity(intent);
                }
            }
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                if (viewPager2.getCurrentItem() == 0) {
                    super.onBackPressed();
                }
                else {
                    viewPager2.setCurrentItem(viewPager2.getCurrentItem() - 1);
                }
                // Handle the back button event
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getActivity(), callback);

        // The callback can be enabled or disabled here or in handleOnBackPressed()


        return view;

    }
/*
    @Override
    public void onBackPressed() {
        if (viewPager2.getCurrentItem() == 0) {
            super.onBackPressed();
        }
        else {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() - 1);
        }
    }*/




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