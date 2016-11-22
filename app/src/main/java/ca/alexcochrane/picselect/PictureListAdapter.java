package ca.alexcochrane.picselect;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

class PictureListAdapter extends ArrayAdapter<Drawable> {

    private static final String PREF_NAME = "PIC_PREFERENCES";
    private static final String PIC_SELECTED = "PIC_SELECTED";

    private Drawable[] objects;
    private boolean[] selected;

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

        // since shared preferences cannot hold arrays, a string is used and broken out into an array
        for (int i = 0; i < ps.length(); i++) {
            selected[i] = ps.toCharArray()[i]=='1';
        }
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        @SuppressWarnings("all")
        View view = inflater.inflate(R.layout.list_item, parent, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setImageDrawable(objects[position]);

        if (selected[position]) imageView.setAlpha(0.5f);
        else imageView.setAlpha(1.0f);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reverseSelected(position);
            }
        });
        return view;
    }

    private void reverseSelected(int position) {
        selected[position] = !selected[position];
        savePreferences();
        this.notifyDataSetChanged();
    }

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
}
