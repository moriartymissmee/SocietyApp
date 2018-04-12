package com.example.sundeepsingh.societyapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    QueryModel queryModel = new QueryModel();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_dashboard:
                    return true;
                case R.id.navigation_notifications:
                    selectedFragment = new Querry();
                    getSupportFragmentManager().beginTransaction().replace(R.id.quizFrame, selectedFragment).commit();
                    Button submitQuery = findViewById(R.id.querry_submit);

                    submitQuery.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            EditText query_title = findViewById(R.id.querry_title);
                            EditText query_description = findViewById(R.id.querry_description);
                            queryModel.title = query_title.getText().toString();
                            queryModel.description = query_description.getText().toString();
                            DatabaseReference queryRef = database.getReference("Query");
                            queryRef.child(queryModel.title).setValue(queryModel);
                            Toast.makeText(getApplicationContext(),"Data Posted",Toast.LENGTH_SHORT).show();
                        }
                    });
                    return true;
            }

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
