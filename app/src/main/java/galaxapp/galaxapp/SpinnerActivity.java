package galaxapp.galaxapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        parent.getItemAtPosition(pos);
        Toast.makeText(parent.getContext(), "On Item Select : \n" +
                        parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();

    }

    public void onNothingSelected(AdapterView<?> parent) {
        //TODO Auto-generated method stub

    }

}
