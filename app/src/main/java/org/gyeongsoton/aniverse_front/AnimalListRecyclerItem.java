package org.gyeongsoton.aniverse_front;

import android.graphics.drawable.Drawable;
import android.net.Uri;

import java.net.URI;

// 추후 아이템에 어떤 값이 들어갈지
public class AnimalListRecyclerItem {
    private String animalImage;
    private String animalInfo;

    public void setImage(String img){
        animalImage=img;
    }

    public void setInfo(String info){
        this.animalInfo=info;
    }

    public String getImage(){ return animalImage; }

    public String getInfo(){
        return animalInfo;
    }
}