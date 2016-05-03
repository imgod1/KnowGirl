package com.kk.imgod.knowgirl.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kk.imgod.knowgirl.R;

public class TestFragment extends Fragment {
    public static final String CONTENT = "content";

    private View parentView;
    private TextView txt_main;
    private String content;

    public static TestFragment newInstance(String content) {
        TestFragment fragment = new TestFragment();
        Bundle args = new Bundle();
        args.putString(CONTENT, content);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_test, container, false);
        initView();
        return parentView;
    }

    private void initView() {
        txt_main = (TextView) parentView.findViewById(R.id.txt_main);
        content = getArguments().getString(CONTENT);
        txt_main.setText(content);
    }

}
