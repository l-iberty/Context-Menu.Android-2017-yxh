package com.yalantis.contextmenu.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.yalantis.contextmenu.R;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemLongClickListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMenuItemClickListener, OnMenuItemLongClickListener {

    private FragmentManager fragmentManager;
    private ContextMenuDialogFragment mMenuDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resetBestConditions();
        fragmentManager = getSupportFragmentManager();
        initToolbar();
        initMenuFragment();
        addFragment(new MainFragment(), true, R.id.container);

        // 测试 SDK 是否正常工作的代码
        /*AVObject testObject = new AVObject("TestObject");
        testObject.put("words", "Hello World!");
        testObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    Log.d("saved", "success!");
                }
            }
        });*/

        //Toast.makeText(this, SharedData.itemList.get(SharedData.itemIndex), Toast.LENGTH_SHORT).show();
    }

    private void initMenuFragment() {
        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize((int) getResources().getDimension(R.dimen.tool_bar_height));
        menuParams.setMenuObjects(getMenuObjects());
        menuParams.setClosableOutside(false);
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
        mMenuDialogFragment.setItemClickListener(this);
        mMenuDialogFragment.setItemLongClickListener(this);
    }

    private List<MenuObject> getMenuObjects() {
        // You can use any [resource, bitmap, drawable, color] as image:
        // item.setResource(...)
        // item.setBitmap(...)
        // item.setDrawable(...)
        // item.setColor(...)
        // You can set image ScaleType:
        // item.setScaleType(ScaleType.FIT_XY)
        // You can use any [resource, drawable, color] as background:
        // item.setBgResource(...)
        // item.setBgDrawable(...)
        // item.setBgColor(...)
        // You can use any [color] as text color:
        // item.setTextColor(...)
        // You can set any [color] as divider color:
        // item.setDividerColor(...)

        List<MenuObject> menuObjects = new ArrayList<>();

        MenuObject close = new MenuObject();
        close.setResource(R.drawable.icn_close);

        /*MenuObject send = new MenuObject("Send message");
        send.setResource(R.drawable.icn_1);

        MenuObject like = new MenuObject("Like profile");
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.icn_2);
        like.setBitmap(b);

        MenuObject addFr = new MenuObject("Add to friends");
        BitmapDrawable bd = new BitmapDrawable(getResources(),
                BitmapFactory.decodeResource(getResources(), R.drawable.icn_3));
        addFr.setDrawable(bd);

        MenuObject addFav = new MenuObject("Add to favorites");
        addFav.setResource(R.drawable.icn_4);

        MenuObject block = new MenuObject("Block user");
        block.setResource(R.drawable.icn_5);*/

        MenuObject temperature = new MenuObject("Check temperature");
        temperature.setResource(R.drawable.icn_temperature);

        MenuObject soil_humidity = new MenuObject("Check soil_humidity");
        soil_humidity.setResource(R.drawable.icn_humidity);

        MenuObject soil_temperatue = new MenuObject("Check soil_temperature");
        soil_temperatue.setResource(R.drawable.icn_soil_temperature);

        MenuObject air_humidity = new MenuObject("Check air_humidity");
        air_humidity.setResource(R.drawable.icn_humidity);

        MenuObject light = new MenuObject("Check light");
        light.setResource(R.drawable.icn_light);

        menuObjects.add(close);
        /*menuObjects.add(send);
        menuObjects.add(like);
        menuObjects.add(addFr);
        menuObjects.add(addFav);
        menuObjects.add(block);*/
        menuObjects.add(temperature);
        menuObjects.add(soil_humidity);
        menuObjects.add(soil_temperatue);
        menuObjects.add(air_humidity);
        menuObjects.add(light);
        return menuObjects;
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
        mToolBarTextView.setText("我的智慧植物管理");
    }

    protected void addFragment(Fragment fragment, boolean addToBackStack, int containerId) {
        invalidateOptionsMenu();
        String backStackName = fragment.getClass().getName();
        boolean fragmentPopped = fragmentManager.popBackStackImmediate(backStackName, 0);
        if (!fragmentPopped) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(containerId, fragment, backStackName)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            if (addToBackStack)
                transaction.addToBackStack(backStackName);
            transaction.commit();
        }
    }

    private void resetBestConditions() {
        BestConditions.updateTemperature(0.0);
        BestConditions.updateSoilHumidity(0.0);
        BestConditions.updateSoilTemperature(0.0);
        BestConditions.updateAirHumidity(0.0);
        BestConditions.updateLight(0.0);
        BestConditions.updateErrMsg("");
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.context_menu:
                if (fragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null) {
                    mMenuDialogFragment.show(fragmentManager, ContextMenuDialogFragment.TAG);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mMenuDialogFragment != null && mMenuDialogFragment.isAdded()) {
            mMenuDialogFragment.dismiss();
        } else {
            finish();
        }
    }

    @Override
    public void onMenuItemClick(View clickedView, int position) {
        //Toast.makeText(this, "Clicked on position: " + position, Toast.LENGTH_SHORT).show();
        final TextView descTextView = (TextView) findViewById(R.id.text_view_desc);

        AVQuery<AVObject> query = new AVQuery<>("Data");
        query.orderByDescending("createdAt"); // 获取最新数据
        query.whereEqualTo("name", SharedData.itemList.get(SharedData.itemIndex));
        query.limit(5); // 最多返回 5 条结果

        switch (position) {
            case 0: // close
                displayDesc(descTextView);
                break;
            case 1: // temperature
                displayData(query, descTextView, "temperature", "最近温度情况", "℃");
                break;
            case 2: // soil_humidity
                displayData(query, descTextView, "soil_humidity", "最近土壤湿度情况", "%");
                break;
            case 3: // soil_temperature
                displayData(query, descTextView, "soil_temperature", "最近土壤温度情况", "℃");
                break;
            case 4: // air_humidity
                displayData(query, descTextView, "air_humidity", "最近空气湿度情况", "%");
                break;
            case 5: // light
                displayData(query, descTextView, "light", "最近光照情况", "Lx");
                break;
        }
    }

    @Override
    public void onMenuItemLongClick(View clickedView, int position) {
        Toast.makeText(this, "Long clicked on position: " + position, Toast.LENGTH_SHORT).show();
    }


    /**
     * 显示描述信息
     *
     * @param textView 用于显示描述信息的 TextView
     */
    private void displayDesc(final TextView textView) {
        String textBestConditions = getResources().getString(R.string.text);

        final String text = SharedData.descList.get(SharedData.itemIndex) +
                "\n\n" +
                textBestConditions;

        AVQuery<AVObject> bestConQuery = new AVQuery<>("BestConditions");
        bestConQuery.whereEqualTo("name", SharedData.itemList.get(SharedData.itemIndex));
        bestConQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null) {
                    if (!list.isEmpty()) {
                        BestConditions.updateData(list.get(0));
                    } else {
                        Toast.makeText(MainActivity.this, "(: 数据为空", Toast.LENGTH_SHORT).show();
                        BestConditions.updateErrMsg("#ERROR#数据丢失");
                    }
                } else {
                    Toast.makeText(MainActivity.this, "(: 网络无连接", Toast.LENGTH_SHORT).show();
                    BestConditions.updateErrMsg("#ERROR#网络无连接");
                }
                textView.setText(BestConditions.getFormatDataString(text));
            }
        });
    }


    /**
     * 从 LeanCloud 拉取数据并显示
     *
     * @param query    AVQuery查询实例
     * @param textView 用于显示信息的 TextView
     * @param key      属性名
     * @param title    抬头
     * @param symbol   属性key的单位符号
     */
    private void displayData(AVQuery<AVObject> query, final TextView textView,
                             final String key, final String title, final String symbol) {
        final double offset = 3.5;
        final int maxExcp = 3;

        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null) {
                    if (!list.isEmpty()) {
                        int excpCount = 0; // 异常数据统计
                        StringBuilder info = new StringBuilder(title + ":\n");
                        for (AVObject data : list) {
                            double value = data.getDouble(key);
                            if (checkExcption(value, (double) BestConditions.get(key), offset)) {
                                excpCount++;
                            }
                            Date createdAt = data.getDate("createdAt");
                            String date = createdAt.toString();
                            info.append("\n" + value + symbol + ",\t\t测量时间: " +
                                    date.substring(0, date.indexOf("GMT")));
                        }
                        textView.setText(info.toString());
                        if (excpCount >= maxExcp) {
                            String msg = "(: " + title + " 有点不正常: 检测到 " + excpCount + " 组异常数据啦... T_T";
                            //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("温馨提示")
                                    .setMessage(msg)
                                    .setPositiveButton("我知道了", null)
                                    .show();
                        }

                    } else {
                        Toast.makeText(MainActivity.this, "(: 数据为空", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "(: 网络无连接", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 判断当前数据是否异常
     *
     * @param value  当前值
     * @param base   基准
     * @param offset 偏移量
     * @return 若 value 与 base 的差值超过 offset, return true,else false
     */
    private boolean checkExcption(double value, double base, double offset) {
        return Math.abs(value - base) >= offset;
    }
}
