package com.example.ripo.mcctest;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.provider.ContactsContract.*;

public class MainActivity extends AppCompatActivity {
    private DBHelper mydb ;
    EditText editTextname;
    EditText editTextage;
    EditText editTextphone;
    EditText editTextemail;
    ImageView image;
    int id_To_Update = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DBHelper(this);
        editTextname=(EditText)findViewById(R.id.ed1);
        editTextage=(EditText)findViewById(R.id.ed2);
        editTextphone=(EditText)findViewById(R.id.ed3);
        editTextemail=(EditText)findViewById(R.id.ed4);
        image= (ImageView) findViewById(R.id.imageView);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            int Value = extras.getInt("id");

            if(Value>0){
                //means this is the view part not the add contact part.
                Cursor rs = mydb.getData(Value);
                id_To_Update = Value;
                rs.moveToFirst();

                String nam = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_NAME));
                String age = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_AGE));
                String emai = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_EMAIL));
                String phon = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_PHONE));
                String image = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_EMAIL));

                if (!rs.isClosed())  {
                    rs.close();
                }
                Button b = (Button)findViewById(R.id.button);
                b.setVisibility(View.INVISIBLE);

                editTextname.setText((CharSequence)nam);
                editTextname.setFocusable(false);
                editTextname.setClickable(false);

                editTextage.setText((CharSequence)age);
                editTextage.setFocusable(false);
                editTextage.setClickable(false);

                editTextemail.setText((CharSequence)emai);
                editTextemail.setFocusable(false);
                editTextemail.setClickable(false);

                editTextphone.setText((CharSequence)phon);
                editTextphone.setFocusable(false);
                editTextphone.setClickable(false);
            }
        }
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Contacts.Photo);
                startActivity(intent);
            }
        });
    }

        }

