package in.anees.dependencyinjectionexample.screens.common.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import in.anees.dependencyinjectionexample.R;

public class ServerErrorDialogFragment extends DialogFragment {


    public static ServerErrorDialogFragment newInstance() {
        return new ServerErrorDialogFragment();
    }

    @Override

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.server_error_dialog_title)
                .setMessage(R.string.server_error_dialog_message)
                .setPositiveButton(
                        R.string.server_error_dialog_button_caption,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dismiss();
                            }
                        }
                );

        return alertDialogBuilder.create();
    }
}
