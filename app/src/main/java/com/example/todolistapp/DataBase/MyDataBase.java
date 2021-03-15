package com.example.todolistapp.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.todolistapp.DataBase.Dao.TodoDao;
import com.example.todolistapp.DataBase.Model.Todo;


@Database(entities = {Todo.class},version = 1,exportSchema = false)
public abstract class MyDataBase extends RoomDatabase {
    public abstract TodoDao todoDao();
    private static MyDataBase myDataBase;
    public static MyDataBase getInstance(Context context){
         if (myDataBase==null)//create dataBase
         {
             myDataBase= Room.databaseBuilder(context.getApplicationContext(),
                     MyDataBase.class,"todo-DataBase")
                     .allowMainThreadQueries()
                     .build();
         }return myDataBase;
    }
}
