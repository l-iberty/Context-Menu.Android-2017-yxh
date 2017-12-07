package com.yalantis.contextmenu.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.yalantis.contextmenu.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IndexActivity extends AppCompatActivity {

    private ListView mItemList;
    private SimpleAdapter mItemListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        initToolbar();
        initListView();

        mItemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (mItemListAdapter != null) {
                    SharedData.itemIndex = i;
                    startActivity(new Intent(IndexActivity.this, MainActivity.class));
                }
            }
        });
    }

    private void initToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mToolBarTextView = (TextView) findViewById(R.id.text_view_toolbar_title);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        mToolbar.setNavigationIcon(R.drawable.btn_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mToolBarTextView.setText("我管理的植物\t\t\t\t");
    }

    private void initListView() {
        mItemList = (ListView) findViewById(R.id.item_list);
        ArrayList<Map<String, Object>> itemList = new ArrayList<>();

        Map<String, Object> map0 = new HashMap<>();
        map0.put("imageId", R.drawable.photo0);
        map0.put("name", "紫罗兰");
        itemList.add(map0);
        SharedData.itemList.add("紫罗兰");
        SharedData.descList.add(getResources().getString(R.string.desc0));

        Map<String, Object> map1 = new HashMap<>();
        map1.put("imageId", R.drawable.photo1);
        map1.put("name", "郁金香");
        itemList.add(map1);
        SharedData.itemList.add("郁金香");
        SharedData.descList.add(getResources().getString(R.string.desc1));

        Map<String, Object> map2 = new HashMap<>();
        map2.put("imageId", R.drawable.photo2);
        map2.put("name", "胧月");
        itemList.add(map2);
        SharedData.itemList.add("胧月");
        SharedData.descList.add(getResources().getString(R.string.desc2));

        mItemListAdapter = new SimpleAdapter(this, itemList,
                R.layout.list_item,
                new String[]{"imageId", "name"},
                new int[]{R.id.image_item, R.id.name_item}
        );
        mItemList.setAdapter(mItemListAdapter);
    }
}
