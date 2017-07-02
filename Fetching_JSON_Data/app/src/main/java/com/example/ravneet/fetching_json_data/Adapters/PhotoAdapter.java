package com.example.ravneet.fetching_json_data.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ravneet.fetching_json_data.Interface.OnItemClickListner;
import com.example.ravneet.fetching_json_data.R;
import com.example.ravneet.fetching_json_data.models.Photo;

import java.util.ArrayList;

/**
 * Created by ravneet on 2/7/17.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    private Context context;
    private ArrayList<Photo> photos;
    private OnItemClickListner onItemClickListner;

    public void setOnItemClickListner(OnItemClickListner onItemClickListner){
        this.onItemClickListner = onItemClickListner;
    }

    public PhotoAdapter(Context context,ArrayList<Photo> photos){
        this.context = context;
        this.photos = photos;
    }

    public void updatephotos(ArrayList<Photo> photos){

        this.photos = photos;
        notifyDataSetChanged();

    }


    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View itemview = li.inflate(R.layout.activity_album,parent,false);

        return new PhotoViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {

        final Photo thisphoto = photos.get(position);

        holder.title.setText(thisphoto.getTitle());
        holder.thisview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListner != null){
                    onItemClickListner.onItemClick(thisphoto.getId());
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class PhotoViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        View thisview;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_title_title);
        }
    }
}
