package com.uiex.examples.dynamiclist;

import android.os.AsyncTask;
import android.os.Handler;

public class TestDynamicModel
{
	private final int PageSize = 10;
	private int _count = 0;

	private Handler _loadCompleted;

	public void loadMore()
	{		
		(new BackgroundTask()).execute();
	}

	public int getCount()
	{
		return _count;
	}

	public String getItem(int position)
	{
		return String.format("Item %d", position);
	}

	public void setLoadCompleted(Handler loadCompleted)
	{
		_loadCompleted = loadCompleted;
	}

	private class BackgroundTask extends AsyncTask<String, Void, Integer> 
	{
		@Override
		protected Integer doInBackground(String... params)
		{
			try
			{
				//do something
				Thread.sleep(5000);
				
				_count += PageSize;
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Integer result)
		{
			if (_loadCompleted != null)
			{
				_loadCompleted.sendEmptyMessage(0);
			}
		}
	}

}
