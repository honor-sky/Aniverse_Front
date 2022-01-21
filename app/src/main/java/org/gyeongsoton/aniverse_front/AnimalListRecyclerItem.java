package org.gyeongsoton.aniverse_front;

import android.graphics.drawable.Drawable;

// 추후 아이템에 어떤 값이 들어갈지
public class AnimalListRecyclerItem {
    private Integer animalImage;
    private String animalInfo;

    public void setImage(Integer img){
        animalImage=img;
    }

    public void setInfo(String info){
        this.animalInfo=info;
    }

    public Integer getImage(){
        return animalImage;
    }

    public String getInfo(){
        return animalInfo;
    }
}
