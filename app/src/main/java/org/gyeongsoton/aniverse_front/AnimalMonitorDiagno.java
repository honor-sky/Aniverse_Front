package org.gyeongsoton.aniverse_front;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

// 동물 모니터링
// 건강검진 결과 작성
public class AnimalMonitorDiagno extends Fragment {

    ImageView imageView1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        ViewGroup view= (ViewGroup) inflater.inflate(R.layout.fragment_animalmonitordiagno, container, false);

        imageView1=view.findViewById(R.id.diagno1);

        Bundle bundle = getArguments();
        String adoptReviewFile1 = bundle.getString("adoptReviewFile1");
        Glide.with(AnimalMonitorDiagno.this).load(adoptReviewFile1).into(imageView1);
        imageView1.setClipToOutline(true);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}