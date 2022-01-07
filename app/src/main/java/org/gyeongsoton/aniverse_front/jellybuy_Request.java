package org.gyeongsoton.aniverse_front;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class jellybuy_Request extends StringRequest {

    final static private String URL = "";
    private Map<String, String> map;

    public jellybuy_Request( String purchaseJellyNum, String purchaseJellyWay, Response.Listener<String> listener) {
        super(Method.POST,URL, listener, null);

        map = new HashMap<>();
        map.put("purchaseJellyNum", purchaseJellyNum);
        map.put("purchaseJellyWay", purchaseJellyWay);

    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}