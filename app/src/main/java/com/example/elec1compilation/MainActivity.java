package com.example.elec1compilation;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elec1compilation.guided_exercises.FirstGuidedExercise;
import com.example.elec1compilation.guided_exercises.FourthGuidedExercise;
import com.example.elec1compilation.guided_exercises.SecondGuidedExercise;
import com.example.elec1compilation.guided_exercises.ThirdGuidedExercise;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create list of exercises
        List<Object> exerciseItems = new ArrayList<>();

        // Guided Exercises Section
        exerciseItems.add("Guided Exercises");
        exerciseItems.add(new ExerciseItem("First Guided Exercise", "Guided Exercises", FirstGuidedExercise.class));
        exerciseItems.add(new ExerciseItem("Second Guided Exercise", "Guided Exercises", SecondGuidedExercise.class));
        exerciseItems.add(new ExerciseItem("Third Guided Exercise", "Guided Exercises", ThirdGuidedExercise.class));
        exerciseItems.add(new ExerciseItem("Fourth Guided Exercise", "Guided Exercises", FourthGuidedExercise.class));
        // Add more guided exercises...

        // Machine Problems Section
        exerciseItems.add("Machine Problems");
//        exerciseItems.add(new ExerciseItem("Calculator", "Machine Problem", DataProcessingActivity.class));

        // Add more machine problems...

        ExerciseAdapter adapter = new ExerciseAdapter(exerciseItems);
        recyclerView.setAdapter(adapter);
    }
}