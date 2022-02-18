package org.gyeongsoton.aniverse_front;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

// 리사이클러뷰 어댑터
public class ListRecycleAdapter extends RecyclerView.Adapter<ListRecycleAdapter.ViewHolder> {
    //어댑터에 들어갈 list
    private ArrayList<ListRecyclerItem> mData= null;
    private Context mContext;
    private Button adopt;

    // 아이템 뷰를 저장하는 뷰홀더 클래스
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ani_img;
        TextView ani_info;
        ListRecyclerItem item;

        ViewHolder(View itemView){
            super(itemView);

            // 뷰 객체에 대한 참조
            ani_img=itemView.findViewById(R.id.ani_img);
            ani_info=itemView.findViewById(R.id.ani_info);
        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음
    public ListRecycleAdapter(Context mContext,ArrayList<ListRecyclerItem> data){
        mData = data;
        this.mContext = mContext;
    }

    // 아이템 뷰를 위한 뷰홀더 객체를 생성하여 리턴
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //layoutinflater를 이용하여 항목 xml을 inflate 시킴
        View view = inflater.inflate(R.layout.recyclerview_animallistitem,parent,false);
        ListRecycleAdapter.ViewHolder vh = new ListRecycleAdapter.ViewHolder(view);

        //ViewHolder로 리턴
        return vh;
    }

    //position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    //item 하나하나를 표시
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListRecyclerItem item = mData.get(position);

        Glide.with(mContext).load(item.getImage()).into(holder.ani_img);
        holder.ani_img.setClipToOutline(true);
        holder.ani_info.setText(item.getInfo());

        System.out.println("버튼!!!!!!!"+adopt);

        // 이미지를 클릭하면 세부화면으로 이동
        holder.ani_img.setOnClickListener(new View.OnClickListener() {
            Intent intent;
            @Override
            public void onClick(View v) {
                if (adopt.isPressed()){
                    intent = new Intent(v.getContext(), AdoptDetail.class);}
                v.getContext().startActivity(intent);
            }
        });

    }

    //RecyclerView의 총 개수
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setArrayData(ListRecyclerItem item){
        mData.add(item);
    }
}
