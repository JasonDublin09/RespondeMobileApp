package com.example.responde;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DAO {
    private DatabaseReference databaseReference;
    public DAO(){
        FirebaseDatabase db =FirebaseDatabase.getInstance();
        databaseReference = db.getReference(ProfileModel.class.getSimpleName());
    }
    public Task<Void> add(ProfileModel prof){
        return databaseReference.push().setValue(prof);
    }






}
