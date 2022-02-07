package com.example.selfdisciplinemaster.Day1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.selfdisciplinemaster.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class activity_main_day1 extends AppCompatActivity {

    // creating a variables for our recycler view.
    private RecyclerView day1RV;
    private static final int ADD_DAY1_REQUEST = 1;
    private static final int EDIT_DAY1_REQUEST = 2;
    private ViewModal_day1 viewmodal;
    CheckBox cbDay1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_day1);

        cbDay1 = findViewById(R.id.cb_day1);
        cbDay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerDialogBuilder = new AlertDialog.Builder(activity_main_day1.this);
                alerDialogBuilder.setTitle("Clock in");
                alerDialogBuilder.setMessage("Congratulations, you've finished all your plans for today. You're one step closer to your goal. Keep going!!! ");

                alerDialogBuilder.setNegativeButton("Thank you for working hard today", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(activity_main_day1.this,"Go!",Toast.LENGTH_LONG).show();
                    }
                });

                alerDialogBuilder.show();
            }
        });

        day1RV = findViewById(R.id.idRVDay1s);
        FloatingActionButton fab = findViewById(R.id.idFABAdd_day1);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity_main_day1.this, NewDay1Activity.class);
                startActivityForResult(intent, ADD_DAY1_REQUEST);
            }
        });


        day1RV.setLayoutManager(new LinearLayoutManager(this));
        day1RV.setHasFixedSize(true);


        final Day1RVAdapter adapter = new Day1RVAdapter();


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
                Toast.makeText(activity_main_day1.this, "Day2 plan deleted", Toast.LENGTH_SHORT).show();
            }
        }).

                        attachToRecyclerView(day1RV);

        adapter.setOnItemClickListener(new Day1RVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Day1Modal model) {

                Intent intent = new Intent(activity_main_day1.this, NewDay1Activity.class);
                intent.putExtra(NewDay1Activity.EXTRA_ID, model.getId());
                intent.putExtra(NewDay1Activity.EXTRA_DAY1_TITLE, model.getDay1Title());
                intent.putExtra(NewDay1Activity.EXTRA_DAY1_TIME_START1, model.getDay1TimeStart1());
                intent.putExtra(NewDay1Activity.EXTRA_DAY1_TIME_START2, model.getDay1TimeStart2());
                intent.putExtra(NewDay1Activity.EXTRA_DAY1_TIME_END, model.getDay1TimeEnd());
                intent.putExtra(NewDay1Activity.EXTRA_DAY1_DATE, model.getDay1Date());
                intent.putExtra(NewDay1Activity.EXTRA_DAY1_NOTE, model.getDay1Note());


                startActivityForResult(intent, EDIT_DAY1_REQUEST);
            }
        });
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
            Toast.makeText(this, "day2 plan updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "day2 plan not saved", Toast.LENGTH_SHORT).show();
        }
    }
}