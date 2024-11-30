package com.example.elec1compilation;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private List<Object> items;

    public ExerciseAdapter(List<Object> items) {
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof String) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == TYPE_HEADER) {
            View headerView = inflater.inflate(R.layout.list_header, parent, false);
            return new HeaderViewHolder(headerView);
        }

        View itemView = inflater.inflate(R.layout.exercise_list_item, parent, false);
        return new ExerciseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;
            headerHolder.headerTitle.setText((String) items.get(position));
        } else if (holder instanceof ExerciseViewHolder) {
            ExerciseViewHolder exerciseHolder = (ExerciseViewHolder) holder;
            ExerciseItem exerciseItem = (ExerciseItem) items.get(position);
            exerciseHolder.bind(exerciseItem);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // Header ViewHolder
    class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView headerTitle;

        HeaderViewHolder(View view) {
            super(view);
            headerTitle = view.findViewById(R.id.header_title);
        }
    }

    // Exercise Item ViewHolder
    class ExerciseViewHolder extends RecyclerView.ViewHolder {
        TextView titleText;

        ExerciseViewHolder(View view) {
            super(view);
            titleText = view.findViewById(R.id.exercise_title);

            view.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    ExerciseItem item = (ExerciseItem) items.get(position);
                    Intent intent = new Intent(v.getContext(), item.getActivityClass());
                    v.getContext().startActivity(intent);
                }
            });
        }

        void bind(ExerciseItem exercise) {
            titleText.setText(exercise.getTitle());
        }
    }
}