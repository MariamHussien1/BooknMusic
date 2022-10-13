package com.example.booksnmusic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.MyViewHolder> implements Filterable {
    private final recyclerview_interface recyclerview_interface1;
    private Context context;
    private ArrayList sname_id,singer_id,duration_id,gerne_id,relyear_id,slink_id,scover_id;
    private ArrayList sname_idall;

    public SongAdapter(Context context, ArrayList sname_id, ArrayList singer_id, ArrayList duration_id, ArrayList gerne_id, ArrayList relyear_id, ArrayList slink_id, ArrayList scover_id,recyclerview_interface recyclerview_interface1) {
        this.context = context;
        this.singer_id = singer_id;
        this.duration_id = duration_id;
        this.gerne_id = gerne_id;
        this.relyear_id = relyear_id;
        this.slink_id = slink_id;
        this.scover_id = scover_id;
        this.sname_idall=sname_id;
        this.sname_id= new ArrayList<>(sname_idall);
        this.recyclerview_interface1=recyclerview_interface1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.songentry,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.sname_id.setText(String.valueOf(sname_id.get(position)));
    }

    @Override
    public int getItemCount() {
        return sname_id.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList filteredlist =new ArrayList<>();

            if(charSequence.toString().isEmpty()){
                filteredlist.addAll(sname_idall);
            }
            else{
                for(Object song: sname_idall){
                    if(song.toString().toLowerCase().contains(charSequence.toString().toLowerCase())){
                        filteredlist.add((String)song);
                    }
                }
            }
            if(filteredlist.isEmpty()){
                Toast.makeText(context.getApplicationContext(),"No Songs Found", Toast.LENGTH_SHORT).show();
            }
            FilterResults filterResults=new FilterResults();
            filterResults.values=filteredlist;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            sname_id.clear();
            sname_id.addAll((Collection) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView sname_id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sname_id=itemView.findViewById(R.id.songname13);
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
