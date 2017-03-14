    package com.example.ismail1.login;

    import android.app.ProgressDialog;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.text.TextUtils;
    import android.view.View;
    import android.widget.ProgressBar;
    import android.widget.Toast;

    import com.google.firebase.auth.AuthResult;
    import com.google.firebase.auth.FirebaseAuth;

    public class MainActivity extends AppCompatActivity implements View.OnClickListener {

        private Button ButtonRegister;
        private EditText editTextEmail;
        private EditText editTextPassword;
        private TextView textViewSignin;

        private ProgressDialog progressDialog;
        private FirebaseAuth firebaseAuth;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            firebaseAuth = firebaseAuth.getInstance();
            if(firebaseAuth.getCurrentUser() != null){
                // the profile activity
                finish();
                startActivity(new intent(getApplicationContext(), profileActivity.class));
            }


            progressDialog = new ProgressDialog(this);

            ButtonRegister = (Button) findViewById(R.id.ButtonRegister);

            editTextEmail = (EditText) findViewById(R.id.editTextEmail);

            editTextPassword = (EditText) findViewById(R.id.editTextPassword);

            textViewSignin = (TextView) findViewById(R.id.textViewSignin);



            ButtonRegister.setOnClickListener(this);
            textViewSignin.setOnClickListener(this);



        }

        @Override
        public void onClick(View v) {
            if(view == ButtonRegister){

                RegisterUser();

            }

            if(view == textViewSignin){
    // will open login activity here

            }
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
                //@Override
                public void onComplete (@Nonnull task < AuthResult > task) {
                    if(task.isSucessful()){

                        //user sucessfully registered
                        Toast.makeText(MainActivity.this, "Registration sucessful", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();

                    }

                }

            });
        }

        private void RegisterUser() {

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



        }
    }
