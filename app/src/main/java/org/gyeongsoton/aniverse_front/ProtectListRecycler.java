package org.gyeongsoton.aniverse_front;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ProtectListRecycler extends Fragment {

    private ImageView ani_img1;
    private TextView ani_info1;
    String animalImage, animalSpecies, animalAge;
    AnimalListRecyclerFragment animalListrecyclerfragment;
    RecyclerView recyclerView;
    AnimalListRecycleAdapter aniAdapter;
    ArrayList<AnimalListRecyclerItem> mList;
    private Context mContext;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_animallistrecycler, container, false); //프래그먼트 레이아웃을 클래스에 올려줌
        System.out.println("입양: onCreateView 실행");

        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler()); //예외처리 핸들러

        //현재 상태 저장, 새 리스트
        mContext = getContext();
        mList = new ArrayList<>();
        //어댑터 객체 (현재 상태와 리스트 전달)
        aniAdapter = new AnimalListRecycleAdapter(mContext,mList);
        //리사이클러뷰 객체
        recyclerView = (RecyclerView) view.findViewById(R.id.aniRecyclerView);
        recyclerView.setAdapter(aniAdapter);
        //레이아웃 지정
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("isSuccess"); //response 제대로 왔는지 확인

                    if (success) {
                        System.out.println("성공");

                        JSONArray respArr = (JSONArray) jsonResponse.get("getProtectListResult");

                        mList = new ArrayList<>();//어댑터 객체
                        aniAdapter = new AnimalListRecycleAdapter(mContext,mList);  //리사이클러뷰 객체
                        recyclerView = (RecyclerView) view.findViewById(R.id.aniRecyclerView);
                        recyclerView.setAdapter(aniAdapter);
                        recyclerView.setLayoutManager(new GridLayoutManager(container.getContext(), 2)); //레이아웃 지정

                        for(int i=0;i<respArr.length();i++){
                            AnimalListRecyclerItem item= new AnimalListRecyclerItem();

                            try {
                                JSONObject obj = (JSONObject)respArr.get(i);
                                System.out.println(obj.getString("animalImage"));
                                item.setImage(obj.getString("animalImage"));
                                item.setInfo(obj.getString("animalSpecies")+" "+obj.getString("animalAge")+"세");
                                aniAdapter.setArrayData(item);

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
        protectlist_Request request= new protectlist_Request(responseListener); //임시보호 진행중
        RequestQueue queue = Volley.newRequestQueue(container.getContext());
        queue.add(request);



        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}