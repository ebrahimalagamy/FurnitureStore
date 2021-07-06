package com.example.furniturestore.ui.FoodList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.furniturestore.Common.Common;
import com.example.furniturestore.Model.FoodModel;

import java.util.List;

public class FoodListViewModel extends ViewModel
{

    private MutableLiveData<List<FoodModel>> mutableLiveDataFoodList;
    public FoodListViewModel()
    {
    }

    public MutableLiveData<List<FoodModel>> getMutableLiveDataFoodList()
    {
        if (mutableLiveDataFoodList==null)
            mutableLiveDataFoodList=new MutableLiveData<>();
        mutableLiveDataFoodList.setValue(Common.CategorySelected.getFoods());
        return mutableLiveDataFoodList;
    }
}