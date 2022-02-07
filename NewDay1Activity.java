package com.example.selfdisciplinemaster.Day1;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.selfdisciplinemaster.R;

import java.util.Calendar;

public class NewDay1Activity extends AppCompatActivity {


    private EditText day1TitleEdt, etNote;
    private Button btnSave, btnDate, btnStart, btnEnd, btnRest, btnSetAlarm, btnNote, btnNoteList;
    private TextView tvDate, tvStart1, tvStart2, tvEnd;


    public static final String EXTRA_ID = "com.gtappdevelopers.gfgroomdatabase.EXTRA_ID";
    public static final String EXTRA_DAY1_TITLE = "com.gtappdevelopers.gfgroomdatabase.EXTRA_DAY1_TITLE";
    public static final String EXTRA_DAY1_TIME_START1 = "com.gtappdevelopers.gfgroomdatabase.EXTRA_DAY1_TIME_START1";
    public static final String EXTRA_DAY1_TIME_START2 = "com.gtappdevelopers.gfgroomdatabase.EXTRA_DAY1_TIME_START2";
    public static final String EXTRA_DAY1_TIME_END = "com.gtappdevelopers.gfgroomdatabase.EXTRA_DAY1_TIME_END";
    public static final String EXTRA_DAY1_DATE= "com.gtappdevelopers.gfgroomdatabase.EXTRA_DAY1_DATE";
    public static final String EXTRA_DAY1_NOTE= "com.gtappdevelopers.gfgroomdatabase.EXTRA_DAY1_NOTE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_day1);


        day1TitleEdt = findViewById(R.id.idEdtDay1_title);
        tvDate = findViewById(R.id.tv_day1_date);
        tvStart1 = findViewById(R.id.tv_day1_start1);
        tvStart2 = findViewById(R.id.tv_day1_start2);
        tvEnd = findViewById(R.id.tv_day1_end);
        etNote = findViewById(R.id.et_day1_note);

        btnSave = findViewById(R.id.idBtnSaveDay1);
        btnRest = findViewById(R.id.idBtnSaveDay1_Rest);
        btnDate = findViewById(R.id.btn_day1_date);
        btnStart = findViewById(R.id.btn_day1_time_start);
        btnEnd = findViewById(R.id.btn_day1_time_end);
        btnSetAlarm = findViewById(R.id.idBtnSet_alarmDay1);

