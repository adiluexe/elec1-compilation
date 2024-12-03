package com.example.elec1compilation;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elec1compilation.guided_exercises.EighthGuidedExercise;
import com.example.elec1compilation.guided_exercises.EleventhGuidedExerciseDragNDrop;
import com.example.elec1compilation.guided_exercises.EleventhGuidedExerciseIntent;
import com.example.elec1compilation.guided_exercises.EmailandCamera;
import com.example.elec1compilation.guided_exercises.FifthGuidedExercise;
import com.example.elec1compilation.guided_exercises.FirstGuidedExercise;
import com.example.elec1compilation.guided_exercises.FourthGuidedExercise;
import com.example.elec1compilation.guided_exercises.NinthGuidedExercise;
import com.example.elec1compilation.guided_exercises.NotificationAndBroadcastReciever;
import com.example.elec1compilation.guided_exercises.SMSandPhoneCall;
import com.example.elec1compilation.guided_exercises.SQLiteDatabaseDemo;
import com.example.elec1compilation.guided_exercises.SecondGuidedExercise;
import com.example.elec1compilation.guided_exercises.SeventhGuidedExercise;
import com.example.elec1compilation.guided_exercises.SixthGuidedExercise;
import com.example.elec1compilation.guided_exercises.SplashScreen;
import com.example.elec1compilation.guided_exercises.TenthGuidedExercise;
import com.example.elec1compilation.guided_exercises.ThirdGuidedExercise;
import com.example.elec1compilation.machine_problems.CCJitters;
import com.example.elec1compilation.machine_problems.Calculator;
import com.example.elec1compilation.machine_problems.EmployeePayrollComputation;
import com.example.elec1compilation.machine_problems.SemestralGradeComputation;

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
        exerciseItems.add(new ExerciseItem("Fifth Guided Exercise", "Guided Exercises", FifthGuidedExercise.class));
        exerciseItems.add(new ExerciseItem("Sixth Guided Exercise", "Guided Exercises", SixthGuidedExercise.class));
        exerciseItems.add(new ExerciseItem("Seventh Guided Exercise", "Guided Exercises", SeventhGuidedExercise.class));
        exerciseItems.add(new ExerciseItem("Eighth Guided Exercise", "Guided Exercises", EighthGuidedExercise.class));
        exerciseItems.add(new ExerciseItem("Ninth Guided Exercise", "Guided Exercises", NinthGuidedExercise.class));
        exerciseItems.add(new ExerciseItem("Tenth Guided Exercise", "Guided Exercises", TenthGuidedExercise.class));
        exerciseItems.add(new ExerciseItem("Eleventh Guided Exercise (Intent)", "Guided Exercises", EleventhGuidedExerciseIntent.class));
        exerciseItems.add(new ExerciseItem("Eleventh Guided Exercise (Drag and Drop)", "Guided Exercises", EleventhGuidedExerciseDragNDrop.class));
        exerciseItems.add(new ExerciseItem("Twelfth Guided Exercise", "Guided Exercises", NotificationAndBroadcastReciever.class));
        exerciseItems.add(new ExerciseItem("Thirteenth Guided Exercise", "Guided Exercises", EmailandCamera.class));
        exerciseItems.add(new ExerciseItem("Fourteenth Guided Exercise", "Guided Exercises", SMSandPhoneCall.class));
        exerciseItems.add(new ExerciseItem("SQLiteDatabaseDemo Guided Exercise", "Guided Exercises", SQLiteDatabaseDemo.class));
        exerciseItems.add(new ExerciseItem("SplashScreen Guided Exercise", "Guided Exercises", SplashScreen.class));
        // Add more guided exercises...

        // Machine Problems Section
        exerciseItems.add("Machine Problems");
        exerciseItems.add(new ExerciseItem("Machine Problem 2 (Calculator)", "Machine Problems", Calculator.class));
        exerciseItems.add(new ExerciseItem("Machine Problem 3 Batch 1 (SemestralGradeComputation)", "Machine Problems", SemestralGradeComputation.class));
        exerciseItems.add(new ExerciseItem("Machine Problem 3 Batch 2 (CCJitters)", "Machine Problems", CCJitters.class));
        exerciseItems.add(new ExerciseItem("Machine Problem 4 Batch (EmployeePayrollComputation)", "Machine Problems", EmployeePayrollComputation.class));

        // Add more machine problems...

        ExerciseAdapter adapter = new ExerciseAdapter(exerciseItems);
        recyclerView.setAdapter(adapter);
    }
}