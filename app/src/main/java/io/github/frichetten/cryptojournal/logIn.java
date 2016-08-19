package io.github.frichetten.cryptojournal;

import android.net.wifi.ScanResult;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class logIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        ListView lv = (ListView) findViewById(R.id.userSelectListView);
        List<String> userData = readUsers();
        if (userData.size() == 0)
            userData.add("NO USERS IN SYSTEM");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                userData
        );
        lv.setAdapter(arrayAdapter);

    }

    private ArrayList<String> readUsers(){
        ArrayList<String> toReturn = new ArrayList<>();
        try {
            FileInputStream fis = openFileInput("Storage.txt");
            byte[] input = new byte[fis.available()];
            while(fis.read(input) != -1) {
                String in = new String(input);
                //toReturn.add(in.substring(0,in.indexOf("::")));
                Log.d("Result",in);
            }
            for(int i=0;i<toReturn.size();i++){
                Log.d("Result",toReturn.get(i));
            }
            //Log.d("Result", content);
        }   catch (java.io.FileNotFoundException e) { Log.d("Result", "File not found"); }
            catch (java.io.IOException e) { Log.d("Result", "IO Exception"); }
        return toReturn;
    }
}
