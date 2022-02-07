package com.example.selfdisciplinemaster.Week;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class activity_main_week extends AppCompatActivity {

    // creating a variables for our recycler view.
    private RecyclerView weekRV;
    private static final int ADD_WEEK_REQUEST = 1;
    private static final int EDIT_WEEK_REQUEST = 2;
    private ViewModal_week viewmodal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_week);

        // initializing our variable for our recycler view and fab.
        weekRV = findViewById(R.id.idRVWeeks);
        FloatingActionButton fab = findViewById(R.id.idFABAdd);

        // adding on click listener for floating action button.
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // starting a new activity for adding a new course
                // and passing a constant value in it.
                Intent intent = new Intent(activity_main_week.this, UpdateWeekActivity.class);
                startActivityForResult(intent, ADD_WEEK_REQUEST);
            }
        });

        // setting layout manager to our adapter class.
        weekRV.setLayoutManager(new LinearLayoutManager(this));
        weekRV.setHasFixedSize(true);

        // initializing adapter for recycler view.
        final WeekRVAdapter adapter = new WeekRVAdapter();

        // setting adapter class for recycler view.
        weekRV.setAdapter(adapter);

        // passing a data from view modal.
        viewmodal = ViewModelProviders.of(this).get(ViewModal_week.class);

        // below line is use to get all the courses from view modal.
        viewmodal.getAllWeeks().observe(this, new Observer<List<WeekModal>>() {
            @Override
            public void onChanged(List<WeekModal> models) {
                // when the data is changed in our models we are
                // adding that list to our adapter class.
                adapter.submitList(models);
            }
        });
        // below method is use to add swipe to delete method for item of recycler view.
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // on recycler view item swiped then we are deleting the item of our recycler view.
                viewmodal.delete(adapter.getWeekAt(viewHolder.getAdapterPosition()));
                Toast.makeText(activity_main_week.this, "Weekly plan deleted", Toast.LENGTH_SHORT).show();
            }
        }).
                // below line is use to attach this to recycler view.
                        attachToRecyclerView(weekRV);
        // below line is use to set item click listener for our item of recycler view.
        adapter.setOnItemClickListener(new WeekRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(WeekModal model) {
                // after clicking on item of recycler view
                // we are opening a new activity and passing
                // a data to our activity.
                Intent intent = new Intent(activity_main_week.this, UpdateWeekActivity.class);
                intent.putExtra(UpdateWeekActivity.EXTRA_ID, model.getId());
                intent.putExtra(UpdateWeekActivity.EXTRA_WEEK_TITLE, model.getWeekTitle());
                intent.putExtra(UpdateWeekActivity.EXTRA_WEEK_DESCRIPTION, model.getWeekDescription());
                intent.putExtra(UpdateWeekActivity.EXTRA_WEEK_ONE, model.getWeekOne());

                // below line is to start a new activity and
                // adding a edit course constant.
                startActivityForResult(intent, EDIT_WEEK_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_WEEK_REQUEST && resultCode == RESULT_OK) {
            String weekTitle = data.getStringExtra(UpdateWeekActivity.EXTRA_WEEK_TITLE);
            String weekDescription = data.getStringExtra(UpdateWeekActivity.EXTRA_WEEK_DESCRIPTION);
            String weekOne = data.getStringExtra(UpdateWeekActivity.EXTRA_WEEK_ONE);
            WeekModal model = new WeekModal(weekTitle, weekDescription, weekOne);
            viewmodal.insert(model);
            Toast.makeText(this, "Course saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_WEEK_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(UpdateWeekActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Week can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String weekTitle = data.getStringExtra(UpdateWeekActivity.EXTRA_WEEK_TITLE);
            String weekDescription = data.getStringExtra(UpdateWeekActivity.EXTRA_WEEK_DESCRIPTION);
            String weekOne = data.getStringExtra(UpdateWeekActivity.EXTRA_WEEK_ONE);
            WeekModal model = new WeekModal(weekTitle, weekDescription, weekOne);
            model.setId(id);
            viewmodal.update(model);
            Toast.makeText(this, "Week plan updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Week plan not saved", Toast.LENGTH_SHORT).show();
        }
    }
}