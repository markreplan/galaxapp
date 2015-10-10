package galaxapp.galaxapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "galaxapp.galaxapp.MESSAGE";

    public class User {
        private String name;
        private String phonenumber;
        private String occupation;

        public User() {}

        public User(String name, String phonenumber, String occupation) {
            this.name = name;
            this.phonenumber = phonenumber;
            this.occupation = occupation;
        }

        public String getName() {
            return name;
        }
        public String getPhoneNumber() {
            return phonenumber;
        }
        public String getOccupation() {
            return occupation;
        }
    }


    //Called when button is clicked
    public void joinGalax(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);

        Firebase rootRef = new Firebase("https://gatchaman.firebaseio.com/gatchaman/users");
        Firebase new_user = rootRef.child("users").child("GalaxUser");
        EditText raw_name = (EditText) findViewById(R.id.name);
        String clean_name = raw_name.getText().toString();
        EditText raw_phone = (EditText) findViewById(R.id.phone);
        String clean_phone = raw_phone.getText().toString();

        Spinner spinner = (Spinner) findViewById(R.id.occupation);
        String clean_occu = spinner.getSelectedItem().toString();
        User gatchaman = new User(clean_name, clean_phone,clean_occu);
        rootRef.setValue(gatchaman);


        startActivity(intent);



    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        Spinner spinner = (Spinner) findViewById(R.id.occupation);
        //Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.occupation, android.R.layout.simple_spinner_item);
        //Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        //Creating the database to store users in Firebase

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
