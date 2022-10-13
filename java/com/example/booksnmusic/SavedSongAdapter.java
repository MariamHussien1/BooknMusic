package com.example.booksnmusic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SavedSongAdapter extends RecyclerView.Adapter<SavedSongAdapter.MyViewHolder>{
    private final recyclerview_interface recyclerview_interface1;
    private Context context;
    private ArrayList sname_id,singer_id,duration_id,gerne_id,relyear_id,slink_id,scover_id;

    public SavedSongAdapter(Context context, ArrayList sname_id, ArrayList singer_id, ArrayList duration_id, ArrayList gerne_id, ArrayList relyear_id, ArrayList slink_id, ArrayList scover_id,recyclerview_interface recyclerview_interface1) {
        this.context = context;
        this.sname_id = sname_id;
        this.singer_id = singer_id;
        this.duration_id = duration_id;
        this.gerne_id = gerne_id;
        this.relyear_id = relyear_id;
        this.slink_id = slink_id;
        this.scover_id = scover_id;
        this.recyclerview_interface1=recyclerview_interface1;
    }

    @NonNull
    @Override
    public SavedSongAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.savedsongentry,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedSongAdapter.MyViewHolder holder, int position) {
        holder.sname_id.setText(String.valueOf(sname_id.get(position)));
    }

    @Override
    public int getItemCount() {
        return sname_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView sname_id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sname_id=itemView.findViewById(R.id.songname201);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerview_interface1 !=null){
                        int pos =getAdapterPosition();
                        recyclerview_interface1.OnItemClick(pos);
                    }
                }
            });
        }
    }
}
