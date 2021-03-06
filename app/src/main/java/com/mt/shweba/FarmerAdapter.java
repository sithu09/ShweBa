package com.mt.shweba;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class FarmerAdapter extends RecyclerView.Adapter<FarmerAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Upload> mUploads;

    public FarmerAdapter(Context context, List<Upload> uploads){
        mContext = context;
        mUploads=uploads;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.farmernews,parent,false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Upload uploadCurrent=mUploads.get(position);
        holder.textViewName.setText(uploadCurrent.getName());
        holder.textViewDate.setText(uploadCurrent.getDate());
        holder.textViewProposal.setText(uploadCurrent.getProposal());
        Picasso.get()
                .load(uploadCurrent.getImageUrl())
                .fit()
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public  class  ImageViewHolder extends  RecyclerView.ViewHolder{
        public TextView textViewName;
        public ImageView imageView;
        public TextView textViewDate,textViewProposal;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName=itemView.findViewById(R.id.text_view_name);
            textViewDate=itemView.findViewById(R.id.text_view_date);
            imageView=itemView.findViewById(R.id.image_view_upload);
            textViewProposal= itemView.findViewById(R.id.text_view_proposal);
        }
    }
}
