package org.gyeongsoton.aniverse_front;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class marketlist_Request extends StringRequest {
    //서버 url 설정(php파일 연동)
    final static  private String URL="http://3.36.175.200/market";
    private Map<String, String> map;

    public marketlist_Request(Response.Listener<String> listener){  //입양, 임시보호, 완료 함께 사용 예정 //서버에 데이터 요청 시 구분 인자 필요할듯
        super(Method.GET, URL, listener,null);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}