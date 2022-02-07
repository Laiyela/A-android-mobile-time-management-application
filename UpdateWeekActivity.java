package com.example.selfdisciplinemaster.Week;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.selfdisciplinemaster.R;

public class UpdateWeekActivity extends AppCompatActivity {

    // creating a variables for our button and edittext.
    private EditText weekTitleEdt, weekDescEdt, weekOneEdt;
    private Button weekBtn;

    // creating a constant string variable for our
    // course name, description and duration.
    public static final String EXTRA_ID = "com.gtappdevelopers.gfgroomdatabase.EXTRA_ID";
    public static final String EXTRA_WEEK_TITLE = "com.gtappdevelopers.gfgroomdatabase.EXTRA_WEEK_TITLE";
    public static final String EXTRA_WEEK_DESCRIPTION = "com.gtappdevelopers.gfgroomdatabase.EXTRA_WEEK_DESCRIPTION";
    public static final String EXTRA_WEEK_ONE = "com.gtappdevelopers.gfgroomdatabase.EXTRA_WEEK_ONE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_week);

        // initializing our variables for each view.
        weekTitleEdt = findViewById(R.id.idEdtWeekTitle);
        weekDescEdt = findViewById(R.id.idEdtWeekDescription);
        weekOneEdt = findViewById(R.id.idEdtWeekOne);
        weekBtn = findViewById(R.id.idBtnSaveWeek);

        // below line is to get intent as we
        // are getting data via an intent.
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            // if we get id for our data then we are
            // setting values to our edit text fields.
            weekTitleEdt.setText(intent.getStringExtra(EXTRA_WEEK_TITLE));
            weekDescEdt.setText(intent.getStringExtra(EXTRA_WEEK_DESCRIPTION));
            weekOneEdt.setText(intent.getStringExtra(EXTRA_WEEK_ONE));
        }
        // adding on click listener for our save button.
        weekBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getting text value from edittext and validating if
                // the text fields are empty or not.
                String weekTtile = weekTitleEdt.getText().toString();
                String weekDesc = weekDescEdt.getText().toString();
                String weekOne = weekOneEdt.getText().toString();
                if (weekTtile.isEmpty() || weekDesc.isEmpty() || weekOne.isEmpty()) {
                    Toast.makeText(UpdateWeekActivity.this, "Please enter the valid week plan details.", Toast.LENGTH_SHORT).show();
                    return;
                }
                // calling a method to save our course.
                saveWeek(weekTtile, weekDesc, weekOne);
            }
        });
    }

    private void saveWeek(String weekTitle, String weekDesc, String weekOne) {
        // inside this method we are passing
        // all the data via an intent.
        Intent data = new Intent();

        // in below line we are passing all our course detail.
        data.putExtra(EXTRA_WEEK_TITLE, weekTitle);
        data.putExtra(EXTRA_WEEK_DESCRIPTION, weekDesc);
        data.putExtra(EXTRA_WEEK_ONE,weekOne);
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            // in below line we are passing our id.
            data.putExtra(EXTRA_ID, id);
        }

        // at last we are setting result as data.
        setResult(RESULT_OK, data);

        // displaying a toast message after adding the data
        Toast.makeText(this, "Week plan has been saved to Room Database. ", Toast.LENGTH_SHORT).show();
    }
}