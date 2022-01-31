package org.gyeongsoton.aniverse_front;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
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
import androidx.loader.content.CursorLoader;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public class AdoptList extends Fragment {


    //굳이 재사용될 필요 없는 변수들은 그냥 메모리 공간 고정 시켜넣고 값만 변경시키도록 static으로 만드는게 좋다고 함
    private static ImageView ani_img1;
    private static TextView ani_info1;
    private static String animalImage, animalSpecies, animalAge;
    private static int adoptListIdx;
    private Map<Object,Integer> items = new HashMap<>();
    //AnimalList animalList;
    TableLayout tablelayout;
    TableRow tableRow;

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };

        CursorLoader cursorLoader = new CursorLoader(getActivity(), contentUri, proj, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();

        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


    //프래그먼트를 액티비티 위에 올린다.
   /* @Override
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
    }*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_adoptlist, container, false); //프래그먼트 레이아웃을 클래스에 올려줌
        //System.out.println("입양: onCreateView 실행");
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler()); //예외처리 핸들러



        tablelayout = (TableLayout)view.findViewById(R.id.tablelayout1); //TableLayout

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("isSuccess"); //reponse 제대로 왔는지 확인

                    if (success) {

                        //데이터 배열 전체 파싱
                        JSONArray respArr = (JSONArray) jsonResponse.get("adoptListRows");
                        System.out.println(respArr.length());
                        //반복문으로 하나하나 파싱
                        for(int i=0;i<respArr.length();i++){
                            JSONObject obj = (JSONObject)respArr.get(i); //데이터 원소 하나하나 가져옴

                            adoptListIdx = obj.getInt("adoptListIdx");
                            animalImage =  obj.getString("animalImage");
                            animalSpecies = obj.getString("animalSpecies");
                            animalAge = obj.getString("animalAge");
                            /* 이미지 로드 백그라운드 실행*/
                            //ImageLoadTask task = new ImageLoadTask(animalImage, ani_img1,AdoptList.this);
                            //task.execute();

                            if(i%2==0) {//짝수번째 데이터 로드 시 row 새로 만듬
                                //System.out.println("new");
                                tableRow = new TableRow(getContext());
                                tableRow.setLayoutParams(new TableRow.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT,160));

                            }

                            ListLayout listLayout = new ListLayout(getContext()); //item 객체 새로 만듬(레이아웃 형태로 만듬) //static 변수로 만들면?...

                            //동물정보 셋팅
                            ani_info1 = listLayout.findViewById(R.id.ani_info1);
                            ani_img1 = listLayout.findViewById(R.id.ani_img1);

                            ani_img1.setTag(i);
                            items.put(ani_img1.getTag(),adoptListIdx); //hashmap에 이미지뷰의 Tag와 동물 인덱스 저장

                            ani_info1.setText(animalSpecies + " " + animalAge + "세");
                            System.out.println(animalImage);
                            Glide.with(AdoptList.this).load(animalImage).into(ani_img1); //백그라운드 처리 시 주석처리
                            ani_img1.setClipToOutline(true);




                             //백그라운드 처리 시 주석처리
                            /*try{
                                InputStream in = getActivity().getContentResolver().openInputStream(animalImage);
                                Bitmap bitmap = BitmapFactory.decodeStream(in);
                                ani_img1.setImageBitmap(bitmap);
                            } catch (FileNotFoundException e){
                                e.printStackTrace();
                            }*/


                            ani_img1.setOnClickListener(new View.OnClickListener(){
                                @Override
                                public void onClick(View v){
                                    int idx= items.get(v.getTag()); //인덱스 출력
                                    Intent intent = new Intent(getActivity(), AdoptDetail.class);
                                    intent.putExtra("adoptListIdx",idx);
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
        animallist_Request request = new animallist_Request("S",responseListener);
        RequestQueue queue = Volley.newRequestQueue(container.getContext());
        queue.add(request);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //System.out.println("입양: onViewCreated 실행 : before super");
        super.onViewCreated(view, savedInstanceState);
        //System.out.println("입양: onViewCreated 실행 : after super");


    }
}

