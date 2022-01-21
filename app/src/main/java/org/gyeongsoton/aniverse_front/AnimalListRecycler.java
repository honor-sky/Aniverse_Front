package org.gyeongsoton.aniverse_front;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
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

        mList = new ArrayList<>();
        //어댑터 객체
        aniAdapter = new AnimalListRecycleAdapter(mList);
        //리사이클러뷰 객체
        recyclerView = (RecyclerView) findViewById(R.id.aniRecyclerView);
        recyclerView.setAdapter(aniAdapter);
        //레이아웃 지정
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        for (int i =0; i<10; i++){
            AnimalListRecyclerItem item= new AnimalListRecyclerItem();

            //각 list의 값들을 객체에 set해줌
            item.setImage(R.drawable.img_square);
            item.setInfo(i+1+"번째 동물");

            //각 값이 들어간 data를 adapter에 추가
            aniAdapter.setArrayData(item);
        }
        //getData();
    }
    /*
    private void getData() {
        // 임의의 데이터입니다.
        List<String> listTitle = Arrays.asList("뭉치", "뽀송", "짱이", "또또", "별님", "두부", "초코", "파이",
                "호두", "마루", "구름", "코코", "멍멍", "초롱", "팽이", "루루");

        List<Integer> listResId = Arrays.asList(
                R.drawable.img_square,
                R.drawable.round_squre_nocol,
                R.drawable.img_square,
                R.drawable.round_squre_nocol,
                R.drawable.img_square,
                R.drawable.round_squre_nocol,
                R.drawable.img_square,
                R.drawable.round_squre_nocol,
                R.drawable.img_square,
                R.drawable.round_squre_nocol,
                R.drawable.img_square,
                R.drawable.round_squre_nocol,
                R.drawable.img_square,
                R.drawable.round_squre_nocol,
                R.drawable.img_square,
                R.drawable.round_squre_nocol
        );

        for ( int i =0; i<10; i++){
            AnimalListRecyclerItem item= new AnimalListRecyclerItem();
            //각 list의 값들을 객체에 set해줌
            item.setImage(listResId.get(i));
            item.setInfo(listTitle.get(i));

            //각 값이 들어간 data를 adapter에 추가
            aniAdapter.setArrayData(item);
        }
    }*/
}
