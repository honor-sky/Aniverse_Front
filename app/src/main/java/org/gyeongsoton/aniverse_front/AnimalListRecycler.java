package org.gyeongsoton.aniverse_front;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnimalListRecycler extends AppCompatActivity {
    RecyclerView recyclerView;
    AnimalListRecycleAdapter aniAdapter;
    ArrayList<AnimalListRecyclerItem> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animallistrecycler);

        //recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button adopt_tab = (Button)findViewById(R.id.adopt_tab);
        Button protect_tab = (Button)findViewById(R.id.protect_tab);
        Button complete_tab = (Button)findViewById(R.id.complete_tab);

        adopt_tab.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                adopt_tab.setPressed(true);
                protect_tab.setPressed(false);
                complete_tab.setPressed(false);

                recyclerAdapter();

                for (int i =0; i<20; i++){
                    AnimalListRecyclerItem item= new AnimalListRecyclerItem();

                    //각 list의 값들을 객체에 set해줌
                    item.setImage(R.drawable.img_square);
                    item.setInfo("입양 "+i+"번째 동물");

                    //각 값이 들어간 data를 adapter에 추가
                    aniAdapter.setArrayData(item);
                }
                return true;
            }
        });

        protect_tab.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                adopt_tab.setPressed(false);
                protect_tab.setPressed(true);
                complete_tab.setPressed(false);

                recyclerAdapter();

                for (int i =0; i<20; i++){
                    AnimalListRecyclerItem item= new AnimalListRecyclerItem();

                    //각 list의 값들을 객체에 set해줌
                    item.setImage(R.drawable.round_squre_nocol);
                    item.setInfo("임시보호 "+i+"번째 동물");

                    //각 값이 들어간 data를 adapter에 추가
                    aniAdapter.setArrayData(item);
                }
                return true;
            }
        });

        complete_tab.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                adopt_tab.setPressed(false);
                protect_tab.setPressed(false);
                complete_tab.setPressed(true);

                recyclerAdapter();

                for (int i =0; i<20; i++){
                    AnimalListRecyclerItem item= new AnimalListRecyclerItem();

                    //각 list의 값들을 객체에 set해줌
                    item.setImage(R.drawable.img_square);
                    item.setInfo("완료 "+i+"번째 동물");

                    //각 값이 들어간 data를 adapter에 추가
                    aniAdapter.setArrayData(item);
                }
                return true;
            }
        });

    }
    public void recyclerAdapter(){
        mList = new ArrayList<>();
        //어댑터 객체
        aniAdapter = new AnimalListRecycleAdapter(mList);
        //리사이클러뷰 객체
        recyclerView = (RecyclerView) findViewById(R.id.aniRecyclerView);
        recyclerView.setAdapter(aniAdapter);
        //레이아웃 지정
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }
}
