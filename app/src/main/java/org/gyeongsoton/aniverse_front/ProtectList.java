package org.gyeongsoton.aniverse_front;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ProtectList extends Fragment {

    private ImageView ani_img1;
    private TextView ani_info1;
    private static String  animalImage, animalSpecies, animalAge;
    //private static int protectListIdx;
    private Map<Object,Integer> items = new HashMap<>();
    //AnimalList animalList;
    TableLayout tablelayout;
    TableRow tableRow;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        ViewGroup view= (ViewGroup) inflater.inflate(R.layout.fragment_protectlist, container, false);
        //System.out.println("임시보호: onCreateView 실행");

        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());//예외처리 핸들러

        tablelayout = (TableLayout)view.findViewById(R.id.tablelayout1); //TableLayout

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("isSuccess"); //reponse 제대로 왔는지 확인

                    if (success) {
                        System.out.println("성공");

                        //데이터 배열 전체 파싱
                        JSONArray respArr = (JSONArray) jsonResponse.get("getProtectListResult");
                        System.out.println(respArr.length());
                        //반복문으로 하나하나 파싱
                        for(int i=0;i<respArr.length();i++){
                            JSONObject obj = (JSONObject)respArr.get(i); //데이터 원소 하나하나 가져옴
                            //protectListIdx = obj.getInt("protectListIdx");
                            animalImage = obj.getString("animalImage");
                            animalSpecies = obj.getString("animalSpecies");
                            animalAge = obj.getString("animalAge");
                            /* 이미지 로드 백그라운드 실행*/
                            //ImageLoadTask task = new ImageLoadTask(animalImage, ani_img1,AdoptList.this);
                            //task.execute();

                            if(i%2==0) {//짝수번째 데이터 로드 시 row 새로 만듬
                                tableRow = new TableRow(getContext());
                                tableRow.setLayoutParams(new TableRow.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT,160));

                            }

                            ListLayout listLayout = new ListLayout(getContext()); //item 객체 새로 만듬(레이아웃 형태로 만듬) //static 변수로 만들면?...

                            //동물정보 셋팅
                            ani_info1 = listLayout.findViewById(R.id.ani_info1);
                            ani_img1 = listLayout.findViewById(R.id.ani_img1);

                            //ani_img1.setTag(i);
                            //items.put(ani_img1.getTag(),adoptListIdx); //hashmap에 이미지뷰의 Tag와 동물 인덱스 저장

                            ani_info1.setText(animalSpecies + " " + animalAge + "세");
                            Glide.with(ProtectList.this).load(animalImage).into(ani_img1); //백그라운드 처리 시 주석처리
                            ani_img1.setClipToOutline(true); //백그라운드 처리 시 주석처리


                            ani_img1.setOnClickListener(new View.OnClickListener(){
                                @Override
                                public void onClick(View v){
                                    int idx= items.get(v.getTag()); //인덱스 출력
                                    Intent intent = new Intent(getActivity(), ProtectDetail.class);
                                    //intent.putExtra("protectListIdx",idx);
                                    startActivity(intent);
                                }
                            });

                            //row에 item 추가
                            tableRow.addView(listLayout);

                            if(i%2==0) {//row 새로 만들면 tablelayout에 추가
                                tablelayout.addView(tableRow);
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
        //System.out.println("임시보호: onViewCreated 실행 : before super");
        super.onViewCreated(view, savedInstanceState);
        //System.out.println("임시보호: onViewCreated 실행 : after super");

    }
}
