package com.example.furniturestore.ui.menu;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furniturestore.Adapter.MyCategoriesAdapter;
import com.example.furniturestore.Common.Common;
import com.example.furniturestore.Common.SpacesItemDecoration;
import com.example.furniturestore.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dmax.dialog.SpotsDialog;

public class MenuFragment extends Fragment {

    private MenuViewModel menuViewModel;
    AlertDialog alertDialog;
    LayoutAnimationController layoutAnimationController;
    MyCategoriesAdapter adapter;
    RecyclerView recycler_menu;
    View root;
    // حاجه زي findviewbyId اكثر سهوله
   /* Unbinder unbinder;
    @BindView(R.id.recycler_menu)
    RecyclerView recycler_menu;*/


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        menuViewModel = ViewModelProviders.of(this).get(MenuViewModel.class);

        root = inflater.inflate(R.layout.fragment_menu, container, false);

       // unbinder= ButterKnife.bind(this,root);
        initViews();

        menuViewModel.getMessageError().observe(this, s -> {
            Toast.makeText(getContext(), ""+s, Toast.LENGTH_SHORT).show();
            alertDialog.dismiss();


        });
        menuViewModel.getCategoryListMutable().observe(this,categoryModelList ->{
            alertDialog.dismiss();
            adapter=new MyCategoriesAdapter(getContext(),categoryModelList);
            recycler_menu.setAdapter(adapter);
            recycler_menu.setLayoutAnimation(layoutAnimationController);
        });
        return root;
    }

    private void initViews()
    {
        recycler_menu=root.findViewById(R.id.recycler_menu);
        alertDialog =new SpotsDialog.Builder().setContext(getContext()).setCancelable(false).build();
        alertDialog.show();
        layoutAnimationController = AnimationUtils.loadLayoutAnimation(getContext(),R.anim.layout_item_from_left);
        GridLayoutManager layoutManager= new GridLayoutManager(getContext(),1);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        // مش فاهم
       /* layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup()
        {
            @Override
            public int getSpanSize(int position)
            {
                if (adapter != null)
                {
                   switch (adapter.getItemViewType(position))
                    {
                        case Common.DEFAULT_COLUMN_COUNT:return 1;
                        case Common.FULL_WIDTH_COLUMN:return 1;
                        default:return -1;
                    }
                }
                return -1;
            }
        });

        */
        recycler_menu.setLayoutManager(layoutManager);
        //المساحه بين كل item في categories
        recycler_menu.addItemDecoration(new SpacesItemDecoration(8));



    }
}