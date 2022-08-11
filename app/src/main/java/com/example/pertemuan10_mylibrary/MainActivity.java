package com.example.pertemuan10_mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText inputName;
    private TextView names;
    private Button btnSimpan, btnAmbil;
    private ArrayList<String> arrayList;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        names = findViewById(R.id.tvNames);
        inputName = findViewById(R.id.etName);

        btnSimpan = findViewById(R.id.btnStore);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper.addStudentDetail(inputName.getText().toString());
                inputName.setText("");
                Toast.makeText(MainActivity.this, "Stored successfull", Toast.LENGTH_SHORT).show();
            }
        });

        btnAmbil = findViewById(R.id.btnGetAll);
        btnAmbil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList = databaseHelper.getAllStudents();
                names.setText("");

                for (String item:arrayList) {
                    names.append(item + " ");
                }

            }
        });
    }
}