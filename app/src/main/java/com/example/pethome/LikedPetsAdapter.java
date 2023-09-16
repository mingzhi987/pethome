package com.example.pethome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LikedPetsAdapter extends RecyclerView.Adapter<LikedPetsAdapter.ViewHolder> {

    Context context;

    String [] names;

    String [] age;
    int [] images;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.liked_card, parent,false);
        ViewHolder viewholder = new ViewHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.leftImage.setImageResource(images[position]);
        holder.leftName.setText(names[position]);
        holder.leftAge.setText(age[position]);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView leftImage;
        TextView leftName;
        TextView leftAge;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            leftImage = itemView.findViewById(R.id.leftImage);
            leftName = itemView.findViewById(R.id.leftName);
            leftAge = itemView.findViewById(R.id.leftAge);
        }
    }

    public LikedPetsAdapter(Context context, String[] names, String[] age, int[] images) {
        this.context = context;
        this.names = names;
        this.age = age;
        this.images = images;
    }

}