package com.example.todolistapp.DataBase.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.todolistapp.DataBase.Model.Todo;

import java.util.List;

@Dao
public interface TodoDao {
    @Insert
    public void AddTodo(Todo todoItem);
    @Delete
    public void RemoveTodo(Todo todoItem);
    @Query("DELETE FROM todo")
    public void removeAllTodo();
    @Update
    public void UpdateTodo(Todo todoItem);
    @Query("select * from todo;")
    public List<Todo> getAllTodo();
}
