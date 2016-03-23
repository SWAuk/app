package uk.co.swa.swapp.controller;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import java.util.List;

import uk.co.swa.swapp.model.SwaObject;

/**
 * Created by oliver on 20/03/2016.
 */
public class SwaObjectAdapter extends ArrayAdapter {

    private List<? extends SwaObject> swaObjects;

    public SwaObjectAdapter(Context context, int resource, List<? extends SwaObject> swaObjects) {
        super(context, resource, swaObjects);
        this.swaObjects = swaObjects;
    }

    @Override
    public long getItemId(int position) {
        return this.swaObjects.get(position).getAppID();
    }
}
