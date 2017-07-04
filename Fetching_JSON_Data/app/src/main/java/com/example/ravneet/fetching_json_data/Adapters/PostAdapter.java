package com.example.ravneet.fetching_json_data.Adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ravneet.fetching_json_data.Interface.OnItemClickListner;
import com.example.ravneet.fetching_json_data.R;
import com.example.ravneet.fetching_json_data.models.Post;

import java.util.ArrayList;

/**
 * Created by ravneet on 1/7/17.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private Context context;
    private ArrayList<Post> posts;

    private OnItemClickListner onItemClickListner;

    public void setOnItemClickListner(OnItemClickListner onItemClickListner){
        this.onItemClickListner = onItemClickListner;
    }

    public PostAdapter(Context context, ArrayList<Post> posts){

        this.posts = posts;
        this.context = context;


    }

    public void updatePosts (ArrayList<Post> posts){

        this.posts = posts;
        notifyDataSetChanged();
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater li = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.activity_post,parent,false);
        return new PostViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {

        final Post thispost = posts.get(position);

        holder.tv_title.setText(thispost.getTitle());
        holder.tv_body.setText(thispost.getBody());
        holder.thisview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListner != null){
                    onItemClickListner.onItemClick(thispost.getId());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


    class PostViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title,tv_body;
        View thisview;

        public PostViewHolder(View itemView) {
            super(itemView);
            tv_body = (TextView) itemView.findViewById(R.id.tv_post_body);
            tv_title = (TextView) itemView.findViewById(R.id.tv_post_task);
            thisview = itemView;

        }
    }
}
