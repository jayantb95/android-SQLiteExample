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
    private EditText edtNameSignup;
    private EditText edtEmailSignup;
    private EditText edtPasswordSignup;
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
        edtNameSignup = (EditText) findViewById(R.id.edt_name_signup);
        edtEmailSignup = (EditText) findViewById(R.id.edt_email_signup);
        edtPasswordSignup = (EditText) findViewById(R.id.edt_password_signup);

        mDatabaseAdapter = new DatabaseAdapter(this);
        mDatabaseAdapter.open();
    }

    private void clickListeners() {
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtNameSignup.getText().toString().trim();
                String email = edtEmailSignup.getText().toString().trim();
                String password = edtPasswordSignup.getText().toString().trim();

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(SignUpActivity.this, "Enter name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(SignUpActivity.this, "Enter email!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(SignUpActivity.this, "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    //save in database
                    mDatabaseAdapter.insertEntry(name, email, password);
                    Toast.makeText(SignUpActivity.this, "account created successfully!", Toast.LENGTH_SHORT).show();
                    clearAll();
                }

            }
        });

    }

    private void clearAll() {
        edtNameSignup.setText("");
        edtEmailSignup.setText("");
        edtPasswordSignup.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseAdapter.close();
    }
}
