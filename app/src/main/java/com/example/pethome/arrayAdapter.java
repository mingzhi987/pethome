package com.example.pethome;

import static androidx.core.content.ContextCompat.startActivity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.unity3d.player.UnityPlayerActivity;

//import com.unity3d.player.UnityPlayerActivity;

import java.util.List;

/**
 * Created by manel on 9/5/2017.
 */

public class arrayAdapter extends ArrayAdapter<Pet>{

    //test push code
    Context context;

    public arrayAdapter(Context context, int resourceId, List<Pet> items){
        super(context, resourceId, items);
    }

    private void openAr(){
        Intent i = new Intent(getContext(), UnityPlayerActivity.class);
        //send data to unity
        i.putExtra("result","some Data");
        startActivity(getContext(),i,null);

    }

    public View getView(int position, View convertView, ViewGroup parent){
        Pet pet_item = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        ImageButton arBtn = (ImageButton) convertView.findViewById(R.id.arBtn);
        TextView name = (TextView) convertView.findViewById(R.id.petname);
        TextView age = (TextView) convertView.findViewById(R.id.petage);
        TextView info = (TextView) convertView.findViewById(R.id.petinfo);
        ImageView image = (ImageView) convertView.findViewById(R.id.petimage);

        name.setText(pet_item.getName());
        age.setText(pet_item.getAge().toString() + " months");
        if(pet_item.isVaccine()) {
            info.setText(pet_item.getGender() + ", " + pet_item.getBreed() + ", Vaccinated");
        } else {
            info.setText(pet_item.getGender() + ", " + pet_item.getBreed() + ", Non-vaccinated");
        }
        switch(pet_item.getImageUrl()){
            case "default":
                Glide.with(convertView.getContext()).load(R.mipmap.ic_launcher).into(image);
                break;
            default:
                Glide.with(convertView.getContext()).clear(image);
                Glide.with(convertView.getContext()).load(pet_item.getImageUrl()).into(image);
                break;
        }

        arBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAr();
            }
        });


        return convertView;

    }
}