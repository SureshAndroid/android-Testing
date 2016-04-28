package app.testingappnew;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class SecondActivity extends AppCompatActivity {

    TextView tv;
    String username = null;
    SharedPreferences prefs;

    Button btn;
    Button webbtn;
    Button fragbtn;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tv = (TextView) findViewById(R.id.welcomemsg);
        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        username = prefs.getString("sharedusername", username);

        // btn = (Button) findViewById(R.id.btnListview);

        webbtn = (Button) findViewById(R.id.webbtn);

        fragbtn = (Button) findViewById(R.id.fragbtn);
        //Bundle extras = getIntent().getExtras();

        //if (extras != null)
        //{
        //   username = extras.getString("username");
        //}
        tv.setText("welcome " + username);
        showToast("Welcome " + username);

        //btn.setOnClickListener(new View.OnClickListener() {
        //  @Override
        //  public void onClick(View v) {
        //      Intent intobj = new Intent(SecondActivity.this, ListActivity.class);
        //      startActivity(intobj);
        //  }
        //});

        LocalFile localfile = new LocalFile();
        localfile.savedToExternal(SecondActivity.this,"Data Stored from Second Activity goto Fragment 2","testing");

        Toast.makeText(SecondActivity.this,"Data Stored from Second Activity goto Fragment 2",Toast.LENGTH_SHORT).show();


        webbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intobj1 = new Intent(SecondActivity.this, Webview.class);
                startActivity(intobj1);
            }
        });
        fragbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intobj1 = new Intent(SecondActivity.this, FragmentActivity.class);
                startActivity(intobj1);
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void showAlert(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(SecondActivity.this).create();
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

    void showToast(String message) {
        //Show the message below just blinking
        Toast.makeText(SecondActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_share:
                //Toast.makeText(SecondActivity.this, "Menu Clicked", Toast.LENGTH_SHORT).show();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + "1234567890"));
                startActivity(callIntent);

                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Second Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://app.testingappnew/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Second Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://app.testingappnew/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
