package com.example.sample;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewholder> {
    ArrayList<Category> items;
    Context context;

    public CategoryAdapter(ArrayList<Category>items) { this.items=items; }

    @NonNull
    @Override
    public CategoryAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category, parent,  false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.viewholder holder, int position) {
        holder.titleTxt.setText(items.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ListActivity.class);
                intent.putExtra("CategoryId",items.get(position).getId());
                intent.putExtra("CategoryName",items.get(position).getName());
                context.startActivity(intent);
            }
        });
        int leftDrawableResId = 0;

        switch(position) {
            case 0: {
                holder.pic.setBackgroundResource(R.drawable.back);
                leftDrawableResId = R.drawable.back;
                break;
            }
            case 1: {
                holder.pic.setBackgroundResource(R.drawable.heart);
                leftDrawableResId = R.drawable.heart;
                break;
            }
            case 2: {
                holder.pic.setBackgroundResource(R.drawable.home);
                leftDrawableResId = R.drawable.home;
                break;
            }
            case 3: {
                holder.pic.setBackgroundResource(R.drawable.price);
                leftDrawableResId = R.drawable.price;
                break;
            }
        }
        // Set left drawable
        if (leftDrawableResId != 0) {
            holder.titleTxt.setCompoundDrawablesWithIntrinsicBounds(leftDrawableResId, 0, 0, 0);
        }
        int drawableResourceId = context.getResources().getIdentifier(
                items.get(position).getImagePath(), "drawable",
                holder.itemView.getContext().getPackageName());

        Glide.with(context)
                .load(drawableResourceId)
                .into(holder.pic);
    }

    @Override
    public int getItemCount() { return items.size(); }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView titleTxt;
        ImageView pic;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            titleTxt=itemView.findViewById(R.id.title);
            pic=itemView.findViewById(R.id.imageView);

        }
    }
}
