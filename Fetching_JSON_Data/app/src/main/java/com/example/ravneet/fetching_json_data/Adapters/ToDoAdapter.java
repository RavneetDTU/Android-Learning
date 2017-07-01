package com.example.ravneet.fetching_json_data.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ravneet.fetching_json_data.Interface.OnItemClickListner;
import com.example.ravneet.fetching_json_data.R;
import com.example.ravneet.fetching_json_data.models.ToDo;

import java.util.ArrayList;

/**
 * Created by ravneet on 1/7/17.
 */

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder> {

    private Context context;
    private ArrayList<ToDo> tasklist;
    private OnItemClickListner onItemClickListner;

    public void setOnItemClickListner(OnItemClickListner onItemClickListner){
        this.onItemClickListner = onItemClickListner;
    }

    public ToDoAdapter(Context context,ArrayList<ToDo> tasklist){

        this.context = context;
        this.tasklist = tasklist;

    }

    public void updatetasks(ArrayList<ToDo> tasklist){
        this.tasklist = tasklist;
    }


    @Override
    public ToDoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View itemview = li.inflate(R.layout.activity_to_do,parent,false);
        return new ToDoViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ToDoViewHolder holder, int position) {

        final ToDo task = tasklist.get(position);

        holder.task.setText(task.getTitle());
        holder.thisview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListner != null){
                    onItemClickListner.onItemClick(task.getId());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return tasklist.size();
    }

    class ToDoViewHolder extends RecyclerView.ViewHolder{
        TextView task;
        View thisview;

        public ToDoViewHolder(View itemView) {
            super(itemView);
        }
    }
}
