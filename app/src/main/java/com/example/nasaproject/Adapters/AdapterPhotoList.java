package com.example.nasaproject.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nasaproject.API.PhotoDTO;
import com.example.nasaproject.PhotoActivity;
import com.example.nasaproject.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterPhotoList extends RecyclerView.Adapter<AdapterPhotoList.ViewHolderPhotoList> {

    ArrayList<PhotoDTO> photoDTOS = new ArrayList<>();

    public void SetData(List<PhotoDTO> photoDTOS) {
        this.photoDTOS.clear();
        this.photoDTOS.addAll(photoDTOS);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolderPhotoList onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listphoto, parent, false);
        return new ViewHolderPhotoList(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPhotoList holder, int position) {
        holder.bind(photoDTOS.get(position));
    }

    @Override
    public int getItemCount() {
        return photoDTOS.size();
    }

    public class ViewHolderPhotoList extends RecyclerView.ViewHolder {

        TextView textforTime;

        PhotoDTO photo;

        public ViewHolderPhotoList(@NonNull View itemView) {
            super(itemView);
            textforTime = itemView.findViewById(R.id.time);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PhotoActivity.start(v.getContext(), photo.getImageUrl());
                }
            });
        }

        public void bind(PhotoDTO photo) {
            this.photo = photo;
            textforTime.setText(photo.getDate());
        }
    }
}
