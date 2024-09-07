package com.example.youtubemusicmod.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.youtubemusicmod.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class PlayerBottomSheetFragment extends BottomSheetDialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Log.d("BottomSheet", "Bottom sheet created");
        return inflater.inflate(R.layout.player_bottom_sheet, container, false);
    }
}
