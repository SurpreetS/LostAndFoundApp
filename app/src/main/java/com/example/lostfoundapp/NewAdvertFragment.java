
/*
            Name        :  Surpreet Singh
            Student ID  :  218663803
            Unit No.    :  SIT305

 */

package com.example.lostfoundapp;

import android.os.Bundle; // Importing the Bundle class

import androidx.fragment.app.Fragment; // Importing necessary classes for working with fragments
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater; // Importing necessary classes for handling UI elements
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import sqllitehelper.DatabaseHelper; // Importing custom classes
import sqllitehelper.UserData;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewAdvertFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewAdvertFragment extends Fragment {

    DatabaseHelper databaseHelper; // Declare a DatabaseHelper instance

    public NewAdvertFragment() {
        // Required empty public constructor
    }

    // Factory method to create a new instance of NewAdvertFragment
    public static NewAdvertFragment newInstance() {
        NewAdvertFragment fragment = new NewAdvertFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // Retrieve any arguments passed to the fragment
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_advert, container, false);

        // Initialize UI elements
        RadioButton lostRadioButton = view.findViewById(R.id.radioButtonLost);
        RadioButton foundRadioButton = view.findViewById(R.id.radioButtonFound);
        EditText nameEditText = view.findViewById(R.id.editTextName);
        EditText phoneEditText = view.findViewById(R.id.editTextPhoneNumber);
        EditText descriptionEditText = view.findViewById(R.id.editTextDescription);
        EditText dateEditText = view.findViewById(R.id.editTextDate);
        EditText locationEditText = view.findViewById(R.id.editTextLocation);
        Button saveButton = view.findViewById(R.id.button3);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve user input from EditText fields
                String name = nameEditText.getText().toString();
                String phoneNumber = phoneEditText.getText().toString();
                String description = descriptionEditText.getText().toString();
                String date = dateEditText.getText().toString();
                String location = locationEditText.getText().toString();

                // Create an instance of DatabaseHelper
                databaseHelper = new DatabaseHelper(getActivity());

                // Create a UserData object with the retrieved data
                UserData myOrderData = new UserData(name, phoneNumber, description, date, location);

                // Insert the UserData object into the database
                long result = databaseHelper.insertData(myOrderData);

                // Check if the insertion was successful
                if (result != -1) {
                    // Display a success message
                    Toast.makeText(getActivity(), "Item created successfully", Toast.LENGTH_SHORT).show();
                    // Create an instance of LostFoundFragment
                    Fragment fragment = LostFoundFragment.newInstance();
                    // Get the FragmentManager and remove the current fragment from the back stack (optional)
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.popBackStackImmediate();
                    // Start a new FragmentTransaction
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    // Replace the current fragment with the LostFoundFragment
                    transaction.replace(R.id.mainActivityLayout, fragment);
                    // Add the transaction to the back stack with a unique name
                    transaction.addToBackStack("LostFoundFragment");
                    // Commit the transaction
                    transaction.commit();
                } else {
                    // Display an error message
                    Toast.makeText(getActivity(), "Failed to create Item", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}