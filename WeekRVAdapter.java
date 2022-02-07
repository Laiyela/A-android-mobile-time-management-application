package com.example.selfdisciplinemaster.Week;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.selfdisciplinemaster.R;

public class WeekRVAdapter extends ListAdapter<WeekModal, WeekRVAdapter.ViewHolder> {

    // creating a variable for on item click listener.
    private OnItemClickListener listener;

    // creating a constructor class for our adapter class.
    WeekRVAdapter() {
        super(DIFF_CALLBACK);
    }

    // creating a call back for item of recycler view.
    private static final DiffUtil.ItemCallback<WeekModal> DIFF_CALLBACK = new DiffUtil.ItemCallback<WeekModal>() {
        @Override
        public boolean areItemsTheSame(WeekModal oldItem, WeekModal newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(WeekModal oldItem, WeekModal newItem) {
            // below line is to check the course name, description and course duration.
            return oldItem.getWeekTitle().equals(newItem.getWeekTitle()) &&
                    oldItem.getWeekDescription().equals(newItem.getWeekDescription()) &&
                    oldItem.getWeekOne().equals(newItem.getWeekOne());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is use to inflate our layout
        // file for each item of our recycler view.
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.week_rv_item, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // below line of code is use to set data to
        // each item of our recycler view.
        WeekModal model = getWeekAt(position);
        holder.weekTitleTV.setText(model.getWeekTitle());
        holder.weekDescTV.setText(model.getWeekDescription());
        holder.weekOneTV.setText(model.getWeekOne());
    }

    // creating a method to get course modal for a specific position.
    public WeekModal getWeekAt(int position) {
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // view holder class to create a variable for each view.
        TextView weekTitleTV, weekDescTV, weekOneTV;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing each view of our recycler view.
            weekTitleTV = itemView.findViewById(R.id.idTVWeekTitle);
            weekDescTV = itemView.findViewById(R.id.idTVWeekDescription);
            weekOneTV = itemView.findViewById(R.id.idTVWeekOne);

            // adding on click listener for each item of recycler view.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // inside on click listener we are passing
                    // position to our item of recycler view.
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(WeekModal model);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}