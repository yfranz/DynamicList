package com.codinglines.examples.dynamiclist;

import android.view.View;
import android.widget.TextView;

import com.codinglines.examples.dynamiclist.R;
import com.codinglines.lib.dynamiclist.AdapterItem;
import com.codinglines.lib.dynamiclist.ViewHolder;

public class ItemViewHolder extends ViewHolder
{
    public final static int ResourceId = R.layout.test_item;
    private TextView textView;
    
    public ItemViewHolder(View convertView)
    {
        super(convertView, ResourceId);        
        
        textView = (TextView)view.findViewById(R.id.itemText);
    }
    
    public void render(AdapterItem item)
    {
        textView.setText(item.value.toString());
    }
}
