package com.uiex.dynamiclist;

public class AdapterItem
{
    public final ViewType viewType;
    public final Object value;
    public final Object tag;

    public AdapterItem(ViewType type)
    {
        this(type, null, null);
    }

    public AdapterItem(Object value, Object tag)
    {
        this(ViewType.Data, value, tag);
    }

    public AdapterItem(ViewType type, Object value, Object tag)
    {
        this.viewType = type;
        this.value = value;
        this.tag = tag;
    }
}
