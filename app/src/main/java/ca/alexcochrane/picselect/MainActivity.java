package ca.alexcochrane.picselect;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private boolean[] selected;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        final Drawable[] drawables = loadDrawables();

        // set the adapter for the list view
        listView.setAdapter(new PictureListAdapter(this,android.R.layout.simple_list_item_1,drawables));
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
