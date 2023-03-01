package com.example.instructionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.instructionapp.R;
import com.example.instructionapp.adapter.ExerciseAdapter;
import com.example.instructionapp.model.ExerciseModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ExerciseAdapter exerciseAdapter;
    ArrayList<ExerciseModel> exerciseModelArrayList;
    BottomNavigationView bottomNavigationView;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_nav_bar);
        bottomNavigationView.setSelectedItemId(R.id.trenirovka);
        exerciseModelArrayList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Exercises");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ExerciseModel exerciseModel = snapshot.getValue(ExerciseModel.class);
                exerciseModelArrayList.add(exerciseModel);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.school:
                        Intent intent = new Intent(MainActivity.this, SchoolActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.trenirovka:
                        return true;
                }
                return false;
            }
        });
        recyclerView = findViewById(R.id.recycle_view);

        exerciseAdapter = new ExerciseAdapter(this, exerciseModelArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(exerciseAdapter);

    }
}