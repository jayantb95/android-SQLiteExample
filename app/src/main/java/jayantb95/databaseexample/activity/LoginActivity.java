package jayantb95.databaseexample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import jayantb95.databaseexample.R;
import jayantb95.databaseexample.other.DatabaseAdapter;

public class LoginActivity extends AppCompatActivity {

    private DatabaseAdapter mDatabaseAdapter;

    private Button btnLogin;
    private Button btnSignup;

    private EditText edtUsername;
    private EditText edtPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialize();
        clickListeners();

    }

    private void initialize() {
        mDatabaseAdapter = new DatabaseAdapter(this);
        mDatabaseAdapter.open();
        btnSignup = (Button) findViewById(R.id.btn_signup);
        btnLogin = (Button) findViewById(R.id.btn_login);
        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
    }

    private void clickListeners() {

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                String storedPassword = mDatabaseAdapter.getSingleEntry(username);

                if (password.equals(storedPassword)) {
                    Toast.makeText(MainActivity.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "User Name or Password does not match our records.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseAdapter.close();
    }
}
