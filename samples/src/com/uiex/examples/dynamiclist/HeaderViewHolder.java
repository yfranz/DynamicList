package com.uiex.examples.dynamiclist;

import android.view.View;
import android.widget.TextView;

import com.codinglines.examples.dynamiclist.R;
import com.uiex.dynamiclist.AdapterItem;
import com.uiex.dynamiclist.ViewHolder;

public class HeaderViewHolder extends ViewHolder
{
    public final static int ResourceId = R.layout.header_item;
    private TextView headerText;
    
    public HeaderViewHolder(View convertView)
    {
        super(convertView, ResourceId);        
        
        headerText = (TextView)view.findViewById(R.id.headerText);
    }
    
    public void render(AdapterItem item)
    {
    	headerText.setText(String.format("Header: %s", item.value));
    }
}
