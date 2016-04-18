package uk.co.swa.swapp.controller;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

import uk.co.swa.swapp.model.SwaObject;

/**
 * Created by oliver on 20/03/2016.
 */
public class SwaObjectAdapter extends ArrayAdapter {

    public SwaObjectAdapter(Context context, int resource, List<? extends SwaObject> swaObjects) {
        super(context, resource, swaObjects);
    }

    @Override
    public long getItemId(int position) {
        return ((SwaObject) getItem(position)).getAppID();
    }

}
