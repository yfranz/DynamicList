package com.uiex.examples.dynamiclist;

import com.codinglines.examples.dynamiclist.R;

import android.app.ListActivity;
import android.os.Bundle;

public class GrouppedListActivity extends ListActivity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.groupedlist_activity);
        
        setListAdapter(new TestDynamicHeaderViewAdapter(this, new TestDynamicModel()));
    }
}
