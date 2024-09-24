package com.example.youtubemusicmod.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.youtubemusicmod.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class PlayerBottomTray extends BottomSheetDialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Log.d("BottomSheet", "Bottom sheet created");
        View v =  inflater.inflate(R.layout.player_bottom_sheet, container, false);
        return v;
    }

    @Override
    public void onStart(){
        super.onStart();

        if(getDialog() != null){
            View bottomSheet = getDialog().findViewById(R.id.bottomSheet);
            if(bottomSheet != null){
                BottomSheetBehavior<View> behavior = BottomSheetBehavior.from(bottomSheet);

                behavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                    @Override
                    public void onStateChanged(@NonNull View view, int i) {
                        if(i == BottomSheetBehavior.STATE_EXPANDED){
                            Toast.makeText(getContext(), "Bottom Sheet Expanded",Toast.LENGTH_SHORT).show();
                        } else if(i == BottomSheetBehavior.STATE_COLLAPSED){
                            Toast.makeText(getContext(), "Bottom Sheet Collapsed",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onSlide(@NonNull View view, float v) {

                    }
                });
            }
        }
    }
}
