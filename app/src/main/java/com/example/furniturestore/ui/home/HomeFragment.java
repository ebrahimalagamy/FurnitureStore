package com.example.furniturestore.ui.home;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furniturestore.Adapter.MyItemGroupAdapter;
import com.example.furniturestore.Interface.FirebaseLoadListener;
import com.example.furniturestore.Model.ItemData;
import com.example.furniturestore.Model.ItemGroup;
import com.example.furniturestore.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class HomeFragment extends Fragment implements FirebaseLoadListener
{
    View view;
    RecyclerView my_recycler_view;
    AlertDialog dialog;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference myData;
    FirebaseLoadListener firebaseLoadListener;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        slideImage();
        initFirebase();
        getFirebaseData();
        initViews();
    }

    private void initViews()
    {
        my_recycler_view=view.findViewById(R.id.my_recycler_view);
        my_recycler_view.setHasFixedSize(true);
        my_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void getFirebaseData()
    {
        dialog.show();
        myData. addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot)
            {
                List<ItemGroup> itemGroups =new ArrayList<>();
                for (DataSnapshot groupSnapShot:dataSnapshot.getChildren())
                {
                    ItemGroup itemGroup = new ItemGroup();
                    itemGroup.setHeaderTitle(groupSnapShot.child("headerTitle").getValue(true).toString());
                    GenericTypeIndicator<ArrayList<ItemData>> genericTypeIndicator =new GenericTypeIndicator<ArrayList<ItemData>>(){};
                    itemGroup.setListItem(groupSnapShot.child("listItem").getValue(genericTypeIndicator));
                    itemGroups.add(itemGroup);
                }
                firebaseLoadListener.onFirebaseLoadSuccess(itemGroups);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
                firebaseLoadListener.onFirebaseLoadFailed(databaseError.getMessage());
            }
        });
    }

    private void initFirebase()
    {
        firebaseDatabase=FirebaseDatabase.getInstance();
        myData= firebaseDatabase.getReference("MyData");
        dialog =new SpotsDialog.Builder().setContext(getContext()).build();
       firebaseLoadListener=this;
    }

    private void slideImage()
    {
        SliderView sliderView =view.findViewById(R.id.imageSlider);
        SliderViewAdapter sliderViewAdapter= new sliderAdapter(getContext());
        sliderView.setSliderAdapter(sliderViewAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3); //set scroll delay in seconds :
        sliderView.startAutoCycle();
    }

    @Override
    public void onFirebaseLoadSuccess(List<ItemGroup> itemGroupList)
    {
        MyItemGroupAdapter adapter =new MyItemGroupAdapter(getContext(),itemGroupList);
        my_recycler_view.setAdapter(adapter);
        dialog.dismiss();
    }

    @Override
    public void onFirebaseLoadFailed(String message)
    {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        dialog.dismiss();
    }

    public class sliderAdapter extends SliderViewAdapter<sliderAdapter.SliderAdapertVH>
    {
        private Context context;
        private int mCount;

        public sliderAdapter(Context context)
        {
            this.context = context;
        }

        public void setCount(int count)
        {
            this.mCount = count;
        }

        @Override
        public SliderAdapertVH onCreateViewHolder(ViewGroup parent)
        {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.imagesilder, null);
            return new SliderAdapertVH(inflate);
        }

        @Override
        public void onBindViewHolder(SliderAdapertVH viewHolder, int position)
        {
            viewHolder.itemView.setOnClickListener(view ->
            {
                // what to do
            });
            switch (position) {
                case 0:
                    viewHolder.textViewDescription.setText("our best sellers");
                    viewHolder.textViewDescription.setTextSize(16);
                    viewHolder.textViewDescription.setTextColor(Color.WHITE);
                    Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/el-corner-5b2c1.appspot.com/o/slide2.2.jpg?alt=media&token=36d88f18-d352-4fc9-a157-f8c86956cde0")
                            .into(viewHolder.imageViewBackground);
                    break;

                case 1:
                    viewHolder.textViewDescription.setText("New LivingRoom collection");
                    viewHolder.textViewDescription.setTextSize(16);
                    viewHolder.textViewDescription.setTextColor(Color.WHITE);
                    Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/el-corner-5b2c1.appspot.com/o/slide1.1.jpg?alt=media&token=9fe60c5a-1c3c-4760-a249-a07fc4a54200")
                            .into(viewHolder.imageViewBackground);
                    break;

                case 2:
                    viewHolder.textViewDescription.setText("Shopping Now");
                    viewHolder.textViewDescription.setTextSize(16);
                    viewHolder.textViewDescription.setTextColor(Color.WHITE);
                    Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/home-shopping-fc92b.appspot.com/o/shopping_online1.jpg?alt=media&token=c83b58fa-2d46-4fef-9c04-f6151c72c365")
                            .into(viewHolder.imageViewBackground);
                    break;
            }
        }

        @Override
        public int getCount()
        {
            return 3;
        }

        class SliderAdapertVH extends SliderViewAdapter.ViewHolder
        {
            View itemView;
            ImageView imageViewBackground;
            TextView textViewDescription;

            public SliderAdapertVH(View itemView)
            {
                super(itemView);
                imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
                textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
                this.itemView = itemView;

            }
        }
    }
}