package org.amrone.cgv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText mUsername;
    EditText mPassword;
    Button mBtnLogin;
    TextView mRegister;

    //ATTACH DATABASE
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
        db = new DatabaseHelper(this);
        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mRegister = (TextView) findViewById(R.id.register);

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(Login.this , Register.class);
                startActivity(registerIntent);
            }
        });
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsername.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                Boolean res = db.checkUser(username, password);

                if (res == true)
                {
                    Intent moveToMain = new Intent(Login.this, MainActivity.class);
                    startActivity(moveToMain);
                    finish();
                }
                else
                {
                    Toast.makeText(Login.this,"Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
