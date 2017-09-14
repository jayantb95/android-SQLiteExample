package jayantb95.databaseexample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import jayantb95.databaseexample.R;
import jayantb95.databaseexample.other.DatabaseAdapter;

public class SignUpActivity extends AppCompatActivity {

    private Button btnSignup;
    private EditText edtUsernameSignup;
    private EditText edtPasswordSignup;
    private EditText edtConfPasswordSignup;
    private DatabaseAdapter mDatabaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initialize();
        clickListeners();
    }

    private void initialize() {
        btnSignup = (Button) findViewById(R.id.btn_signup_reg);
        edtUsernameSignup = (EditText) findViewById(R.id.edt_username);
        edtPasswordSignup = (EditText) findViewById(R.id.edt_password);
        edtConfPasswordSignup = (EditText) findViewById(R.id.edt_conf_password);

        mDatabaseAdapter = new DatabaseAdapter(this);
        mDatabaseAdapter.open();
    }

    private void clickListeners() {
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsernameSignup.getText().toString().trim();
                String password = edtPasswordSignup.getText().toString().trim();
                String cnfPwd = edtConfPasswordSignup.getText().toString().trim();

                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(SignUpActivity.this, "Enter username!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(SignUpActivity.this, "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(SignUpActivity.this, "Enter password confirmation!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(cnfPwd)) {
                    Toast.makeText(SignUpActivity.this, "passwords does not match!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    //save in database
                    mDatabaseAdapter.insertEntry(username, password);
                    Toast.makeText(SignUpActivity.this, "account successfully created.", Toast.LENGTH_SHORT).show();
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
