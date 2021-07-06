package com.example.furniturestore.ui.FoodDetail;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.furniturestore.Common.Common;
import com.example.furniturestore.Model.FoodModel;

public class FoodDetailViewModel extends ViewModel
{

    private MutableLiveData<FoodModel> mutableLiveDataFood;

    public FoodDetailViewModel()
    {

    }

    public MutableLiveData<FoodModel> getMutableLiveDataFood()
    {
        if (mutableLiveDataFood==null)
            mutableLiveDataFood=new MutableLiveData<>();
        mutableLiveDataFood.setValue(Common.selectFood);
        return mutableLiveDataFood;
    }
}