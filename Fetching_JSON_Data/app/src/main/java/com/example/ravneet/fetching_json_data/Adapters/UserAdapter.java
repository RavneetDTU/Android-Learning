package com.example.ravneet.fetching_json_data.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.example.ravneet.fetching_json_data.CommentActivity;
import com.example.ravneet.fetching_json_data.Interface.OnItemClickListner;
import com.example.ravneet.fetching_json_data.R;
import com.example.ravneet.fetching_json_data.ToDoActivity;
import com.example.ravneet.fetching_json_data.models.Comment;
import com.example.ravneet.fetching_json_data.models.User;

import java.util.ArrayList;

/**
 * Created by ravneet on 1/7/17.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    private Context context;
    private ArrayList<User> users;
    private OnItemClickListner onItemClickListner;

    public void setOnItemClickListner(OnItemClickListner onItemClickListner){
        this.onItemClickListner = onItemClickListner;
    }

    public UserAdapter(Context context,ArrayList<User> users){
        this.context = context;
        this.users = users;
    }

    public void updateuser(ArrayList<User> newuserlist){

        this.users = newuserlist;
        notifyDataSetChanged();
    }

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemview = li.inflate(R.layout.activity_user,parent,false);

        return new UserHolder(itemview);
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {

        final User thisuser = users.get(position);

        holder.tvusername.setText(thisuser.getUsername());
        holder.tvtel.setText(thisuser.getPhone());
        holder.tvemail.setText(thisuser.getEmail());
        holder.thisview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListner != null){
                    onItemClickListner.onItemClick(thisuser.getId());
                }
            }
        });

        holder.btn_todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), ToDoActivity.class);
                i.putExtra("userId",thisuser.getId());
                view.getContext().startActivity(i);
            }
        });

        holder.btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), CommentActivity.class);
                i.putExtra("userId",thisuser.getId());
                view.getContext().startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    class UserHolder extends RecyclerView.ViewHolder{

        TextView tvusername,tvtel,tvemail;
        Button btn_post,btn_todo;
        View thisview;

        public UserHolder(View itemView) {
            super(itemView);

            thisview = itemView;
            tvusername = (TextView)itemView.findViewById(R.id.tv_user_username);
            tvtel = (TextView)itemView.findViewById(R.id.tv_user_contactnumber);
            tvemail = (TextView)itemView.findViewById(R.id.tv_user_email);
            btn_post = (Button) itemView.findViewById(R.id.btn_activityuser_post);
            btn_todo = (Button) itemView.findViewById(R.id.btn_activityuser_comments);
            thisview = itemView;


        }
    }

}
