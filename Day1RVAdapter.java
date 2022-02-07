package com.example.selfdisciplinemaster.Day1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.selfdisciplinemaster.R;

public class Day1RVAdapter extends ListAdapter<Day1Modal, Day1RVAdapter.ViewHolder> {

    private OnItemClickListener listener;

    Day1RVAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Day1Modal> DIFF_CALLBACK = new DiffUtil.ItemCallback<Day1Modal>() {
        @Override
        public boolean areItemsTheSame(Day1Modal oldItem, Day1Modal newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Day1Modal oldItem, Day1Modal newItem) {
            return oldItem.getDay1Title().equals(newItem.getDay1Title()) &&
                    oldItem.getDay1TimeStart1().equals(newItem.getDay1TimeStart1()) &&
                    oldItem.getDay1TimeStart2().equals(newItem.getDay1TimeStart2()) &&
                    oldItem.getDay1TimeEnd().equals(newItem.getDay1TimeEnd()) &&
                    oldItem.getDay1Date().equals(newItem.getDay1Date()) &&
                    oldItem.getDay1Note().equals(newItem.getDay1Note());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.day1_rv_item, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Day1Modal model = getDay1At(position);
        holder.day1TitleTV.setText(model.getDay1Title());
        holder.day1TimeStartTV1.setText(model.getDay1TimeStart1());
        holder.day1TimeStartTV2.setText(model.getDay1TimeStart2());
        holder.Day1TimeEndTV.setText(model.getDay1TimeEnd());
        holder.day1DateTV.setText(model.getDay1Date());

    }

    public Day1Modal getDay1At(int position) {
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView day1TitleTV, day1TimeStartTV1, day1TimeStartTV2, Day1TimeEndTV, day1DateTV, day1NoteTV;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            day1TitleTV = itemView.findViewById(R.id.idTV_day1_title);
            day1TimeStartTV1 = itemView.findViewById(R.id.idTV_day1_Start1);
            day1TimeStartTV2 = itemView.findViewById(R.id.idTV_day1_Start2);
            Day1TimeEndTV = itemView.findViewById(R.id.idTV_day1_end);
            day1DateTV = itemView.findViewById(R.id.idTV_day1_Date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Day1Modal model);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}