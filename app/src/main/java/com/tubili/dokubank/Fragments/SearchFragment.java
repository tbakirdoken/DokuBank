package com.tubili.dokubank.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tubili.dokubank.Model.Demand;
import com.tubili.dokubank.R;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    Spinner spinnerOptions,spinnerOptionsMenu,spinnerOptionsAltMenu;
    TextView txtOptionType;
    RecyclerView recyclerSearch;
    String option;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference demands;
    ArrayList<Demand> suggested;

    String [] tissueTypeArray = {"Kan","Organ","Trombosit"};
    String [] bloodTypeArray = {"ABRh+","ABRh-","ARh+","ARh-","BRh+","BRh-","0Rh+","0Rh-"};
    String [] cityArray = {"Adana", "Adıyaman", "Afyon", "Ağrı", "Amasya", "Ankara", "Antalya", "Artvin", "Aydın", "Balıkesir", "Bilecik", "Bingöl", "Bitlis", "Bolu", "urdur", "Bursa", "Çanakkale", "Çankırı", "Çorum", "Denizli", "Diyarbakır", "Edirne", "Elazığ", "Ezincan", "Erzurum", "Eskişehir", "Gaziantep", "Giresun", "Gümüşhane", "Hakkari", "Hatay", "Isparta", "Mersin", "İstanbul", "İzmir", "Kars", "Kastamonu", "Kayseri", "Kırklareli", "Kırşehir", "Kocaeli", "Konya", "Kütahya", "Malatya", "Manisa", "Kahramanmaraş", "Mardin", "Muğla", "Muş", "Nevşehir", "Niğde", "Ordu", "Rize", "Sakarya", "Samsun", "Siirt", "Sinop", "Sivas", "Tekirdağ", "Tokat", "Trabzon", "Tunceli", "Şanlıurfa", "Uşak", "Van", "Yozgat", "Zonguldak", "Aksaray", "Bayburt", "Karaman", "Kırıkkale", "Batman", "Şırnak", "Bartın", "Ardahan", "Iğdır", "Yalova", "Karabük", "Kilis", "Osmaniye", "Düzce"};
    String [] organArray = {"Böbrek", "Karaciğer", "Pankreas", "Akciğer", "İncebağırsak", "Kalp", "Kornea", "Tendon", "Kök hücre", "Kemik iliği", "Kalp kapağı", "Yüz", "Saçlı deri"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.fragment_search,container,false);
        spinnerOptions = RootView.findViewById(R.id.spinnerOptions);
        txtOptionType = RootView.findViewById(R.id.textViewOptionType);
        spinnerOptionsMenu = RootView.findViewById(R.id.spinnerOptionsMenu);
        spinnerOptionsAltMenu = RootView.findViewById(R.id.spinnerOptionsAltMenu);
        spinnerOptionsMenu.setVisibility(View.GONE);
        spinnerOptionsAltMenu.setVisibility(View.GONE);
        recyclerSearch = RootView.findViewById(R.id.recycler_search);
        suggested = new ArrayList<>();

        option = spinnerOptions.getSelectedItem().toString();

        txtOptionType.setText(option + " seçin");

        //init firebase
        firebaseDatabase = FirebaseDatabase.getInstance();
        demands = firebaseDatabase.getReference("Demand");

        if(option.equals("Doku Türü")){
            spinnerOptionsMenu.setVisibility(View.VISIBLE);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, tissueTypeArray);
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            spinnerOptionsMenu.setAdapter(adapter);

            if(spinnerOptionsMenu.getSelectedItem().toString().equals("Kan")){
                spinnerOptionsAltMenu.setVisibility(View.VISIBLE);
                ArrayAdapter<String> adapterBlood = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, bloodTypeArray);
                adapterBlood.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                spinnerOptionsAltMenu.setAdapter(adapterBlood);
            }

            else if(spinnerOptionsMenu.getSelectedItem().toString().equals("Trombosit")){
                demands.orderByChild("tissueType").equalTo("Trombosit").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot suggestions : dataSnapshot.getChildren()) {
                            Demand item = suggestions.getValue(Demand.class);
                            assert item != null;
                            suggested.add(item);
                        }
                        Toast.makeText(getContext(), suggested.toString(), Toast.LENGTH_SHORT).show();
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
            else{
                spinnerOptionsAltMenu.setVisibility(View.VISIBLE);
                ArrayAdapter<String> adapterOrgan = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, organArray);
                adapterOrgan.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                spinnerOptionsAltMenu.setAdapter(adapterOrgan);
            }
        }
        else if(option.equals("Şehir")){
            ArrayAdapter<String> adapterCity = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, cityArray);
            adapterCity.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            spinnerOptionsMenu.setAdapter(adapterCity);

        }
        else{

        }










        return RootView;

    }
}
