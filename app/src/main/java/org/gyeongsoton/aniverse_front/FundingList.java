package org.gyeongsoton.aniverse_front;

import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

// 펀딩 항목들이 리스트로 나타남
// 리사이클러뷰 사용
public class FundingList extends AppCompatActivity {

    RecyclerView recyclerView;
    ListRecycleAdapter fundAdapter;
    ArrayList<ListRecyclerItem> mList;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fundinglist);

        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler()); //예외처리 핸들러


        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("isSuccess"); //response 제대로 왔는지 확인

                    if (success) {
                        System.out.println("성공");

                        JSONArray respArr = (JSONArray) jsonResponse.get("fundingListRows");
                        System.out.println(respArr.length());

                        mContext = getApplicationContext();
                        mList = new ArrayList<>();
                        //어댑터 객체
                        fundAdapter = new ListRecycleAdapter(4,mContext,mList);
                        //리사이클러뷰 객체
                        recyclerView = (RecyclerView) findViewById(R.id.fundRecyclerView);
                        recyclerView.setAdapter(fundAdapter);
                        //레이아웃 지정
                        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

                        for(int i=0;i<respArr.length();i++){
                            ListRecyclerItem item= new ListRecyclerItem();
                            JSONObject obj = null;
                            try {
                                obj = (JSONObject)respArr.get(i);
                                item.setImage(obj.getString("fundingImage"));
                                item.setInfo(obj.getString("fundingName"));
                                fundAdapter.setArrayData(item);

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
        fundinglist_Request request= new fundinglist_Request(responseListener); //임시보호 진행중
        RequestQueue queue = Volley.newRequestQueue(FundingList.this);
        queue.add(request);
    }
}