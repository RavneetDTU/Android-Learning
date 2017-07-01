package com.example.ravneet.fetching_json_data.Adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.example.ravneet.fetching_json_data.R;

/**
 * Created by ravneet on 1/7/17.
 */

public class PostAdapter extends RecyclerView<PostAdapter.PostViewHolder> {


    public PostAdapter(Context context) {
        super(context);
    }

    public PostAdapter(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PostAdapter(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    class PostViewHolder extends RecyclerView.ViewHolder{

        TextView tv_title,tv_body;

        public PostViewHolder(View itemView) {
            super(itemView);
            tv_body = (TextView) itemView.findViewById(R.id.)
        }
    }
}
