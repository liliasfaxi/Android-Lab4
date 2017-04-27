package tn.medtech.lab4;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FilesActivity extends AppCompatActivity {

    private static String FILENAME = "myFile";
    EditText age, name;
    TextView internalRes, externalRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files);

        name = (EditText)findViewById(R.id.name_file);
        age = (EditText)findViewById(R.id.age_file);

        internalRes = (TextView) findViewById(R.id.internal_result);
        externalRes= (TextView) findViewById(R.id.external_result);


    }

    public void saveInternal(View v) throws IOException{
        FileOutputStream fos = openFileOutput(FILENAME, MODE_PRIVATE | MODE_APPEND);
        String message = "My name is "+name.getText().toString()+
                " and I am "+ age.getText()+ " years old.\n";
        fos.write(message.getBytes());
        fos.close();

        internalRes.setText(readInternal());
    }

    public StringBuffer readInternal() throws IOException{
        FileInputStream fis = openFileInput(FILENAME);
        StringBuffer buffer = new StringBuffer();
        int content;
        while ((content = fis.read())!=-1){
            buffer.append((char) content);
        }

        fis.close();
        return buffer;
    }


    public boolean isExternalStorageWritable(){
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)){
            return true;
        }
        return false;
    }

    public void saveExternal(View v) throws IOException{
        if (isExternalStorageWritable()){
            File directory = Environment.getExternalStorageDirectory();
            File file = new File(directory + "/"+FILENAME);
            FileOutputStream fos = new FileOutputStream(file);
            String message = "My name is "+name.getText().toString()+
                    " and I am "+ age.getText()+ " years old.\n";
            fos.write(message.getBytes());
            fos.close();

            externalRes.setText(readExternal());
        }
    }

    public boolean isExternalStorageReadable(){
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)){
            return true;
        }
        return false;
    }

    public StringBuffer readExternal() throws IOException{
        if (isExternalStorageReadable()){
            File directory = Environment.getExternalStorageDirectory();
            File file = new File(directory + "/"+FILENAME);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                StringBuffer buffer = new StringBuffer();
                int content;
                while ((content = fis.read())!=-1){
                    buffer.append((char) content);
                }
                fis.close();

                return buffer;
            }else{
                return new StringBuffer("File not found!");
            }
        }
        return null;
    }

}
