package uk.co.swa.swapp.controller;

import android.view.View;
import android.widget.AdapterView;

/**
 * Created by oliver on 21/03/2016.
 */
public interface OnEditDeleteDialogClick {

    void onEditClicked(final AdapterView<?> parent, final View view,
                          final int position, final long appID);

    void onDeleteClicked(final AdapterView<?> parent, final View view,
                            final int position, final long appID);

}
