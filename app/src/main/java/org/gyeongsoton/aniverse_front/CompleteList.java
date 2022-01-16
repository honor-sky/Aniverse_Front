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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import org.json.JSONException;
import org.json.JSONObject;

public class CompleteList extends Fragment {

    private ImageView ani_img1;
    private TextView ani_info1;
    String animalImage,animalSpecies,animalAge,adoptDate,userIdx,userAuth; //입양완료 띄우는거
    AnimalList animalList;

    //프래그먼트를 액티비티 위에 올린다.
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        animalList = (AnimalList) getActivity();
        System.out.println("완료: onAttach 실행");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("완료: onDestroyView 실행");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("완료: onDestroy 실행");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        ViewGroup view= (ViewGroup) inflater.inflate(R.layout.fragment_completelist, container, false);
        System.out.println("완료: onCreateView 실행");

        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());//예외처리 핸들러

        ani_img1=view.findViewById(R.id.ani_img1);
        ani_info1=view.findViewById(R.id.ani_info1);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonResponse = new JSONObject(response); //서버 응답 받아 json 파일 받아옴
                    boolean success = jsonResponse.getBoolean("isSuccess");
                    if (success) {
                        System.out.println("성공");

                        //animalIdx = jsonResponse.getInt("animalIdx");  //세부화면으로 넘어갈 때 필요
                        animalImage = jsonResponse.getString("animalImage");
                        animalSpecies=jsonResponse.getString("animalSpecies");
                        animalAge=jsonResponse.getString("animalAge");

                        System.out.println(animalImage); //이미지 url 확인
                        /*이미지 로드 및 셋팅 백그라운드 실행*/
                        //ImageLoadTask task = new ImageLoadTask(animalImage, ani_img1);
                        //task.execute();

                        //물 정보 셋팅
                        ani_info1.setText(animalSpecies+" "+animalAge+"세");
                        Glide.with(CompleteList.this).load(animalImage).into(ani_img1);
                        ani_img1.setClipToOutline(true);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        animallist_Request request = new animallist_Request("S", responseListener);
        RequestQueue queue = Volley.newRequestQueue(container.getContext());
        queue.add(request);

        ani_img1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), AnimalMonitorTotal.class);
                //intent.putExtra( "animalIdx", Integer.toString(animalIdx));
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        System.out.println("완료: onViewCreated 실행 : before super");
        super.onViewCreated(view, savedInstanceState);
        System.out.println("완료: onViewCreated 실행 : after super");

    }
}