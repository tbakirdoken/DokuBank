package com.tubili.dokubank;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DemandDetailActivity extends AppCompatActivity {

    TextView txtDetail, txtTelephone;

    Button btnSendMessage,btnCall,btnMap;
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
        btnSendMessage = findViewById(R.id.btnSendMessage);
        btnCall = findViewById(R.id.btnCall);
        btnMap = findViewById(R.id.btnMap);

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // String latLng = "geo:0,0?z=5&q="+Common.selectedDemand.getLatitude()+","+Common.selectedDemand.getLongitude();
                String latLng = "geo:0,0?z=5&q="+Common.selectedDemand.getCity()+"+"+Common.selectedDemand.getHospitalName();
                Toast.makeText(DemandDetailActivity.this, latLng, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(latLng));
                Intent chooser = Intent.createChooser(intent, "Launch Maps");
                startActivity(chooser);

            }
        });

        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DemandDetailActivity.this, MessageActivity.class);
                intent.putExtra("userid", Common.selectedDemand.getUserId());
                startActivity(intent);
                finish();
            }
        });

        createMessageTemplate();

        txtTelephone.setText(Common.selectedDemand.getPhone());

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+txtTelephone.getText().toString()));
                //intent.setData(Uri.parse(txtTelephone.toString()));
                startActivity(intent);
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
