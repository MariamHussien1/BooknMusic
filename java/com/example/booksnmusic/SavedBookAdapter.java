package com.example.booksnmusic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class SavedBookAdapter extends RecyclerView.Adapter<SavedBookAdapter.MyViewHolder> {
    private final recyclerview_interface recyclerview_interface1;
    private Context context;
    private ArrayList bname_id,author_id,desc_id,page_id,pubyear_id,link_id,cover_id;

    public SavedBookAdapter(Context context, ArrayList bname_id, ArrayList author_id, ArrayList desc_id, ArrayList page_id, ArrayList pubyear_id, ArrayList link_id, ArrayList cover_id,recyclerview_interface recyclerview_interface1) {
        this.context = context;
        this.bname_id = bname_id;
        this.author_id = author_id;
        this.desc_id = desc_id;
        this.page_id = page_id;
        this.pubyear_id = pubyear_id;
        this.link_id = link_id;
        this.cover_id = cover_id;
        this.recyclerview_interface1=recyclerview_interface1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.savedbookentry,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bname_id.setText(String.valueOf(bname_id.get(position)));
    }

    @Override
    public int getItemCount() {
        return bname_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView bname_id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bname_id=itemView.findViewById(R.id.bookname201);
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
