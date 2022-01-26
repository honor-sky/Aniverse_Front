package org.gyeongsoton.aniverse_front;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class ListLayout extends LinearLayout{


    public ListLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public ListLayout(Context context) {
        super(context);

        init(context);
    }
    private void init(Context context){
        LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_layout,this,true);
    }
}