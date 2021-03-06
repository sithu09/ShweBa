package com.mt.shweba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BrokerRetrieve extends AppCompatActivity {
DatabaseReference reff;
EditText towns;
Button market;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broker_retrieve);

        towns=(EditText)findViewById(R.id.town);
        market=(Button)findViewById(R.id.farmer_market);

        final ListView listView=(ListView)findViewById(R.id.listView);
        final ArrayList<String> arrayList=new ArrayList<>();
        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(BrokerRetrieve.this,android.R.layout.simple_list_item_1,arrayList);

//
        market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String townlocation= towns.getText().toString().trim();
                reff = FirebaseDatabase.getInstance().getReference().child("Broker").child(townlocation);
                reff.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override

                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        // insert  ပိုင်းမှာ ဒီနာမည်နဲ့ ဝင်အောင်ထည့်ပေးပါ။
                        final BrokerInsert insert=new BrokerInsert();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String name= snapshot.child("name").getValue().toString();
                            String price= snapshot.child("price").getValue().toString();
                            String goods=snapshot.child("goods").getValue().toString();
                            String date=snapshot.child("date").getValue().toString();
                            String location=snapshot.child("location").getValue().toString();
                            String phoneno=snapshot.child("phoneno").getValue().toString();
                            String  all= "\n\t\t\tကုန်ပစ္စည်း : " +"\t" + goods + "\n"+ "\t\t\tစျေးနှုန်း : " +"\t" + price + "\n" + "\t\t\tရက်စွဲ : " + "\t" + date+ "\n" + "\t\t\tဆိုင်နာမည် : " + "\t" + name + "\n" + "\t\t\tတည်နေရာ : "+"\t" + location+"\n" + "\t\t\tဖုန်းနံပါတ် : "+"\t" +  phoneno+"\n";
//ကုန်ပစ္စည်း
//စျေးနှုန်း
//ရက်စွဲ
//ဆိုင်နာမည်
//တည်နေရာ
//ဖုန်းနံပါတ်
//                    List<String> namesList = Arrays.asList( name, price, goods,date);
//                    arrayList.add("Name: " + name);
//                    arrayList.add("Goods: " + goods);
//                    arrayList.add("Price: " + price);
                            arrayList.add(all);
//                    arrayList.addAll(namesList);
                            listView.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