        btnNoteList = findViewById(R.id.idBtnNoteDay1);
        btnNoteList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent note = new Intent(NewDay1Activity.this, note_main_day1.class);
                startActivity(note);
            }
        });

        btnSetAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvStart1.getText().toString().isEmpty() && tvStart2.getText().toString().isEmpty()) {
                    Toast.makeText(NewDay1Activity.this, "Please choose a time", Toast.LENGTH_SHORT).show();
                } else {

                    Intent in = new Intent(AlarmClock.ACTION_SET_ALARM);
                    in.putExtra(AlarmClock.EXTRA_HOUR, Integer.parseInt(tvStart1.getText().toString()));
                    in.putExtra(AlarmClock.EXTRA_MINUTES, Integer.parseInt(tvStart2.getText().toString()));
                    in.putExtra(AlarmClock.EXTRA_MESSAGE, "The plan: " + day1TitleEdt.getText().toString() + " is start");
                    if(in.resolveActivity(getPackageManager()) != null) {
                        startActivity(in);
                    }else {
                        Toast.makeText(NewDay1Activity.this, "There is no app that support this action", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });


        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {

            day1TitleEdt.setText(intent.getStringExtra(EXTRA_DAY1_TITLE));
            tvStart1.setText(intent.getStringExtra(EXTRA_DAY1_TIME_START1));
            tvStart2.setText(intent.getStringExtra(EXTRA_DAY1_TIME_START2));
            tvEnd.setText(intent.getStringExtra(EXTRA_DAY1_TIME_END));
            tvDate.setText(intent.getStringExtra(EXTRA_DAY1_DATE));
            etNote.setText(intent.getStringExtra(EXTRA_DAY1_NOTE));
        }

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleDateButton();
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleTimeStart();
            }
        });

        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleTimeEnd();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String day1Ttile = day1TitleEdt.getText().toString();
                String day1Start1 = tvStart1.getText().toString();
                String day1Start2 = tvStart2.getText().toString();
                String day1End = tvEnd.getText().toString();
                String day1D = tvDate.getText().toString();
                String day1Note = etNote.getText().toString();
                if (day1Ttile.isEmpty() || day1Start1.isEmpty() || day1Start2.isEmpty() || day1End.isEmpty() || day1D.isEmpty()) {
                    Toast.makeText(NewDay1Activity.this, "Please enter the valid day2 plan details.", Toast.LENGTH_SHORT).show();
                    return;
                }

                saveDay1(day1Ttile, day1Start1, day1Start2, day1End, day1D, day1Note);
                day1TitleEdt.setText("");
                tvDate.setText("");
                tvStart1.setText("");
                tvStart2.setText("");
                tvEnd.setText("");
                etNote.setText("");

                AlertDialog.Builder alerDialogBuilder = new AlertDialog.Builder(NewDay1Activity.this);
                alerDialogBuilder.setTitle("Allocate Rest Time");
                alerDialogBuilder.setMessage("In order to keep your healthy, you should allocate some time to rest ");

                alerDialogBuilder.setNegativeButton("Check your plan, then Go to", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(NewDay1Activity.this,"Go!",Toast.LENGTH_LONG).show();
                    }
                });

                alerDialogBuilder.show();



            }
        });

        btnRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String day1Ttile = day1TitleEdt.getText().toString();
                String day1Start1 = tvStart1.getText().toString();
                String day1Start2 = tvStart2.getText().toString();
                String day1End = tvEnd.getText().toString();
                String day1D = tvDate.getText().toString();
                String day1Note = etNote.getText().toString();
                if (day1Ttile.isEmpty() || day1Start1.isEmpty() || day1Start2.isEmpty() || day1End.isEmpty() || day1D.isEmpty()) {
                    Toast.makeText(NewDay1Activity.this, "Please enter the rest time.", Toast.LENGTH_SHORT).show();
                    return;
                }

                saveDay1(day1Ttile, day1Start1, day1Start2, day1End, day1D, day1Note);
                day1TitleEdt.setText("");
                tvDate.setText("");
                tvStart1.setText("");
                tvStart2.setText("");
                tvEnd.setText("");
                etNote.setText("");

            }
        });
    }

    private void saveDay1(String day1Ttile, String day1Start1, String day1Start2, String day1End, String day1D, String day1Note) {
        Intent data = new Intent();


        data.putExtra(EXTRA_DAY1_TITLE, day1Ttile);
        data.putExtra(EXTRA_DAY1_TIME_START1, day1Start1);
        data.putExtra(EXTRA_DAY1_TIME_START2, day1Start2);
        data.putExtra(EXTRA_DAY1_TIME_END,day1End);
        data.putExtra(EXTRA_DAY1_DATE,day1D);
        data.putExtra(EXTRA_DAY1_NOTE, day1Note);
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {

            data.putExtra(EXTRA_ID, id);
        }


        setResult(RESULT_OK, data);

        Toast.makeText(this, "day2 plan has been saved to Room Database. ", Toast.LENGTH_SHORT).show();
    }

    private void handleTimeStart() {


        Calendar mcurrentTime = Calendar.getInstance();
        int currentHour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int currentMinute = mcurrentTime.get(Calendar.MINUTE);

        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                tvStart1.setText(String.format("%02d", selectedHour));
                tvStart2.setText(String.format("%02d", selectedMinute));
            }
        }, currentHour, currentMinute, false);//Yes 24 hour time

        mTimePicker.show();

        Toast.makeText(this, "start time", Toast.LENGTH_SHORT).show();
    }

    private void handleTimeEnd() {


        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                tvEnd.setText(selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
        Toast.makeText(this, "end time", Toast.LENGTH_SHORT).show();
    }

    public void handleDateButton() {
        Calendar calendar = Calendar.getInstance();
        int YEAR = calendar.get(calendar.YEAR);
        int MONTH = calendar.get(calendar.MONTH);
        int DATE = calendar.get(calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int date) {

                Calendar calendar2 = Calendar.getInstance();
                calendar2.set(Calendar.YEAR, year);
                calendar2.set(Calendar.MONTH, month);
                calendar2.set(Calendar.DATE, date);
                String dateText2 = DateFormat.format("EEEE, MMM d, yyyy", calendar2).toString();

                tvDate.setText(dateText2);

            }
        }, YEAR, MONTH, DATE);

        datePickerDialog.show();
        Toast.makeText(this, "Date has slected", Toast.LENGTH_SHORT).show();

    }


}