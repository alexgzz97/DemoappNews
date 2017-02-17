package com.zeltixgames.apps.umbraltoolbox;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by jesus.gonzalez on 16/02/2017.
 */

public class CharactersFragment extends Fragment {
    EditText charedittext;
    Button btn;
    String sCharacterName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_characters, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    charedittext=(EditText) view.findViewById(R.id.characterEditText);
        btn=(Button) getActivity().findViewById(R.id.characterSearchButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sCharacterName=charedittext.getText().toString();
               System.out.println(sCharacterName);
            }
        });
    }
}
