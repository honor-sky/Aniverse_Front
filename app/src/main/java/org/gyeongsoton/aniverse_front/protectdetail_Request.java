package org.gyeongsoton.aniverse_front;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class protectdetail_Request extends StringRequest {

    //서버 url 설정(php파일 연동)
    final static  private String URL="http://3.36.175.200/protect/info";
    private Map<String, String> map;

    public protectdetail_Request( Response.Listener<String> listener){
        super(Method.GET, URL, listener,null);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

}