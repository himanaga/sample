package com.example.sample;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation. NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import java.util.ArrayList;

public class FarmsAdapter extends RecyclerView.Adapter<FarmsAdapter.viewholder> {
    ArrayList<Farm> items;
    Context context;

    public FarmsAdapter(ArrayList<Farm> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public FarmsAdapter.viewholder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_item,parent,false);
        return new viewholder(inflate);
    }
    @Override
    public void onBindViewHolder (@NonNull FarmsAdapter.viewholder holder, int position) {
        holder.titleTxt.setText(items.get(position).getTitle());
        holder.priceTxt.setText("₹"+items.get(position).getPrice());
        holder.starTxt.setText(String.valueOf(items.get(position).getStar()));
        Glide.with(context)
                .load(items.get(position).getImagePath())
                .transform(new CenterCrop(),new RoundedCorners(30))
                .into(holder.pic);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,DetailActivity.class);
                intent.putExtra("items",items.get(position));
                context.startActivity( intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return items.size();
    }
    public class viewholder extends RecyclerView.ViewHolder {
        TextView titleTxt,priceTxt,starTxt;
        ImageView pic;
        public viewholder(@NonNull View itemView){
            super(itemView);
            titleTxt =itemView.findViewById(R.id.titletxt);
            priceTxt = itemView.findViewById(R.id.pricetxt);
            starTxt = itemView.findViewById(R.id.startxt);
            pic = itemView.findViewById(R.id.imageView3);
        }


    }
}

