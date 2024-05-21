package com.example.birzeit_university_student.activity_tools;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.birzeit_university_student.R;
import com.example.birzeit_university_student.classes.Major;

import java.util.List;

public class MajorAdapter extends RecyclerView.Adapter<MajorAdapter.ViewHolder> {
    private Context context;
    private List<Major> majors;
    private boolean circularScrolling = false;

    public MajorAdapter(Context context, List<Major> majors) {
        this.context = context;
        this.majors = majors;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_major,
                parent,
                false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Major major = majors.get(position % majors.size());
        CardView cardView = holder.cardView;
        ImageView imageView = cardView.findViewById(R.id.image);
        String major_img = major.getMajor_image();
        int resourceId = context.getResources().getIdentifier(major_img, "drawable", context.getPackageName());
        Drawable dr = ContextCompat.getDrawable(context, resourceId);
        imageView.setImageDrawable(dr);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle item click
            }
        });
    }

    @Override
    public int getItemCount() {
        if (circularScrolling && majors.size() > 1) {
            return Integer.MAX_VALUE;
        }
        return majors.size();
    }

    public void enableCircularScrolling(boolean enable) {
        circularScrolling = enable;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }
    }
}
