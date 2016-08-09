package io.github.frichetten.cryptojournal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.FileInputStream;

public class logIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        String content = "";
        try {
            FileInputStream fis = openFileInput("Storage.txt");
            byte[] input = new byte[fis.available()];
            while(fis.read(input) != -1)
                content += new String(input);
            Log.d("Result", content);
        }   catch (java.io.FileNotFoundException e) { Log.d("Result", "File not found"); }
            catch (java.io.IOException e) { Log.d("Result", "IO Exception"); }
    }
}
