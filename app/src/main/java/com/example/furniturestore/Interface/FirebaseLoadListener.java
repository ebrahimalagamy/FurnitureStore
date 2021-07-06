package com.example.furniturestore.Interface;

import com.example.furniturestore.Model.ItemGroup;

import java.util.List;

public interface FirebaseLoadListener
{
    void onFirebaseLoadSuccess(List<ItemGroup> itemGroupList);
    void onFirebaseLoadFailed(String message);
}
