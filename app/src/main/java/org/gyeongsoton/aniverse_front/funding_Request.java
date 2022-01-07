package org.gyeongsoton.aniverse_front;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

//젤리 기부를 요청하는 클래스
public class funding_Request extends StringRequest {
    final static private String URL = "";
    private Map<String, String> map;


    public funding_Request(String donateJellyIDX, String fundingIDX, String userIDX,String jellyNum,Response.Listener<String> listener) {
        super(Method.POST,URL, listener, null);

        map = new HashMap<>();
        map.put("donateJellyIdx", donateJellyIDX);
        map.put("fundingIdx", fundingIDX);
        map.put("userIdx", userIDX);
        map.put("donateJellyNum", jellyNum);
    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}