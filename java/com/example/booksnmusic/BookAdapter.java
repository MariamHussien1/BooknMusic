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

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> implements Filterable {
    private final recyclerview_interface recyclerview_interface1;
    private Context context;
    private ArrayList bname_id,author_id,desc_id,page_id,pubyear_id,link_id,cover_id;
    private ArrayList bname_idall;

    public BookAdapter(Context context, ArrayList bname_id, ArrayList author_id, ArrayList desc_id, ArrayList page_id, ArrayList pubyear_id, ArrayList link_id, ArrayList cover_id,recyclerview_interface recyclerview_interface1) {
        this.context = context;
        this.author_id = author_id;
        this.desc_id = desc_id;
        this.page_id = page_id;
        this.pubyear_id = pubyear_id;
        this.link_id = link_id;
        this.cover_id = cover_id;
        this.bname_idall=bname_id;
        this.bname_id= new ArrayList<>(bname_idall);
        this.recyclerview_interface1=recyclerview_interface1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.bookentry,parent,false);
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

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList filteredlist =new ArrayList<>();

            if(charSequence.toString().isEmpty()){
                filteredlist.addAll(bname_idall);
            }
            else{
                for(Object book: bname_idall){
                    if(book.toString().toLowerCase().contains(charSequence.toString().toLowerCase())){
                        filteredlist.add((String)book);
                    }
                }
            }
            if(filteredlist.isEmpty()){
                Toast.makeText(context.getApplicationContext(),"No Books Found", Toast.LENGTH_SHORT).show();
            }
            FilterResults filterResults=new FilterResults();
            filterResults.values=filteredlist;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            bname_id.clear();
            bname_id.addAll((Collection) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView bname_id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bname_id=itemView.findViewById(R.id.bookname1);
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
