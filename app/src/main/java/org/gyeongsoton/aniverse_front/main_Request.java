package org.gyeongsoton.aniverse_front;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

public class main_Request extends StringRequest {
    final static private String URL="http://3.36.175.200/main";

    public main_Request( Response.Listener<String> listener){
        super(Method.GET, URL, listener,null);

    }

}
