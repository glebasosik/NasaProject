package com.example.nasaproject.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nasaproject.API.DateDTO;
import com.example.nasaproject.PhotoListActivity;
import com.example.nasaproject.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterDates extends RecyclerView.Adapter<AdapterDates.ViewHolderDates> {

    ArrayList<DateDTO> dates = new ArrayList<>();

    public void setDates(List<DateDTO> dates) {
        this.dates.clear();
        this.dates.addAll(dates);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolderDates onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_date, parent, false);
        return new ViewHolderDates(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDates holder, int position) {
        holder.bind(dates.get(position));
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }

    public class ViewHolderDates extends RecyclerView.ViewHolder {

        DateDTO dateDTO;

        TextView textforDate;

        public ViewHolderDates(@NonNull View itemView) {
            super(itemView);
            textforDate = itemView.findViewById(R.id.date);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PhotoListActivity.start(v.getContext(), dateDTO.getDate());
                }
            });
        }

        public void bind(DateDTO date) {
            this.dateDTO = date;
            textforDate.setText(date.getDate());
        }
    }
}
