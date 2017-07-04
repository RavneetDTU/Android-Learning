package com.example.ravneet.fetching_json_data.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ravneet.fetching_json_data.Interface.OnItemClickListner;
import com.example.ravneet.fetching_json_data.R;
import com.example.ravneet.fetching_json_data.models.Album;

import java.util.ArrayList;

/**
 * Created by ravneet on 1/7/17.
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {
    
    public static final String TAG = "Hello";

    private Context context;
    private ArrayList<Album> albumtitle;
    private OnItemClickListner onItemClickListner;

    public void setOnItemClickListner(OnItemClickListner onItemClickListner){
        this.onItemClickListner = onItemClickListner;
    }

    public AlbumAdapter(Context context, ArrayList<Album> albumtitle){
        this.albumtitle = albumtitle;
        this.context = context;
    }

    public void updatealbum(ArrayList<Album> albumtitle){
        this.albumtitle = albumtitle;
        notifyDataSetChanged();
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.i(TAG, "onCreateViewHolder: ");
        LayoutInflater li = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View itemview = li.inflate(R.layout.activity_album,parent,false);
        return new AlbumViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {

        Log.i(TAG, "onBindViewHolder: ");

        final Album thisalbum = albumtitle.get(position);

        holder.albumtitle.setText(thisalbum.getTitle());
        holder.thisview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListner != null){
                    onItemClickListner.onItemClick(thisalbum.getId());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return albumtitle.size();
    }

    class AlbumViewHolder extends RecyclerView.ViewHolder{
        TextView albumtitle;
        View thisview;

        public AlbumViewHolder(View itemView) {
            super(itemView);
            albumtitle = (TextView) itemView.findViewById(R.id.tv_title_title);
            thisview = itemView;
        }
    }
}
