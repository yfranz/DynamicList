package com.codinglines.examples.dynamiclist;

import com.codinglines.examples.dynamiclist.R;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;

        intent = new Intent().setClass(this, DynamicListActivity.class);
        spec = tabHost.newTabSpec("EndlessList").setIndicator("Endless List").setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, GrouppedListActivity.class);
        spec = tabHost.newTabSpec("GrouppedList").setIndicator("Groupped List").setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);
}
}
