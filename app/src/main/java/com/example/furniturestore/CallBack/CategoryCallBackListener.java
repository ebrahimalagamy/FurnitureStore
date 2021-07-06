package com.example.furniturestore.CallBack;

import com.example.furniturestore.Model.CategoryModel;

import java.util.List;

public interface CategoryCallBackListener
{
    void onCategoryLoadSuccess (List<CategoryModel> categoryModelList);
    void onCategoryLoadFailed(String message);
}
