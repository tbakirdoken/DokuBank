package com.tubili.dokubank;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyDemandsDetailsActivity extends AppCompatActivity {

    Button btnEdit,btnDelete;
    TextView txtDetail,txtTelephone;
    String messageTemplate = "$IL$, $HASTANEADI$ yatmakta olan $ISIM$ isimli hastanın acilen $KANGRUBU$ $DOKUTIPI$ ihtiyaç duymaktadır.";

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_demands_details);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Demand");

        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);
        txtDetail = findViewById(R.id.txtDetail);
        txtTelephone = findViewById(R.id.txtTelephone);

        txtTelephone.setText(Common.selectedDemand.getPhone());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MyDemandsDetailsActivity.this);
                builder.setTitle("Emin misiniz");
                builder.setMessage("Talebi silmek istiyor musunuz?");
                builder.setNegativeButton("İptal", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {

                        //İptal butonuna basılınca yapılacaklar.Sadece kapanması isteniyorsa boş bırakılacak

                    }
                });


                builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        databaseReference.child(Common.selectedDemand.getDemandId()).removeValue();
                        Toast.makeText(MyDemandsDetailsActivity.this, Common.selectedDemand.getUserId(), Toast.LENGTH_SHORT).show();


                    }
                });

                builder.show();
            }
        });




    }

    void createMessageTemplate(){

        messageTemplate = messageTemplate.replace("$IL$", Common.selectedDemand.getCity());
        messageTemplate = messageTemplate.replace("$HASTANEADI$", Common.selectedDemand.getHospitalName());
        messageTemplate = messageTemplate.replace("$ISIM$", Common.selectedDemand.getName());
        messageTemplate = messageTemplate.replace("$KANGRUBU$", Common.selectedDemand.getBloodGroup());
        messageTemplate = messageTemplate.replace("$DOKUTIPI$", Common.selectedDemand.getTissueType());

        txtDetail.setText(messageTemplate);
    }
}
