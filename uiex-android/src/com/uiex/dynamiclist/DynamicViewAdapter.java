package com.uiex.dynamiclist;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class DynamicViewAdapter extends BaseAdapter
{
    protected final Context _context;

    protected final ArrayList<AdapterItem> _list;

    private final LayoutInflater _inflater;

    private final int _progressResourceId;
    private final int _unavailableResourceId;
    private final int _emptyResourceId;

    protected boolean _isInitialization = false;
    protected boolean _isRequestSubmited = false;

    public DynamicViewAdapter(Context context, int progressResourceId, int unavailableResourceId, int emptyResourceId)
    {
        _context = context;
        _inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        _list = new ArrayList<AdapterItem>();

        _progressResourceId = progressResourceId;
        _unavailableResourceId = unavailableResourceId;
        _emptyResourceId = emptyResourceId;
    }

    protected abstract View onDataView(int position, AdapterItem item, View convertView, ViewGroup parent);

    protected abstract void onDataLoad();

    protected abstract boolean isMoreToLoad();

    protected abstract void onSubmitDataRequest();

    public int getCount()
    {
        return _list.size();
    }

    public Object getItem(int position)
    {
        Object result = null;

        if (position < _list.size())
        {
            result = _list.get(position);
        }

        return result;
    }

    /* Override this method when implementing stable id */
    public long getItemId(int position)
    {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (_list.size() > position)
        {
            AdapterItem item = _list.get(position);

            if (item.viewType == ViewType.Data)
            {
                convertView = onDataView(position, item, convertView, parent);
            }
            else
            {
                int resourceid = getResourceByViewType(item.viewType);
                convertView = tryInflateView(convertView, resourceid, parent);
                tryCreateViewHolder(convertView, item, resourceid);

                if (!_isRequestSubmited && item.viewType == ViewType.Progress)
                {
                    submitDataRequest();
                }
            }
        }

        return convertView;
    }

    protected int getResourceByViewType(ViewType expectedType)
    {
        int result = 0;

        switch (expectedType)
        {
        case Progress:
            result = _progressResourceId;
            break;
        case Unavailable:
            result = _unavailableResourceId;
            break;
        case Empty:
            result = _emptyResourceId;
            break;
        }

        return result;
    }

    protected void tryCreateViewHolder(View viewSource, AdapterItem item, int resourceId)
    {
        ViewHolder viewItem = (ViewHolder) viewSource.getTag();
        if (viewItem == null)
        {
            viewItem = new ViewHolder(viewSource, resourceId);
        }
    }

    protected View tryInflateView(View viewSource, int resourceId, ViewGroup parent)
    {
        View result = null;

        if (viewSource != null && viewSource.getTag() != null)
        {
            ViewHolder viewItem = (ViewHolder) viewSource.getTag();
            // try to re-use view if it has the same type
            if (viewItem != null && viewItem.resourceId == resourceId)
            {
                result = viewSource;
            }
        }

        if (result == null)
        {
            // create new view
            result = _inflater.inflate(resourceId, parent, false);
        }

        return result;
    }

    protected void addItem(Object value, Object tag)
    {
        AdapterItem item = new AdapterItem(value, tag);
        _list.add(item);
    }

	protected void submitDataRequest()
	{
		showProgress();
		onSubmitDataRequest();
		_isRequestSubmited = true;
	}
	
    protected void setDataCompleted()
    {
        _list.clear();
        
        onDataLoad();
        if (isMoreToLoad())
        {
            showProgress();
        }
        notifyDataSetChanged();
        
        _isRequestSubmited = false;
    }

    private void removeAnyBut(ViewType type)
    {
        if (type != ViewType.Progress)
        {
            tryEndProgress();
        }

        if (type != ViewType.Empty)
        {
            tryEndEmpty();
        }

        if (type != ViewType.Unavailable)
        {
            tryEndUnavailable();
        }
    }

    private void tryShowItem(ViewType type)
    {
        removeAnyBut(type);

        if (_list.size() == 0 || _list.get(_list.size() - 1).viewType != type)
        {
            _list.add(new AdapterItem(type));
        }

        notifyDataSetChanged();
    }

    protected void showUnavailable()
    {
        tryShowItem(ViewType.Unavailable);
    }

    protected void showProgress()
    {
        tryShowItem(ViewType.Progress);
    }

    protected void showEmpty()
    {
        tryShowItem(ViewType.Empty);
    }

    private void tryRemoveItem(ViewType type)
    {
        if (_list.size() > 0 && _list.get(_list.size() - 1).viewType == type)
        {
            _list.remove(_list.size() - 1);
        }
    }

    protected void tryEndProgress()
    {
        tryRemoveItem(ViewType.Progress);
    }

    protected void tryEndUnavailable()
    {
        tryRemoveItem(ViewType.Unavailable);
    }

    protected void tryEndEmpty()
    {
        tryRemoveItem(ViewType.Empty);
    }

}
