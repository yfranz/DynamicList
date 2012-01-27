package com.codelines.lib.dynamiclist;

public class AdapterItem
{
    public final ViewType viewType;
    public final Object value;
    public final Object tag;
    public final String section;

    public AdapterItem(ViewType type)
    {
        this(type, null, null, "");
    }

    public AdapterItem(Object value, Object tag)
    {
        this(ViewType.Data, value, tag, "");
    }

    private AdapterItem(ViewType type, Object value, Object tag, String section)
    {
        this.viewType = type;
        this.value = value;
        this.tag = tag;
        this.section = section;        
    }
}
