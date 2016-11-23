package ca.alexcochrane.picselect;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

class PictureListAdapter extends ArrayAdapter<Drawable> {

    private static final String PREF_NAME = "PIC_PREFERENCES";
    private static final String PIC_SELECTED = "PIC_SELECTED";

    private Drawable[] objects;
    private boolean[] selected;

    Animation fadeIn;
    Animation fadeOut;

    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param objects  The objects to represent in the ListView.
     */
    PictureListAdapter(Context context, int resource, Drawable[] objects) {
        super(context, resource, objects);
        this.objects = objects;

        // Restore preferences
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, 0);
        String ps = preferences.getString(PIC_SELECTED,"000000");
        selected = new boolean[ps.length()];
        // since shared preferences cannot hold arrays, a string is used and broken out into an array
        for (int i = 0; i < ps.length(); i++) {
            selected[i] = ps.toCharArray()[i]=='1';
        }

        // load animations
        fadeIn  = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        fadeOut = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {

        // inflate the inflater and get the layout
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        @SuppressWarnings("all")
        View view = inflater.inflate(R.layout.list_item, parent, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);

        // set the image
        imageView.setImageDrawable(objects[position]);

        // grey-out the image if it has already been "selected"
        if (selected[position]) {
            imageView.setAlpha(0.5f);
        }
        else {
            imageView.setAlpha(1.0f);
        }

        // set the onClick Listener
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // sets the image to "selected"
                reverseSelected(position);
                Activity parent = (Activity) getContext();

                ImageView bigImage = (ImageView) parent.findViewById(R.id.bigImage);
                ListView listView = (ListView) parent.findViewById(R.id.listView);

                bigImage.setImageDrawable(objects[position]);

                if (bigImage.getVisibility() == View.GONE) {
                    bigImage.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.GONE);
                }

                // more animations
                bigImage.setAnimation(fadeIn);
                bigImage.animate();
            }
        });

        // set the onLongClickListener to unset the "selected" switch of the image
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                reset(position);
                return false;
            }
        });
        return view;
    }

    // sets an image to selected
    private void reverseSelected(int position) {
        selected[position] = true;
        savePreferences();
        this.notifyDataSetChanged();
    }

    // saves preferences
    private void savePreferences() {
        StringBuilder sb = new StringBuilder();
        for (boolean b : selected) {
            sb.append(b?"1":"0");
        }
        SharedPreferences preferences = getContext().getSharedPreferences(PREF_NAME, 0);
        preferences
                .edit()
                .putString(PIC_SELECTED, sb.toString())
                .apply();
    }

    // resets the selection data
    private void reset(int position) {
        selected[position] = false;
        savePreferences();
        this.notifyDataSetChanged();
    }
}
