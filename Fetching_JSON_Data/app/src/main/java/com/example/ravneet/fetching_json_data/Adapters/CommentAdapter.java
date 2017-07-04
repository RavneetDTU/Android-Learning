package com.example.ravneet.fetching_json_data.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ravneet.fetching_json_data.Interface.OnItemClickListner;
import com.example.ravneet.fetching_json_data.R;
import com.example.ravneet.fetching_json_data.models.Comment;

import java.util.ArrayList;

/**
 * Created by ravneet on 2/7/17.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private Context context;
    private ArrayList<Comment> commentlist;
    private OnItemClickListner onItemClickListner;

    public void setOnItemClickListner(OnItemClickListner onItemClickListner){
        this.onItemClickListner = onItemClickListner;
    }

    public CommentAdapter(Context context,ArrayList<Comment> commentlist){
        this.context = context;
        this.commentlist = commentlist;
    }

    public void updatecomments(ArrayList<Comment> commentlist){
        this.commentlist = commentlist;
        notifyDataSetChanged();
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View itemview = li.inflate(R.layout.activity_comment,parent,false);

        return new CommentViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {

        final Comment thiscomment = commentlist.get(position);

        holder.tvname.setText(thiscomment.getName());
        holder.tvbody.setText(thiscomment.getBody());
        holder.tvemail.setText(thiscomment.getEmail());
        holder.thisview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListner != null){
                    onItemClickListner.onItemClick(thiscomment.getId());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class CommentViewHolder extends RecyclerView.ViewHolder{

        TextView tvbody,tvname,tvemail;
        View thisview;

        public CommentViewHolder(View itemView) {
            super(itemView);

            tvbody = (TextView) itemView.findViewById(R.id.tv_comment_body);
            tvname = (TextView) itemView.findViewById(R.id.tv_comment_name);
            tvemail = (TextView) itemView.findViewById(R.id.tv_comment_email);
            thisview = itemView;
        }
    }
}
