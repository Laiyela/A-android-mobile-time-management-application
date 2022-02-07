package com.example.selfdisciplinemaster.Day1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.selfdisciplinemaster.NoteActivity;
import com.example.selfdisciplinemaster.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class note_main_day1 extends AppCompatActivity {

    private RecyclerView day1RV;
    private static final int ADD_DAY1_REQUEST = 1;
    private static final int EDIT_DAY1_REQUEST = 2;
    private ViewModal_day1 viewmodal;
    Button btnNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_main_day1);

        day1RV = findViewById(R.id.idRVDay1s_note);
        FloatingActionButton fab = findViewById(R.id.idFABAdd_day1_note);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(note_main_day1.this, activity_main_day1.class);
                startActivityForResult(intent, ADD_DAY1_REQUEST);
            }
        });

        btnNote = findViewById(R.id.btn_day1_mainNote);
        btnNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent note = new Intent(note_main_day1.this, NoteActivity.class);
                startActivity(note);
            }
        });

        day1RV.setLayoutManager(new LinearLayoutManager(this));
        day1RV.setHasFixedSize(true);

        final Day1RVAdapter_note adapter = new Day1RVAdapter_note();

        day1RV.setAdapter(adapter);

        viewmodal = ViewModelProviders.of(this).get(ViewModal_day1.class);

        viewmodal.getAllDay1s().observe(this, new Observer<List<Day1Modal>>() {
            @Override
            public void onChanged(List<Day1Modal> models) {

                adapter.submitList(models);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewmodal.delete(adapter.getDay1At(viewHolder.getAdapterPosition()));
                Toast.makeText(note_main_day1.this, "Day1 plan deleted", Toast.LENGTH_SHORT).show();
            }
        }).
                        attachToRecyclerView(day1RV);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_DAY1_REQUEST && resultCode == RESULT_OK) {
            String day1Title = data.getStringExtra(NewDay1Activity.EXTRA_DAY1_TITLE);
            String day1Start1 = data.getStringExtra(NewDay1Activity.EXTRA_DAY1_TIME_START1);
            String day1Start2 = data.getStringExtra(NewDay1Activity.EXTRA_DAY1_TIME_START2);
            String day1End = data.getStringExtra(NewDay1Activity.EXTRA_DAY1_TIME_END);
            String day1D = data.getStringExtra(NewDay1Activity.EXTRA_DAY1_DATE);
            String day1Note = data.getStringExtra(NewDay1Activity.EXTRA_DAY1_NOTE);
            Day1Modal model = new Day1Modal(day1Title, day1Start1, day1Start2,day1End, day1D, day1Note);

            viewmodal.insert(model);
            Toast.makeText(this, "plan saved", Toast.LENGTH_SHORT).show();
        }

        else if (requestCode == EDIT_DAY1_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(NewDay1Activity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "day1 can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String day1Title = data.getStringExtra(NewDay1Activity.EXTRA_DAY1_TITLE);
            String day1Start1 = data.getStringExtra(NewDay1Activity.EXTRA_DAY1_TIME_START1);
            String day1Start2 = data.getStringExtra(NewDay1Activity.EXTRA_DAY1_TIME_START2);
            String day1End = data.getStringExtra(NewDay1Activity.EXTRA_DAY1_TIME_END);
            String day1D = data.getStringExtra(NewDay1Activity.EXTRA_DAY1_DATE);
            String day1Note = data.getStringExtra(NewDay1Activity.EXTRA_DAY1_NOTE);
            Day1Modal model = new Day1Modal(day1Title, day1Start1, day1Start2, day1End, day1D, day1Note);

            model.setId(id);
            viewmodal.update(model);
            Toast.makeText(this, "day1 plan updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "day1 plan not saved", Toast.LENGTH_SHORT).show();
        }
    }
}