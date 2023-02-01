package com.example.githubrepositories;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.repoHolder> {
    MainActivity mainActivity;
    ArrayList<RepoModel> ownerData;
    public RepoListAdapter(MainActivity mainActivity, ArrayList<RepoModel> ownerData) {
        this.mainActivity=mainActivity;
        this.ownerData=ownerData;
    }

    @NonNull
    @Override
    public repoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new repoHolder(LayoutInflater.from(mainActivity).inflate(R.layout.item_repo,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull repoHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(ownerData.get(position).getName());
        holder.description.setText(ownerData.get(position).getFull_name());
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,ownerData.get(position).html_url);
                intent.setType("text/pain");
                if(intent.resolveActivity(mainActivity.getPackageManager())!=null){
                    mainActivity.startActivity(intent);
                }

            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(mainActivity, WebViewActivity.class);
                Bundle data=new Bundle();
                data.putString("url",ownerData.get(position).html_url);
                intent.putExtras(data);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mainActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ownerData.size();
    }

    class repoHolder extends RecyclerView.ViewHolder{
        TextView title,description;
        ImageView itemImage,share;
        CardView cardView;
        public repoHolder(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.cardView);
            title=itemView.findViewById(R.id.itemTitle);
            description=itemView.findViewById(R.id.itemDescription);
            itemImage=itemView.findViewById(R.id.itemImage);
            share=itemView.findViewById(R.id.share);
        }
    }
}
