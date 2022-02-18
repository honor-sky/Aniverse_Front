package org.gyeongsoton.aniverse_front;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

// 동물 모니터링
// 후기, 소식 작성
public class AnimalMonitorReview extends Fragment {

    ImageView imageView1;
    TextView textView2;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        ViewGroup view= (ViewGroup) inflater.inflate(R.layout.fragment_animalmonitorreview, container, false);

        imageView1=view.findViewById(R.id.review_img1);
        textView2=view.findViewById(R.id.review_text1);

        Bundle bundle = getArguments();
        String adoptReviewFile2 = bundle.getString("adoptReviewFile2");
        String adoptReviewText = bundle.getString("adoptReviewText");

        Glide.with(AnimalMonitorReview.this).load(adoptReviewFile2).into(imageView1);
        imageView1.setClipToOutline(true);
        textView2.setText(adoptReviewText);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}