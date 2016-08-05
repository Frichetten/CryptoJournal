package io.github.frichetten.cryptojournal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;

public class createNewUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_user);

        final EditText username = (EditText) findViewById(R.id.usernameEditText);
        username.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        final EditText password = (EditText) findViewById(R.id.passwordEditText);

        Button createUser = (Button) findViewById(R.id.submitUserButton);
        createUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String passwd = password.getText().toString();

                //Need to hash the password
                byte[] hash = hashPass(passwd);
                String output = String.format("%064x", new java.math.BigInteger(1, hash));

                byte[] r2 = hashPass(output);
                String output2 = String.format("%064x", new java.math.BigInteger(1, r2));

                byte[] r3 = hashPass(output2);
                String output3 = String.format("%064x", new java.math.BigInteger(1, r3));
                Toast.makeText(getApplicationContext(), user + " " + output3, Toast.LENGTH_LONG).show();
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
