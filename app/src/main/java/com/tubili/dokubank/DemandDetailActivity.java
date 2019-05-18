package com.tubili.dokubank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DemandDetailActivity extends AppCompatActivity {

    TextView txtDetail, txtTelephone;
    String messageTemplate = "$IL$, $HASTANEADI$ yatmakta olan $ISIM$ isimli hastanın acilen $KANGRUBU$ $DOKUTIPI$ ihtiyaç duymaktadır.";
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demand_detail);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        txtDetail = findViewById(R.id.txtDetail);
        txtTelephone = findViewById(R.id.txtTelephone);

        createMessageTemplate();
        txtTelephone.setText(Common.selectedDemand.getTelephone());
    }

    void createMessageTemplate(){

        messageTemplate = messageTemplate.replace("$IL$", Common.selectedDemand.getCity());
        messageTemplate = messageTemplate.replace("$HASTANEADI$", Common.selectedDemand.getHospitalName());
        messageTemplate = messageTemplate.replace("$ISIM$", Common.selectedDemand.getName());
        messageTemplate = messageTemplate.replace("$KANGRUBU$", Common.selectedDemand.getBloodGroup());
        messageTemplate = messageTemplate.replace("$DOKUTIPI$", Common.selectedDemand.getTissueType());

        txtDetail.setText(messageTemplate);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_back_button:
                finish();
                // User chose the "Settings" item, show the app settings UI...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
