package com.example.it18153682;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.it18153682.Database.DBHelper;

public class EditProfile extends AppCompatActivity {



    DBHelper db;

    TextView userNameTXT,DOBTXT,PasswordTXT;
    RadioGroup gender;
    RadioButton radioButton;
    Button Delete , Edit,Search;

    public void clear(){

        userNameTXT.setText("");
        DOBTXT.setText("");
        PasswordTXT.setText("");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        db = new DBHelper(this);

        userNameTXT=findViewById(R.id.UserName);
        DOBTXT = findViewById(R.id.DOB);
        PasswordTXT  = findViewById(R.id.Password);

        gender = findViewById(R.id.Gender);
       //radioButton = findViewById(Gid);
        Delete = findViewById(R.id.button3);
        Edit = findViewById(R.id.update);
        Search = findViewById(R.id.button5);

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Cursor cursor = db.readAllInfor();
                String  UserName = userNameTXT.getText().toString();
                while (cursor.moveToNext()){
                    String us_name = cursor.getString(1);

                    if (us_name.equals(UserName)){
                        final String _ID = cursor.getString(0);

                        Cursor cursor1 = db.readAllInfor(_ID);



                        while (cursor1.moveToNext()){

                            DOBTXT.setText(cursor1.getString(2));
                            String SElectedGender = cursor1.getString(3);
                            PasswordTXT.setText(cursor1.getString(4));

                            if(SElectedGender.equals("Female")){
                                int Fgender_ID = 2131165188;
                                radioButton = findViewById(Fgender_ID);
                                radioButton.setChecked(true);

                            }else{
                                int Mgender_ID = 2131165191;
                                radioButton = findViewById(Mgender_ID);
                                radioButton.setChecked(true);
                            }

                        }

                        Edit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                String UserName = userNameTXT.getText().toString();
                                String dob = DOBTXT.getText().toString();
                                String password = PasswordTXT.getText().toString();
                                int Gid = gender.getCheckedRadioButtonId();
                                radioButton = findViewById(Gid);
                                String Gender = radioButton.getText().toString();



                                boolean okk =db.updateInfor(_ID,UserName,dob,Gender,password);
                                System.out.println("----------------------....."+okk);

                                if (okk){


                                    Toast toast = Toast.makeText(EditProfile.this,"Your Data IS updated",Toast.LENGTH_LONG);
                                    toast.show();
                                }else {
                                    Toast toast = Toast.makeText(EditProfile.this,"Incorrect User Name",Toast.LENGTH_LONG);
                                    toast.show();
                                }

                            }
                        });

                        Delete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                                db.delete(_ID);

                                Toast toast = Toast.makeText(EditProfile.this,"Your Data Is Deleted",Toast.LENGTH_SHORT);
                                toast.show();


                                int Gid = gender.getCheckedRadioButtonId();
                                radioButton = findViewById(Gid);
                                radioButton.setChecked(false);
                                clear();


                            }
                        });

                        break;
                    }

                    else if(cursor.isLast()) {
                        Toast toast = Toast.makeText(EditProfile.this,"THIS IS INVALIDE",Toast.LENGTH_LONG);
                        toast.show();
                        int Gid = gender.getCheckedRadioButtonId();
                        radioButton = findViewById(Gid);
                        radioButton.setChecked(false);
                        clear();
                    }

                }



            }
        });



    }
}
