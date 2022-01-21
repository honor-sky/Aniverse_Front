package org.gyeongsoton.aniverse_front;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class AdoptList extends Fragment {

    private ImageView ani_img1;
    private TextView ani_info1;
    String animalImage, animalSpecies, animalAge;
    AnimalList animalList;
    TableLayout tablelayout;
    ListLayout list_layout;
    TableRow tableRow;


    //프래그먼트를 액티비티 위에 올린다.
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        animalList = (AnimalList) getActivity();
        System.out.println("입양: onAttach 실행");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("입양: onDestroyView 실행");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("입양: onDestroy 실행");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_adoptlist, container, false); //프래그먼트 레이아웃을 클래스에 올려줌
        System.out.println("입양: onCreateView 실행");

        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler()); //예외처리 핸들러

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
                        JSONArray respArr = (JSONArray) jsonResponse.get("adoptListRows");
                        System.out.println(respArr.length());

                        //반복문으로 하나하나 파싱
                        for(int i=0;i<respArr.length();i++){
                            JSONObject obj = (JSONObject)respArr.get(i); //데이터 원소 하나하나 가져옴
                            animalImage = obj.getString("animalImage");
                            animalSpecies = obj.getString("animalSpecies");
                            animalAge = obj.getString("animalAge");
                            /*백그라운드 실행*/
                            //ImageLoadTask task = new ImageLoadTask(animalImage, ani_img1,AdoptList.this);
                            //task.execute();

                            if(i%2==0) {//짝수번째 데이터 로드 시 row 새로 만듬
                                System.out.println("new");
                                tableRow = new TableRow(getContext());
                                tableRow.setLayoutParams(new TableRow.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT,160));

                            }

                            ListLayout list_layout = new ListLayout(getContext()); //item 객체 새로 만듬(레이아웃 형태로 만듬)

                            //동물정보 셋팅
                            ani_info1 = list_layout.findViewById(R.id.ani_info1);
                            ani_img1 = list_layout.findViewById(R.id.ani_img1);
                            ani_info1.setText(animalSpecies + " " + animalAge + "세");
                            Glide.with(AdoptList.this).load(animalImage).into(ani_img1); //백그라운드 처리 시 주석처리
                            ani_img1.setClipToOutline(true); //백그라운드 처리 시 주석처리

                            //row에 item 추가
                            tableRow.addView(list_layout);

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
        animallist_Request request = new animallist_Request("S",responseListener);
        RequestQueue queue = Volley.newRequestQueue(container.getContext());
        queue.add(request);

        //이미지 누르면 세부화면으로 전환
     /*   ani_img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AdoptDetail.class);
                startActivity(intent);
            }
        });*/


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        System.out.println("입양: onViewCreated 실행 : before super");
        super.onViewCreated(view, savedInstanceState);
        System.out.println("입양: onViewCreated 실행 : after super");


    }
}

