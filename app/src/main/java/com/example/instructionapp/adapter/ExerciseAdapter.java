package com.example.instructionapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.instructionapp.R;
import com.example.instructionapp.DefaultActivity;
import com.example.instructionapp.FirstActivity;
import com.example.instructionapp.SecondActivity;
import com.example.instructionapp.ZeroActivity;
import com.example.instructionapp.model.ExerciseModel;

import java.util.ArrayList;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ExerciseModel> exerciseModelArrayList;

    public ExerciseAdapter(Context context, ArrayList<ExerciseModel> exerciseModelArrayList) {
        this.context = context;
        this.exerciseModelArrayList = exerciseModelArrayList;

    }

    @NonNull
    @Override
    public ExerciseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.exercise_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseAdapter.ViewHolder holder, int position) {
        ExerciseModel model = exerciseModelArrayList.get(position);
        holder.title.setText(model.getTitle());
        holder.description.setText(model.getDescription());
        holder.image.setImageResource(model.getImage());

    }

    @Override
    public int getItemCount() {
        return exerciseModelArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title, description;
        private ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.exe_txt_item);
            description = itemView.findViewById(R.id.exe_des_item);
            image = itemView.findViewById(R.id.exe_img_item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            final Intent intent;
            switch (getAdapterPosition()) {
                case 0:
                    intent = new Intent(context, ZeroActivity.class);
                    break;
                case 1:
                    intent = new Intent(context, FirstActivity.class);
                    break;
                case 2:
                    intent = new Intent(context, SecondActivity.class);
                    break;

                default:
                    intent = new Intent(context, DefaultActivity.class);
                    break;
            }
            context.startActivity(intent);
        }
    }

}
