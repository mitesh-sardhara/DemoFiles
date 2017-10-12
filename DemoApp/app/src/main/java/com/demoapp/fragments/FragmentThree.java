package com.demoapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demoapp.R;

import butterknife.ButterKnife;

/**
 * Created by mitesh on 12/10/17.
 */

public class FragmentThree extends Fragment {


    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three, container, false);
        mContext = getActivity();
        ButterKnife.bind(this, view);
        init(view);
        return view;

    }

    private void init(View view) {

    }

}
