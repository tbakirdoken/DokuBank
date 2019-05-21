package com.tubili.dokubank.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tubili.dokubank.Common;
import com.tubili.dokubank.Model.Demand;
import com.tubili.dokubank.R;

public class AddNewFragment extends Fragment {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference demands;
    FirebaseUser firebaseUser;

    EditText etName,etSurname,etAge,etHospital;
    Spinner citySpinner,bloodSpinner,tissueSpinner;
    String username;
    Button btnAddRequest;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        username = Common.currentUser.getUsername();
        View RootView =inflater.inflate(R.layout.fragment_add_new,container,false);

        firebaseDatabase = FirebaseDatabase.getInstance();
        demands = firebaseDatabase.getReference("Demand");
        //demands = firebaseDatabase.getReference().child("Demand").child(Common.currentUser.getUsername());


        etName= RootView.findViewById(R.id.editTextName);
        etSurname =RootView.findViewById(R.id.editTextSurname);
        etAge =RootView.findViewById(R.id.editTextAge);
        citySpinner =RootView.findViewById(R.id.city_spinner);
        etHospital =RootView.findViewById(R.id.editTextHospital);
        bloodSpinner =RootView.findViewById(R.id.blood_spinner);
        tissueSpinner =RootView.findViewById(R.id.doku_spinner);


        btnAddRequest = RootView.findViewById(R.id.buttonAddRequest);

        Toast.makeText(getContext(), username, Toast.LENGTH_SHORT).show();

        btnAddRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Lütfen bekleyin...");
                progressDialog.show();

                demands.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            progressDialog.dismiss();
                            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                            assert firebaseUser != null;
                            Demand demand = new Demand(firebaseUser.getUid(),etName.getText().toString(),citySpinner.getSelectedItem().toString(),
                                    etSurname.getText().toString(),
                                    Common.currentUser.getPhone(),
                                    etHospital.getText().toString(),
                                    bloodSpinner.getSelectedItem().toString(),
                                    tissueSpinner.getSelectedItem().toString(),
                                    etAge.getText().toString());



                            //kullanıcı adı ile child oluştur.

                            //String username = Common.currentUser.getUsername();
                            demands.push().setValue(demand);
                            Toast.makeText(getContext(), "Talebiniz başarı ile oluşturuldu!", Toast.LENGTH_SHORT).show();
                            etSurname.setText("");
                            etName.setText("");
                            etHospital.setText("");
                            etAge.setText("");

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


        return RootView;


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
