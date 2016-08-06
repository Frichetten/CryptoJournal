package io.github.frichetten.cryptojournal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
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

                Toast t = Toast.makeText(getApplicationContext(), "Creating User", Toast.LENGTH_LONG);
                t.setGravity(Gravity.CENTER,0,0);
                t.show();

                //Need to hash the password
                String output = passwd;
                for(int i=0;i<1000;i++){
                    byte[] hashHolder = hashPass(output);
                    output = String.format("%064x", new java.math.BigInteger(1, hashHolder));
                }


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
