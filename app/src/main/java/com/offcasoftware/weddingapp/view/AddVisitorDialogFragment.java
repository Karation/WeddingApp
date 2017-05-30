package com.offcasoftware.weddingapp.view;

import com.offcasoftware.weddingapp.R;
import com.offcasoftware.weddingapp.model.Visitor;
import com.offcasoftware.weddingapp.repository.PeopleRepository;
import com.offcasoftware.weddingapp.repository.PeopleRepositoryImpl;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddVisitorDialogFragment extends DialogFragment {

    public interface AddVisitorDialogInterface {

        void onDissmisDialog();
    }

    private static final String BUNDLE_TEST
            = AddVisitorDialogFragment.class.getSimpleName() + "Test";
    private static final String BUNDLE_VISITOR_ID
            = AddVisitorDialogFragment.class.getSimpleName() + "VisitorId";

    @BindView(R.id.name_edit)
    EditText mName;
    @BindView(R.id.surname_edit)
    EditText mSurname;
    @BindView(R.id.additional_person)
    TextView mAdditionalPerson;
    @BindView(R.id.seekbar)
    SeekBar mSeekBar;

    private String mLabel;
    private Visitor mVisitor;
    private final PeopleRepository mPeopleRepository = PeopleRepositoryImpl.getInstance();
    private AddVisitorDialogInterface mAddVisitorDialogInterface;

    public static AddVisitorDialogFragment newInstance(String test) {
        AddVisitorDialogFragment dialog = new AddVisitorDialogFragment();

        Bundle arguments = new Bundle();
        arguments.putString(BUNDLE_TEST, test);
        dialog.setArguments(arguments);

        return dialog;
    }

    public static AddVisitorDialogFragment newInstance(int visitorId) {
        AddVisitorDialogFragment dialog = new AddVisitorDialogFragment();

        Bundle arguments = new Bundle();
        arguments.putInt(BUNDLE_VISITOR_ID, visitorId);
        dialog.setArguments(arguments);

        return dialog;
    }

    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);

        if (activity instanceof AddVisitorDialogInterface) {
            mAddVisitorDialogInterface = (AddVisitorDialogInterface) activity;
        } else {
            throw new ClassCastException("Activity must implement AddVisitorDialogInterface");
        }
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);

        if (context instanceof AddVisitorDialogInterface) {
            mAddVisitorDialogInterface = (AddVisitorDialogInterface) context;
        } else {
            throw new ClassCastException("Activity must implement AddVisitorDialogInterface");
        }
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle.containsKey(BUNDLE_TEST)) {
            mLabel = bundle.getString(BUNDLE_TEST);
        }
        if (bundle.containsKey(BUNDLE_VISITOR_ID)) {
            int visitorId = bundle.getInt(BUNDLE_VISITOR_ID);
            mVisitor = mPeopleRepository.getVisitor(visitorId);
        }
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_visitor, container, false);

        ButterKnife.bind(this, view);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(final SeekBar seekBar, final int progress, final boolean fromUser) {
                mAdditionalPerson.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(final SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(final SeekBar seekBar) {

            }
        });

        if (mVisitor != null) {
            displayVisitor();
        }

        return view;
    }

    @Override
    public void onResume() {
        final ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = LinearLayout.LayoutParams.MATCH_PARENT;
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        super.onResume();
    }

    @Override
    public void onDismiss(final DialogInterface dialog) {
        super.onDismiss(dialog);
        mAddVisitorDialogInterface.onDissmisDialog();
    }

    @OnClick(R.id.save)
    void onSaveClicked(View view) {
        String name = mName.getText().toString();
        String surname = mSurname.getText().toString();
        int additional = mSeekBar.getProgress();

        if (mVisitor == null) {
            Visitor visitor = new Visitor(12, name, surname, additional, Visitor.VisitorStatus.NO_RESPONSE);
            mPeopleRepository.saveVisitor(visitor);
        } else {
            mVisitor.setName(name);
            mVisitor.setSurname(surname);
            mVisitor.setAdditionalPerson(additional);
            mPeopleRepository.updateVisitor(mVisitor.getId(), mVisitor);
        }

        dismiss();
    }

    @OnClick(R.id.cancel)
    void onCancelClicked(View view) {
        dismiss();

    }

    private void displayVisitor() {
        mName.setText(mVisitor.getName());
        mSurname.setText(mVisitor.getSurname());
        mSeekBar.setProgress(mVisitor.getAdditionalPerson());
    }
}
