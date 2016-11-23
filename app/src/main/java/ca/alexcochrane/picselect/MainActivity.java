package ca.alexcochrane.picselect;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements PictureSelectFragment.OnFragmentInteractionListener {

    // creates the main activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    // go back to the listview when back is pressed
    @Override
    public void onBackPressed() {
        recreate(); // FIXME: horrible hack
    }
}
