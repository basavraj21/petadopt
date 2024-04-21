package com.project.petadopt;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.ViewHolder>{
    ArrayList<Pet> list;
    Context context;
    String from;

    public PetAdapter(ArrayList<Pet> list, Context context,String from) {
        this.list = list;
        this.context = context;
        this.from = from;
    }

    @NonNull
    @Override
    public PetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem;
        listItem = layoutInflater.inflate(R.layout.design_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PetAdapter.ViewHolder holder, int position) {
         Pet myListData = list.get(position);
         holder.name.setText(myListData.getName());
         holder.gender.setText(myListData.getGender());
         holder.desc.setText(myListData.getDescription());
         holder.address.setText(myListData.getAddress());
         holder.breed.setText(myListData.getBreed());
        Glide.with(holder.itemView.getContext()).load(myListData.getImageUrl()).into(holder.img);

        if(from.equals("My Pet")){
            holder.delete.setVisibility(View.VISIBLE);
            holder.edit.setVisibility(View.VISIBLE);
        }

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StorageReference imageRef = FirebaseStorage.getInstance().getReferenceFromUrl(myListData.getImageUrl());



                AlertDialog dialog = new AlertDialog.Builder(context).setMessage("Are you sure? you want to delete").setTitle("Delete").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String selectedKey = myListData.getId();

                        imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                FirebaseDatabase.getInstance().getReference().child("pets").child(selectedKey).removeValue();
                                dialog.dismiss();
                                Toast.makeText(context, "Deleted", Toast.LENGTH_LONG).show();
                                notifyDataSetChanged();
                            }
                        });
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
                dialog.setCancelable(false);
                dialog.show();




            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,AddActivity.class);
                intent.putExtra("from","Edit");
                intent.putExtra("name",myListData.getName());
                intent.putExtra("breed",myListData.getBreed());
                intent.putExtra("desc",myListData.getDescription());
                intent.putExtra("category",myListData.getCategory());
                intent.putExtra("gender",myListData.getGender());
                intent.putExtra("sterlized",myListData.getSterlized());
                intent.putExtra("address",myListData.getAddress());
                intent.putExtra("imageUrl",myListData.getImageUrl());
                intent.putExtra("owner_name",myListData.getOwnerName());
                intent.putExtra("owner_email",myListData.getOwnerEmail());
                intent.putExtra("owner_uid",myListData.getOwnerUID());
                intent.putExtra("id",myListData.getId());
                context.startActivity(intent);



            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(context,DetailsActivity.class);
                intent.putExtra("petName",myListData.getName());
                intent.putExtra("petbreed",myListData.getBreed());
                intent.putExtra("desc",myListData.getDescription());
                intent.putExtra("sex",myListData.getGender());
                intent.putExtra("owneremail",myListData.getOwnerEmail());
                intent.putExtra("ownername",myListData.getOwnerName());
                intent.putExtra("location",myListData.getAddress());
                intent.putExtra("ownerID",myListData.getOwnerUID());
                intent.putExtra("id",myListData.getId());
                intent.putExtra("imageUrl",myListData.getImageUrl());
                intent.putExtra("strelized",myListData.getSterlized());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,breed,desc,gender,address;
        ImageView img;

        ImageView delete,edit;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.image);
            breed = itemView.findViewById(R.id.breed);
            name = itemView.findViewById(R.id.name);
            desc = itemView.findViewById(R.id.desc);
            gender = itemView.findViewById(R.id.gender);
            address = itemView.findViewById(R.id.address);

            delete = itemView.findViewById(R.id.delete);
            edit = itemView.findViewById(R.id.edit);
        }
    }
    // method for filtering our recyclerview items.
    public void filterList(ArrayList<Pet> filterlist) {
        // below line is to add our filtered
        // list in our course array list.
        list = filterlist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }



}
