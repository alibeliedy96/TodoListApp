package com.example.todolistapp.ui;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.todolistapp.Base.BaseActivity;
import com.example.todolistapp.DataBase.Model.Todo;
import com.example.todolistapp.DataBase.MyDataBase;
import com.example.todolistapp.R;
import com.example.todolistapp.TaskAlarmBroadCastReciever;


import java.util.Calendar;

public class AddTodoActivity extends BaseActivity implements OnClickListener {

    private  EditText mTitle;
    private TextView mData;
    private EditText mContent;
    private  Button mButtonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        initViews();
    }
    public void initViews(){
        mTitle = findViewById(R.id.title);
        mData = findViewById(R.id.data);
        mContent = findViewById(R.id.content);
        mButtonAdd = findViewById(R.id.button_add);
        mButtonAdd.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.button_add){
            String title=mTitle.getText().toString();
            String data=mData.getText().toString();
            String content=mContent.getText().toString();
                if (title!=null && !title.isEmpty()&&data!=null && !data.isEmpty()&&content!=null && !content.isEmpty()){
                    Todo todoItem = new Todo(title, content, data);
                    MyDataBase.getInstance(this)
                            .todoDao()
                            .AddTodo(todoItem);
                    showConfirmationMessage(R.string.success, R.string.todo_Add_successfully, R.string.ok
                            , new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    dialog.dismiss();
                                    finish();
                                }
                            }).setCancelable(false);
                    setAlarmForTodo();

                }else {
                    showConfirmationMessage(R.string.error, R.string.field_is_empty, R.string.ok
                            , new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    dialog.dismiss();
                                    finish();
                                }
                            }).setCancelable(true);
                }



    }
}
public void setAlarmForTodo(){
    AlarmManager alarmManager=(AlarmManager) getSystemService(ALARM_SERVICE);
    Calendar calendar=Calendar.getInstance();
    calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
    calendar.set(Calendar.MINUTE,minutes);
    Intent alarmIntent=new Intent(activity, TaskAlarmBroadCastReciever.class);
    alarmIntent.putExtra("title",mTitle.getText().toString());
    alarmIntent.putExtra("desc",mContent.getText().toString());
    //using PendingIntent to save object i want to send to BroadcastReciever
    PendingIntent pendingIntent=PendingIntent.getBroadcast(this,0,alarmIntent,0);
    alarmManager.set(AlarmManager.RTC_WAKEUP,
            calendar.getTimeInMillis(),
            pendingIntent);

}
    int hourOfDay=-1;
    int minutes=-1;
    public void openTimePicker(View view) {
        Calendar calendar=Calendar.getInstance();
        TimePickerDialog timePickerDialog=new TimePickerDialog(activity, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                hourOfDay=hour;
                minutes=minute;
                mData.setText(hour+":"+minute);
            }
        },calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),false);
        timePickerDialog.show();
    }
}