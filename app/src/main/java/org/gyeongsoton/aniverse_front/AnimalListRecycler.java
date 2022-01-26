package org.gyeongsoton.aniverse_front;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableRow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        //입양 버튼 눌렀을 때
        adopt_tab.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                adopt_tab.setPressed(true);
                protect_tab.setPressed(false);
                complete_tab.setPressed(false);
                makeRequest(1);

                return true;
            }
        });
        //임시보호 버튼 눌렀을 때
        protect_tab.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                adopt_tab.setPressed(false);
                protect_tab.setPressed(true);
                complete_tab.setPressed(false);

                makeRequest(2);

                return true;
            }
        });
        //입양 완료 버튼 눌렀을 때
        complete_tab.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                adopt_tab.setPressed(false);
                protect_tab.setPressed(false);
                complete_tab.setPressed(true);

                makeRequest(3);

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

    public void makeRequest(int num){

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("isSuccess"); //response 제대로 왔는지 확인

                    if (success) {
                        System.out.println("성공");

                        JSONArray respArr = (JSONArray) jsonResponse.get("adoptListRows");
                        System.out.println(respArr.length());


                        mList = new ArrayList<>();
                        //어댑터 객체
                        aniAdapter = new AnimalListRecycleAdapter(mList);
                        //리사이클러뷰 객체
                        recyclerView = (RecyclerView) findViewById(R.id.aniRecyclerView);
                        recyclerView.setAdapter(aniAdapter);
                        //레이아웃 지정
                        recyclerView.setLayoutManager(new GridLayoutManager(AnimalListRecycler.this, 2));

                        for(int i=0;i<respArr.length();i++){
                            AnimalListRecyclerItem item= new AnimalListRecyclerItem();
                            JSONObject obj = null;
                            try {
                                obj = (JSONObject)respArr.get(i);
                                //item.setImage(obj.getInt("animalImage"));
                                //item.setImage(R.drawable.img_square);
                                //item.setInfo(i+" 동물");
                                System.out.println(obj.getString("animalSpecies"));
                                item.setInfo(obj.getString("animalSpecies")+" "+obj.getString("animalAge"));
                                aniAdapter.setArrayData(item);
                                /*백그라운드 실행*/
                                //ImageLoadTask task = new ImageLoadTask(animalImage, ani_img1,AdoptList.this);
                                //task.execute();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        if (num==1){
            animallist_Request request = new animallist_Request("S",responseListener);
            RequestQueue queue = Volley.newRequestQueue(AnimalListRecycler.this);
            queue.add(request);
        }
        else if(num==2){
            protectlist_Request request= new protectlist_Request(responseListener); //임시보호 진행중
            RequestQueue queue = Volley.newRequestQueue(AnimalListRecycler.this);
            queue.add(request);
        }
        else{
            animallist_Request request = new animallist_Request("Y",responseListener);
            RequestQueue queue = Volley.newRequestQueue(AnimalListRecycler.this);
            queue.add(request);
        }
    }
}
