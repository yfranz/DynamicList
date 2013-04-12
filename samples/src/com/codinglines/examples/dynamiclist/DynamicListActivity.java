package com.codinglines.examples.dynamiclist;

import com.codinglines.examples.dynamiclist.R;

import android.app.ListActivity;
import android.os.Bundle;

public class DynamicListActivity extends ListActivity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dynamilist_activity);
        
        setListAdapter(new TestDynamicViewAdapter(this, new TestDynamicModel()));
    }
}