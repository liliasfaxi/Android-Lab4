package tn.medtech.lab4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText nameInput, ageInput;
    TextView nameResult, ageResult;
    private static final String MY_PREFS_NAME = "PatientsRecords";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = (EditText)findViewById(R.id.name);
        ageInput = (EditText)findViewById(R.id.age);

        nameResult = (TextView) findViewById(R.id.result_name);
        ageResult= (TextView) findViewById(R.id.result_age);
    }


    public void save(View v){
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("name", nameInput.getText().toString());
        editor.putInt("age", Integer.valueOf(ageInput.getText().toString()));
        editor.commit();


        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String name = prefs.getString("name", "No name defined!");
        int age = prefs.getInt("age",0);
        nameResult.setText("Name: "+name);
        ageResult.setText("Age: "+age);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.next) {
            Intent intent = new Intent(this,FilesActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
