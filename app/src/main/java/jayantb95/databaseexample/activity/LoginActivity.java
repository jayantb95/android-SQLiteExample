package jayantb95.databaseexample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import jayantb95.databaseexample.R;
import jayantb95.databaseexample.other.DatabaseAdapter;

public class LoginActivity extends AppCompatActivity {

    private DatabaseAdapter mDatabaseAdapter;

    private Button btnLogin;
    private Button btnRegister;

    private EditText edtEmailLogin;
    private EditText edtPasswordLogin;


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
        btnRegister = (Button) findViewById(R.id.btn_link_register);
        btnLogin = (Button) findViewById(R.id.btn_login);
        edtEmailLogin = (EditText) findViewById(R.id.edt_email_login);
        edtPasswordLogin = (EditText) findViewById(R.id.edt_password_login);
    }

    private void clickListeners() {

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmailLogin.getText().toString().trim();
                String password = edtPasswordLogin.getText().toString().trim();

                String storedPassword = mDatabaseAdapter.getSingleEntry(email);


                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(LoginActivity.this, "Enter email!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.equals(storedPassword)) {
                    Toast.makeText(LoginActivity.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, UserDetails.class);
                    intent.putExtra("email", email);
                    intent.putExtra("password", password);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "email or Password does not match our records.", Toast.LENGTH_LONG).show();
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
