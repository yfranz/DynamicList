package com.codinglines.examples.dynamiclist;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;

import com.codinglines.examples.dynamiclist.R;
import com.codinglines.lib.dynamiclist.AdapterItem;
import com.codinglines.lib.dynamiclist.DynamicViewAdapter;
import com.codinglines.lib.dynamiclist.ViewHolder;

public class TestDynamicHeaderViewAdapter extends DynamicViewAdapter
{
	private final TestDynamicModel _model;

	public TestDynamicHeaderViewAdapter(Context context, TestDynamicModel model)
	{
		super(context, R.layout.progress_item, R.layout.unavailable_item,
				R.layout.empty_item);

		_model = model;
		_model.setLoadCompleted(new Handler()
		{
			@Override
			public void dispatchMessage(Message msg)
			{
				setDataCompleted();
			}
		});
		submitDataRequest();
	}

	@Override
	public View onDataView(int position, AdapterItem item, View convertView,
			ViewGroup parent)
	{
		int resourceId = (Integer) item.tag;

		convertView = tryInflateView(convertView, resourceId, parent);
		ViewHolder viewHolder = (ViewHolder) convertView.getTag();
		if (viewHolder == null)
		{
			viewHolder = createViewHolderByResourceId(convertView, resourceId);
		}
		viewHolder.render(item);
		return convertView;
	}

	private ViewHolder createViewHolderByResourceId(View convertView,
			int resourceId)
	{
		ViewHolder result = null;
		switch (resourceId)
		{
		case ItemViewHolder.ResourceId:
			result = new ItemViewHolder(convertView);
			break;
		case HeaderViewHolder.ResourceId:
			result = new HeaderViewHolder(convertView);
			break;
		}
		return result;
	}

	/*
	 * This method should return true if there is more data to load. For
	 * instance it loads by 20 items, for list with 60 items it should return
	 * true until it riches (60th) last item in the list;
	 */
	@Override
	protected boolean isMoreToLoad()
	{
		return _model.getCount() < 100;
	}

	/*
	 * This method is called when data item needs to be loaded from the model to
	 * the adapter; Use addItem method to load every item from the
	 * model.*(non-Javadoc)
	 * 
	 * @see com.examples.dynamiclist.core.DynamicViewAdapter#isMoreToLoad()
	 */
	@Override
	protected void onDataLoad()
	{
		for (int i = 0; i < _model.getCount(); i++)
		{
			if (i % 10 == 0)
			{
				addItem(_model.getItem(i), HeaderViewHolder.ResourceId);
			} 			
			else
			{
				addItem(_model.getItem(i), ItemViewHolder.ResourceId);
			}
		}
	}

	@Override
	protected void onSubmitDataRequest()
	{
		_model.loadMore();
	}
}
