package org.gyeongsoton.aniverse_front;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

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

public class Home extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        TableLayout layout[] = {(TableLayout) view.findViewById(R.id.tablelayout1), (TableLayout) view.findViewById(R.id.tablelayout2), (TableLayout) view.findViewById(R.id.tablelayout3)};
        TableRow row[] = new TableRow[3];


        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray jArray = new JSONArray(response); //서버 응답 받아 json 파일 받아옴
                    boolean success = jArray.getJSONObject(0).getBoolean("isSuccess"); //제대로 받아왔는지 확인
                    if (success) {
                        System.out.println("성공");

                        for (int i = 1; i < jArray.length(); i++) {
                            JSONObject jobj = jArray.getJSONObject(i); //adopt, fund, market 차례대로 접근
                            ImageView img[] = new ImageView[jobj.length()];  //가져온 이미지 개수만큼 이미지뷰 만듬
                            /*이미지뷰의 속성 설정*/
                            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(100, 100);
                            layoutParams.leftMargin = 10;

                            for (int j = 0; j < jobj.length(); j++) {
                                img[j].setLayoutParams(layoutParams); //이미지뷰의 속성 변경
                                //img[j].setLayoutParams(new LinearLayout.LayoutParams(100,100)); //이미지뷰 크기 설정
                                row[i - 1].addView(img[j]); //TableRow에 이미지뷰 부착
                                Glide.with(getContext()).load(jobj.getString("img")).into(img[j]); //이미지 셋팅

                            }
                            layout[i - 1].addView(row[i - 1]); //tableLayout에 TableRow 부착

                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        main_Request mainrequest = new main_Request(responseListener); //임시보호 진행중
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(mainrequest);

        return view;
    }
}
