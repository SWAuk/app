package uk.co.swa.swapp.controller;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import uk.co.swa.swapp.R;

/**
 * Created by oliver on 21/03/2016.
 */
public class EditDeleteDialogOnItemLongClick implements AdapterView.OnItemLongClickListener {

    OnEditDeleteDialogClick onEditDeleteDialogClick;

    public EditDeleteDialogOnItemLongClick(OnEditDeleteDialogClick onEditDeleteDialogClick) {
        this.onEditDeleteDialogClick = onEditDeleteDialogClick;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long appID) {
        Log.d(this.getClass().getSimpleName(), String.format("%s long clicked. position: %d, " +
                "appID: %d", parent.getTag(), position, appID));

        DialogInterface.OnClickListener editDeleteOnClickListener =
                new EditDeleteDialogClick(parent, view, position, appID);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(view.getContext());
        dialogBuilder.setTitle(parent.getAdapter().getItem(position).toString());
        dialogBuilder.setItems(R.array.edit_delete, editDeleteOnClickListener);

        dialogBuilder.create().show();

        return true;
    }

    private class EditDeleteDialogClick implements DialogInterface.OnClickListener {

        AdapterView<?> parent;
        View view;
        int position;
        long appID;

        public EditDeleteDialogClick(AdapterView<?> parent, View view, int position, long appID) {
            this.parent = parent;
            this.view = view;
            this.position = position;
            this.appID = appID;
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case 0:
                    Log.d(getClass().getSimpleName(), "EditDeleteDialog clicked: Edit");

                    onEditDeleteDialogClick.onEditClicked(this.parent, this.view,
                            this.position, this.appID);
                    break;
                case 1:
                    Log.d(getClass().getSimpleName(), "EditDeleteDialog clicked: Delete");

                    onEditDeleteDialogClick.onDeleteClicked(this.parent, this.view,
                            this.position, this.appID);
                    break;
            }
        }
    }
}
