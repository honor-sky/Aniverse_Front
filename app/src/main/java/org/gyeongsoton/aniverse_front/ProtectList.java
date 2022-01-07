package org.gyeongsoton.aniverse_front;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class ProtectList extends Fragment {

    private ImageView ani_img1;
    private TextView ani_info1;

    String Image, Info;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        ViewGroup view= (ViewGroup) inflater.inflate(R.layout.fragment_protectlist, container, false);

        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
        //인플레이션: xml에 있는 코드들이 메모리에 올라가고 메모리에서 UI가 출력
        //fragment_adopt_protect가 Adopt_list의 framelayout 위로 올라감

        //Bundle bundle = getArguments();

        ani_info1=view.findViewById(R.id.ani_info1);
        ani_img1=view.findViewById(R.id.ani_img1);

        ani_info1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), ProtectDetail.class);
                startActivity(intent);
            }
        });

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonResponse = new JSONObject(response); //서버 응답 받아 json 파일 받아옴
                    boolean success = jsonResponse.getBoolean("isSuccess");
                    if (success) {
                        System.out.println("성공");

                        //DB에서 가져온 이미지(종, 나이) 개수 만큼 배열로 객체 생성
                        //ImageView[] img = new TextView[emotionCommentsArrayList.size()];

                        Image = jsonResponse.getString("protectImage");
                        Info=jsonResponse.getString("animalSpecies");

                        ani_info1.setText(Info);
                        Glide.with(ProtectList.this).load(Image).into(ani_img1);
                        ani_img1.setClipToOutline(true);
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
