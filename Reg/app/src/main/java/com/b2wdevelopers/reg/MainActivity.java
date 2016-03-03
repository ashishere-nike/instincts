package com.b2wdevelopers.reg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText name,email,college,mob;
    Button reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        name=(EditText)findViewById(R.id.name);
        college=(EditText)findViewById(R.id.college);
        email=(EditText)findViewById(R.id.email);
        mob=(EditText)findViewById(R.id.mob);
        reg=(Button)findViewById(R.id.reg);

    }


}
