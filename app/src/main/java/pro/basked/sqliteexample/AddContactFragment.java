package pro.basked.sqliteexample;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddContactFragment extends Fragment {
    private Button bnSave;
    EditText Id, Name, Email;


    public AddContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_contact, container, false);
        bnSave= view.findViewById(R.id.bn_save);

        Id = view.findViewById(R.id.txt_contact_id);
        Name = view.findViewById(R.id.txt_contact_name);
        Email = view.findViewById(R.id.txt_contact_email);

        bnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = Id.getText().toString();
                String name = Name.getText().toString();
                String email = Email.getText().toString();
                ContactDdHelper contactDdHelper = new ContactDdHelper(getActivity());
                SQLiteDatabase database = contactDdHelper.getWritableDatabase();
                try {
                    contactDdHelper.addContact(Integer.parseInt(id),name,email,database);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                contactDdHelper.close();
                Id.setText("");
                Name.setText("");
                Email.setText("");
                Toast.makeText(getActivity(),"Contact Saved Successfully...",Toast.LENGTH_SHORT).show();
                contactDdHelper.copyDatabaseFromAssets(view.getContext(), "contact_db",true);
            }
        });

        return view;
    }

}
