        package com.example.ismail1.login;

        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.text.TextUtils;
        import android.view.View;
        import android.widget.Toast;

        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;

        public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

            private Button ButtonSignin;
            private EditText editTextEmail;
            private EditText editTextPassword;
            private TextView textViewSignup;

            private ProgressDialog progressDialog;
            private FirebaseAuth firebaseAuth;


            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_login);


                ButtonSignin = (Button) findViewById(R.id.ButtonSignin);

                editTextEmail = (EditText) findViewById(R.id.editTextEmail);

                editTextPassword = (EditText) findViewById(R.id.editTextPassword);

                textViewSignup = (TextView) findViewById(R.id.textViewSignup);

                progressDialog = new ProgressDialog(this);
                firebaseAuth = firebaseAuth.getInstance();

                if(firebaseAuth.getCurrentUser() != null){
                    // the profile activity
                    finish();
                    startActivity(new Intent(getApplicationContext(), profileActivity.class));
                }



                ButtonSignin.setOnClickListener(this);
                textViewSignup.setOnClickListener(this);

            }

            private void UserLogin() {

                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    //email is empty
                    Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
                    //stops function execution further
                    return;


                }
                if (TextUtils.isEmpty(password)) {
                    //password is empty
                    Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
                    //stops function execution further
                    return;
                }
                // if validations are ok it will show a progress bar

                progressDialog.setMessage("Registering user please wait");
                progressDialog.show();

                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            //@Override
                            public void onComplete(@Nonnull task<AuthResult> task) {
                                progressDialog.dismiss();
                                if (task.isSucessful()) {

                                    //user sucessfully registered
                                    finish();
                                    startActivity(new intent(getApplicationContext(), profileActivity.class));


                                }

                            }


                            @Override
                            public void onClick(View view) {

                                public void onClick (View view){
                                    if (view == ButtonSignin) {

                                        UserLogin();

                                    }

                                    if (view == textViewSignup) {
                                        Finish();
                                        startActivities(new Intent(this, LoginActivity.class));
                                    }

                                }
                            }
                        }
