package com.yalantis.contextmenu.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.yalantis.contextmenu.R;

import java.util.List;

public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ImageView mMainImage = (ImageView) rootView.findViewById(R.id.main_image);
        switch (SharedData.itemIndex) {
            case 0:
                mMainImage.setImageResource(R.drawable.photo0);
                break;
            case 1:
                mMainImage.setImageResource(R.drawable.photo1);
                break;
            case 2:
                mMainImage.setImageResource(R.drawable.photo2);
                break;
        }

        initToolbar2(rootView);

        final TextView mTitleTextView = (TextView) rootView.findViewById(R.id.text_title_name);
        final TextView mDescTextView = (TextView) rootView.findViewById(R.id.text_view_desc);

        String title = SharedData.itemList.get(SharedData.itemIndex);
        mTitleTextView.setText(title);

        String textBestConditions = getResources().getString(R.string.text);
        final String text = SharedData.descList.get(SharedData.itemIndex) +
                "\n\n" +
                textBestConditions;

        AVQuery<AVObject> query = new AVQuery<>("BestConditions");
        query.whereEqualTo("name", title);
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null) {
                    if (!list.isEmpty()) {
                        BestConditions.updateData(list.get(0));
                    } else {
                        BestConditions.updateErrMsg("#ERROR#数据丢失");
                    }
                } else {
                    BestConditions.updateErrMsg("#ERROR#网络无连接");
                }
                mDescTextView.setText(BestConditions.getFormatDataString(text));
            }
        });
        return rootView;
    }

    private void initToolbar2(View rootView) {
        TextView textView = (TextView) rootView.findViewById(R.id.text_view_toolbar_title2);
        textView.setText("植物信息预览\t\t\t\t");
    }
}
