package com.example.it18153682;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.it18153682.Database.DBHelper;

public class ProfileManagement extends AppCompatActivity {

    DBHelper db;

    TextView userNameTXT,DOBTXT,PasswordTXT;
    RadioGroup gender;
    RadioButton radioButton;
    Button Update , Register;


    public void clear(){
        userNameTXT.setText(" ");
        DOBTXT.setText(" ");
        PasswordTXT.setText(" ");
        radioButton.setChecked(false);
    }
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        db = new DBHelper(this);




        Update = findViewById(R.id.update);

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),EditProfile.class);
                startActivity(intent);
            }
        });

        //find radio button value



        //end

        userNameTXT = findViewById(R.id.UserName);
        DOBTXT = findViewById(R.id.DOB);
        PasswordTXT = findViewById(R.id.Password);
        Register = findViewById(R.id.save);




        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{




                    final String userName = userNameTXT.getText().toString();
                    final String dob = DOBTXT.getText().toString();
                    final String password = PasswordTXT.getText().toString();
                    gender = findViewById(R.id.Gender);
                    final int Gid = gender.getCheckedRadioButtonId();
                    radioButton =  findViewById(Gid);
                    final String val = String.valueOf(Gid);



                    if(userName.isEmpty()){
                        userNameTXT.requestFocus();
                        userNameTXT.setError("please fill this ");
                    }
                    else if(dob.isEmpty()){
                        DOBTXT.requestFocus();
                        DOBTXT.setError("please fill this ");
                    }

                    else if(password.isEmpty()){
                        PasswordTXT.requestFocus();
                        PasswordTXT.setError("please fill this ");
                    }




                    else if (val.equals("-1")){
                        Toast toast = Toast.makeText(ProfileManagement.this,"Please Fill Select Your Gender",Toast.LENGTH_LONG);
                        toast.show();

                    }

                    else{


                         String selectedGender =radioButton.getText().toString();


                             db.addInfo(userName,dob,selectedGender,password);

                             Toast toast = Toast.makeText(ProfileManagement.this,"YOUR DATA IS ADDED",Toast.LENGTH_LONG);
                             toast.show();

                             clear();




                    }



                }catch (Exception e){


                }




            }
        });







    }
}
