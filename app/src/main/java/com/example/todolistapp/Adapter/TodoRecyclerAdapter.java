package com.example.todolistapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.todolistapp.DataBase.Model.Todo;
import com.example.todolistapp.R;

import java.util.List;

public class TodoRecyclerAdapter extends RecyclerView.Adapter<TodoRecyclerAdapter.MyViewHolder> {
    Context context;
    List<Todo>items;

    public TodoRecyclerAdapter(Context context, List<Todo> items) {
        this.context = context;
        this.items = items;

    }


    public void dataChanged(List<Todo> item){
        this.items=item;
        notifyDataSetChanged();


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.todo_card_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Todo todoItem=items.get(position);
        holder.title.setText(todoItem.getTitle());
        holder.date.setText(todoItem.getDateTime());
        holder.content.setText(todoItem.getContent());
    }

    @Override
    public int getItemCount() {
        if (items==null)return 0;
        return items.size();
    }
    public Todo getTodoAt(int position){
        return items.get(position);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView date;
        TextView content;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            date=itemView.findViewById(R.id.date);
            content=itemView.findViewById(R.id.contents);
        }
    }
}
