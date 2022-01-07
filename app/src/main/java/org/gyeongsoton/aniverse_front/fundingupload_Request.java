package org.gyeongsoton.aniverse_front;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class fundingupload_Request extends StringRequest{

    final static private String URL = "http://3.36.175.200/funding";
    private Map<String, String> map;


    public fundingupload_Request(String fund_title, String fund_obj, String fund_price,
                                 String fund_term, Response.Listener<String> listener) {
        super(Method.POST,URL, listener, null);

        map = new HashMap<>();
        map.put("fundingName", fund_title);
        map.put("fundingPurpose", fund_obj);
        map.put("fundingAmount", fund_price);
        map.put("fundingPeriod", fund_term);

    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}