package org.gyeongsoton.aniverse_front;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class Main extends AppCompatActivity {

    private BottomNavigationView mBottomNV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        mBottomNV = findViewById(R.id.nav_view);
        mBottomNV.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() { //NavigationItemSelecte
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                BottomNavigate(menuItem.getItemId());


                return true;
            }
        });
        mBottomNV.setSelectedItemId(R.id.home_btn); //기본 화면
    }

    private void BottomNavigate(int id) {  //BottomNavigation 페이지 변경
        String tag = String.valueOf(id);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment currentFragment = fragmentManager.getPrimaryNavigationFragment(); //현재 프래그먼트
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment); //현재 프래그먼트 숨기기
        }

        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment == null) { //프래그먼트 한 번도 생성 안 된 경우
            if (id == R.id.home_btn) {
                //fragment = new Home();

            } else if (id == R.id.adopt_btn){
                //fragment = new FundingList();

            }else if(id==R.id.funding_btn){
                fragment = new FundingList();

            }
            else{
                //fragment = new FragmentPage3();
            }

            fragmentTransaction.add(R.id.content_layout, fragment, tag); //현재 액티비티에 생성한 프래그먼트 추ㅏㄱ

        } else {  //이미 생성된 프래그먼트면 다시 보이기
            fragmentTransaction.show(fragment);
        }

        fragmentTransaction.setPrimaryNavigationFragment(fragment);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitNow();




    }
}