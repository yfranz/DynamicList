package com.codelines.examples.dynamiclist;

import android.view.View;
import android.widget.TextView;

import com.codelines.lib.dynamiclist.AdapterItem;
import com.codelines.lib.dynamiclist.ViewHolder;
import com.codelines.examples.dynamiclist.R;

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
