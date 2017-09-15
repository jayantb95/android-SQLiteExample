package jayantb95.databaseexample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import jayantb95.databaseexample.R;

public class UserDetails extends AppCompatActivity {

    private TextView txtNameUser;

    private String sqlEmail;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        initialize();
        clickListeners();
    }

    private void initialize() {
        txtNameUser = (TextView) findViewById(R.id.txt_login_user);
        btnLogout = (Button) findViewById(R.id.btn_logout);
        Intent intent = getIntent();
        String email = intent.getExtras().getString("email");
        String password = intent.getExtras().getString("password");
//        txtNameUser.setText("email: " + email + "\npassword: " + password);
        txtNameUser.setText("welcome " + email + "!");
    }

    private void clickListeners() {
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserDetails.this, "Successfully logged out", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
