package ca.alexcochrane.picselect;

import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    public static final String PREF_NAME = "PIC_PREFERENCES";
    public static final String PIC_SELECTED = "PIC_SELECTED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView);

        // Restore preferences
        SharedPreferences preferences = getSharedPreferences(PREF_NAME, 0);
        String ps = preferences.getString(PIC_SELECTED,"000000");
        boolean[] selected = new boolean[ps.length()];

        // since shared preferences cannot hold arrays, a string is used and broken out into an array
        for (int i = 0; i < ps.length(); i++) {
            selected[i] = ps.toCharArray()[i]=='1';
        }

        Drawable[] drawables = loadDrawables();
    }

    /**
     * loads all drawables
     * @return array of drawables
     */
    public Drawable[] loadDrawables() {
        return new Drawable[] {
                getDrawable(R.drawable.photo1),
                getDrawable(R.drawable.photo2),
                getDrawable(R.drawable.photo3),
                getDrawable(R.drawable.photo4),
                getDrawable(R.drawable.photo5),
                getDrawable(R.drawable.photo6)
        };
    }
}
