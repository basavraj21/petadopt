package com.project.petadopt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyPetsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    DatabaseReference mbase;
    ArrayList<Pet> list = new ArrayList<>();
    PetAdapter adapter;
    ProgressBar bar;
    MaterialCardView cardAll,cardDog,cardCat,cardFish,cardBird,cardmale,cardfemale,cardBoth;

    String sex = "both";
    String cat = "All";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pets);

        getSupportActionBar().setTitle("My Pets");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cardAll = findViewById(R.id.card_all);
        cardBird = findViewById(R.id.card_bird);
        cardCat = findViewById(R.id.card_cat);
        cardFish = findViewById(R.id.card_fish);
        cardDog = findViewById(R.id.card_dog);
        cardmale = findViewById(R.id.card_male);
        cardfemale = findViewById(R.id.card_female);
        cardBoth = findViewById(R.id.card_both);
        cardBoth.setChecked(true);
        cardAll.setChecked(true);

        cardAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardAll.setChecked(true);
                cardBird.setChecked(false);
                cardCat.setChecked(false);
                cardFish.setChecked(false);
                cardDog.setChecked(false);
                cat = "All";
                showPets(cat);
            }
        });


        cardDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardAll.setChecked(false);
                cardBird.setChecked(false);
                cardCat.setChecked(false);
                cardFish.setChecked(false);
                cardDog.setChecked(true);
                cat = "Dog";
                showPets(cat);
            }
        });
        cardFish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardAll.setChecked(false);
                cardBird.setChecked(false);
                cardCat.setChecked(false);
                cardFish.setChecked(true);
                cardDog.setChecked(false);
                cat = "Fish";
                showPets(cat);
            }
        });

        cardBird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardAll.setChecked(false);
                cardBird.setChecked(true);
                cardCat.setChecked(false);
                cardFish.setChecked(false);
                cardDog.setChecked(false);
                cat = "Bird";
                showPets(cat);
            }
        });

        cardCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardAll.setChecked(false);
                cardBird.setChecked(false);
                cardCat.setChecked(true);
                cardFish.setChecked(false);
                cardDog.setChecked(false);
                cat = "Cat";
                showPets(cat);
            }
        });

        cardmale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardmale.setChecked(true);
                cardfemale.setChecked(false);
                cardBoth.setChecked(false);

                sex = "male";
                showPets(cat);
            }
        });
        cardfemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardmale.setChecked(false);
                cardfemale.setChecked(true);
                cardBoth.setChecked(false);
                sex = "female";
                showPets(cat);
            }
        });

        cardBoth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardmale.setChecked(false);
                cardfemale.setChecked(true);
                cardBoth.setChecked(true);
                sex = "both";
                showPets(cat);
            }
        });
        bar = findViewById(R.id.progress);
        mbase
                = FirebaseDatabase.getInstance().getReference().child("pets");

        recyclerView = findViewById(R.id.recycler1);

        // To display the Recycler view linearly
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setHasFixedSize(true);


        showPets(cat);



    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // below line is to get our inflater
        MenuInflater inflater = getMenuInflater();

        // inside inflater we are inflating our menu file.
        inflater.inflate(R.menu.search_view, menu);

        // below line is to get our menu item.
        MenuItem searchItem = menu.findItem(R.id.actionSearch);

        // getting search view of our item.
        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) searchItem.getActionView();

        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(newText);
                return false;
            }
        });
        return true;
    }
    private void filter(String text) {
        // creating a new array list to filter our data.
        ArrayList<Pet> filteredlist = new ArrayList<Pet>();

        // running a for loop to compare elements.
        for (Pet item : list) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            // Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            adapter.filterList(filteredlist);
        }
    }

    private void showPets(String cat){
        bar.setVisibility(View.VISIBLE);

        //Fetching data from firebase
        mbase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot ds:snapshot.getChildren()){
                    String name = ds.child("name").getValue(String.class);
                    String breed = ds.child("breed").getValue(String.class);
                    String address = ds.child("address").getValue(String.class);
                    String category = ds.child("category").getValue(String.class);
                    String description = ds.child("description").getValue(String.class);
                    String gender = ds.child("gender").getValue(String.class);
                    String imageUrl = ds.child("imageUrl").getValue(String.class);
                    String ownerEmail = ds.child("ownerEmail").getValue(String.class);
                    String ownderName = ds.child("ownerName").getValue(String.class);
                    String ownerUID = ds.child("ownerUID").getValue(String.class);
                    String sterlized = ds.child("sterlized").getValue(String.class);
                    String id = ds.getKey();

                    if(ownerUID.equals(Common.uid)){
                        if(cat.equals("Dog") && category.equals("Dog")){

                            list.add(new Pet(address,breed,category,description,gender,imageUrl,name,ownerEmail,ownerUID,ownderName,sterlized,id));

                        }

                        else  if(cat.equals("Cat") && category.equals("Cat")){

                            list.add(new Pet(address,breed,category,description,gender,imageUrl,name,ownerEmail,ownerUID,ownderName,sterlized,id));
                        }
                        else  if(cat.equals("Fish") && category.equals("Fish")){

                            list.add(new Pet(address,breed,category,description,gender,imageUrl,name,ownerEmail,ownerUID,ownderName,sterlized,id));
                        }

                        else  if(cat.equals("Bird") && category.equals("Bird")){

                            list.add(new Pet(address,breed,category,description,gender,imageUrl,name,ownerEmail,ownerUID,ownderName,sterlized,id));
                        }

                        else if(cat.equals("All")){

                            list.add(new Pet(address,breed,category,description,gender,imageUrl,name,ownerEmail,ownerUID,ownderName,sterlized,id));
                        }

                    }



                }
                // Connecting object of required Adapter class to
                // the Adapter class itself
                adapter = new PetAdapter(list,MyPetsActivity.this,"My Pet");
                // Connecting Adapter class with the Recycler view*/
                recyclerView.setAdapter(adapter);
                bar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                bar.setVisibility(View.GONE);
                Toast.makeText(MyPetsActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

}