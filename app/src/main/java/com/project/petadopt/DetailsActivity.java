package com.project.petadopt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailsActivity extends AppCompatActivity {

    String petName,petbreed,imageUrl,location,ownername,owneremail,desc,sex,id,ownerID,strelized;

    ImageView imageView;
    TextView tvname,tvbreed,tvlocation,tvownername,tvowneremail,tvdesc,tvsex;

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        petName = getIntent().getStringExtra("petName");
        getSupportActionBar().setTitle(petName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        petbreed = getIntent().getStringExtra("petbreed");
        imageUrl = getIntent().getStringExtra("imageUrl");
        location = getIntent().getStringExtra("location");
        ownername = getIntent().getStringExtra("ownername");
        owneremail = getIntent().getStringExtra("owneremail");
        desc = getIntent().getStringExtra("desc");
        sex = getIntent().getStringExtra("sex");
        id = getIntent().getStringExtra("id");
        ownerID = getIntent().getStringExtra("ownerID");
        strelized = getIntent().getStringExtra("strelized");

        tvname = findViewById(R.id.name);
        tvbreed = findViewById(R.id.breed);
        tvlocation = findViewById(R.id.owner_address);
        tvownername = findViewById(R.id.owner_name);
        tvowneremail = findViewById(R.id.owner_email);
        tvdesc = findViewById(R.id.desc);
        tvsex = findViewById(R.id.gender);
        imageView = findViewById(R.id.imageview);
        btn = findViewById(R.id.request);

        if(owneremail.equals(Common.email)){
            btn.setVisibility(View.GONE);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this, ChatActivity.class);
                intent.putExtra("name", ownername);
                intent.putExtra("uid", ownerID);
                startActivity(intent);
            }
        });

        tvname.setText(petName);
        tvbreed.setText(petbreed);
        tvlocation.setText(location);
        tvowneremail.setText(owneremail);
        tvownername.setText(ownername);
        tvsex.setText(sex);
        tvdesc.setText(desc);

        Glide.with(DetailsActivity.this).load(imageUrl).into(imageView);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return true;
    }

}