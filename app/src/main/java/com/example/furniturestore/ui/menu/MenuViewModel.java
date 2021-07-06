package com.example.furniturestore.ui.menu;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.furniturestore.CallBack.CategoryCallBackListener;
import com.example.furniturestore.Common.Common;
import com.example.furniturestore.Model.CategoryModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MenuViewModel extends ViewModel implements CategoryCallBackListener
{
    private MutableLiveData<List<CategoryModel>> categoryListMutable;
    private MutableLiveData<String> messageError=new MutableLiveData<>();
    private CategoryCallBackListener categoryCallBackListener;

    public MenuViewModel()
    {
        categoryCallBackListener=this;

    }

    public MutableLiveData<List<CategoryModel>> getCategoryListMutable()
    {
        if (categoryListMutable==null)
        {
            categoryListMutable=new MutableLiveData<>();
            messageError=new MutableLiveData<>();
            loadCategories();
        }
        return categoryListMutable;
    }

    private void loadCategories()
    {
        final List<CategoryModel> tempList= new ArrayList<>();
        DatabaseReference categoryRef= FirebaseDatabase.getInstance().getReference(Common.CATEGORY_REF);
        categoryRef.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                for (DataSnapshot itemSnapShot:dataSnapshot.getChildren())
                {
                    CategoryModel categoryModel =itemSnapShot.getValue(CategoryModel.class);
                    categoryModel.setMenu_id(itemSnapShot.getKey());
                    tempList.add(categoryModel);
                }
                categoryCallBackListener.onCategoryLoadSuccess(tempList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
                categoryCallBackListener.onCategoryLoadFailed(databaseError.getMessage());

            }
        });
    }

    public MutableLiveData<String> getMessageError()
    {
        return messageError;
    }

    @Override
    public void onCategoryLoadSuccess(List<CategoryModel> categoryModelList)
    {
        categoryListMutable.setValue(categoryModelList);
    }
    @Override
    public void onCategoryLoadFailed(String message)
    {
        messageError.setValue(message);

    }
}