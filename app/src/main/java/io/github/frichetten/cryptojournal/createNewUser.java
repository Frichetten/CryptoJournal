package io.github.frichetten.cryptojournal;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.security.MessageDigest;

public class createNewUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_user);

        //Creating the EditText objects
        final EditText username = (EditText) findViewById(R.id.usernameEditText);
        username.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        final EditText password = (EditText) findViewById(R.id.passwordEditText);

        Button createUser = (Button) findViewById(R.id.submitUserButton);
        createUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Getting user input
                String user = username.getText().toString();
                String passwd = password.getText().toString();

                //Need to hash the password
                String output = passwd;
                for(int i=0;i<1000;i++){
                    byte[] hashHolder = hashPass(output);
                    output = String.format("%064x", new java.math.BigInteger(1, hashHolder));
                }

                //TEST CODE->
                Toast t = Toast.makeText(getApplicationContext(), "Creating User: " + user + " " + output, Toast.LENGTH_LONG);
                t.setGravity(Gravity.CENTER,0,0);
                t.show();

                //Now that we have the input we must put that input into the storage media
                try {
                    FileOutputStream out = openFileOutput("Storage.txt", Context.MODE_PRIVATE);
                    out.write(user.getBytes());
                    out.close();
                    Log.d("Results", user);
                }   catch (java.io.FileNotFoundException e) { Log.d("Result", "File not found"); }
                    catch (java.io.IOException e) { Log.d("Result", "IO Exception"); }

                getApplicationContext().deleteFile("Storage.txt");
            }
        });
    }

    private static byte[] hashPass(String password){
        try {
            java.security.MessageDigest hasher = java.security.MessageDigest.getInstance("SHA-256");
            hasher.reset();
            hasher.update(password.getBytes("UTF-8"));
            return hasher.digest();
        }
        catch(java.security.NoSuchAlgorithmException e) { Log.d("Result", "No Such Algorithm Exception"); }
        catch(java.io.UnsupportedEncodingException e) { Log.d("Result", "Unsupported Encoding Exception"); }
        return null;
    }
}
