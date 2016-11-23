package ca.alexcochrane.picselect;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PictureSelectFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class PictureSelectFragment extends Fragment {

    private static final String PREF_NAME = "PIC_PREFERENCES";
    private static final String PIC_SELECTED = "PIC_SELECTED";

    private OnFragmentInteractionListener mListener;

    public PictureSelectFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_picture_select, container, false);
        ListView listView = (ListView) v.findViewById(R.id.listView);

        final Drawable[] drawables = loadDrawables();

        // set the adapter for the list view
        listView.setAdapter (
                new PictureListAdapter (
                        getContext(),
                        R.layout.list_item,
                        drawables
                )
        );

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    /**
     * loads all drawables
     * @return array of drawables
     */
    public Drawable[] loadDrawables() {

        return new Drawable[] {
                getResources().getDrawable(R.drawable.photo1,null),
                getResources().getDrawable(R.drawable.photo2,null),
                getResources().getDrawable(R.drawable.photo3,null),
                getResources().getDrawable(R.drawable.photo4,null),
                getResources().getDrawable(R.drawable.photo5,null),
                getResources().getDrawable(R.drawable.photo6,null)
        };
    }
}
