package app.testingappnew;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {


    Button btn_login;
    // Declare / create (obj)the edTxt
    EditText edTxt;
    // Declare / create (obj)the edpwd
    EditText edpwd;

    //shared preferences to store the values in cookie/ session.
    SharedPreferences prefs;

    // Shared preference editor for edit the session
    SharedPreferences.Editor editor;

    String username=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // make the preferences edit the data.
        try {
            prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            editor = prefs.edit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        username = prefs.getString("sharedusername",username);

        if (username!=null)
        {
            // Move the one activity to next activity
            Intent intentobj = new Intent(MainActivity.this, SecondActivity.class);
            //Start the A
            startActivity(intentobj);
            finish();
        }
        else {


            // Declare / create (obj)the btn_login by the id of the obj.
            btn_login = (Button) findViewById(R.id.btn);
            // Declare / create (obj)the edTxt by the id of the obj.
            edTxt = (EditText) findViewById(R.id.eTusername);
            // Declare / create (obj)the edpwd by the id of the obj.
            edpwd = (EditText) findViewById(R.id.eTpassword);

            btn_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Get the string from the object. edTxt.
                    String username = edTxt.getText().toString();
                    //Get the string from the object. edpwd.
                    String pwd = edpwd.getText().toString();


                    // Check the Username is having value or not
                    if (username.equals("")) {

                        edTxt.setError("fhfffhhf");
                        // Incase its contains the empty below part will work
                        //call the Alert method to make the alert msg and pass the message to display.
                        showAlert("Enter User Name");
                        // Call the show Toast M
                        // ethod.
                        showToast("Enter User Name");
                    }
                    // Check the Password is having value or not
                    else if (pwd.equals("")) {
                        // Incase its contains the empty below part will work
                        //call the Alert method to make the alert msg and pass the message to display.
                        showAlert("Enter Password");
                        // Call the show Toast Method.
                        showToast("Enter Password");
                    } else {
                        // create the bundle for the pass the value to the next activity
                        Bundle bundle = new Bundle();


                        editor.putString("sharedusername", username);
                        editor.commit();
                        //Store the data in the bundle

                        bundle.putString("username", username);

                        // Move the one activity to next activity
                        Intent intentobj = new Intent(MainActivity.this, SecondActivity.class);
                        // put extras to get the values on next activity
                        intentobj.putExtras(bundle);
                        //Start the A
                        startActivity(intentobj);
                        finish();
                    }
                }

            });
        }
    }



    @Override
    protected void onStart() {
        super.onStart();
        showToast("Activity Started !");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        showToast("Activity Restarting !");
    }

    @Override
    protected void onPause() {
        super.onPause();
        showToast("Activity Paused !");
    }

    @Override
    protected void onStop() {
        super.onStop();
        showToast("Activity Stopped !");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showToast("Activity Destroyed !");
    }

    //Alert message method. Its getting the message and display in the alert box.
    private void showAlert(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle(R.string.app_name);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    void showToast(String message)
    {
        //Show the message below just blinking
        Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();
    }
}
