package com.altrovis.prewit.Bussines.Unfinished;

import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.AbsListView;
import android.widget.ListView;

import com.altrovis.prewit.ActivityHome;

/**
 * Created by Wisnu on 10/03/2016.
 */
public class UnfinishedToMeEndlessScroll implements AbsListView.OnScrollListener {

    private int visibleThreshold = 2;

    private ActivityHome context;
    private ListView listViewWorkItem;
    private SwipeRefreshLayout refreshLayout;
    private UnfinishedToMeAsyncTask asyncTask;
    private UnfinishedAdapter adapter;

    public UnfinishedToMeEndlessScroll(ActivityHome context, UnfinishedAdapter adapter) {
        this.context = context;
        this.adapter = adapter;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {

        if (totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) {

            if (asyncTask == null || asyncTask.getStatus() == AsyncTask.Status.FINISHED) {
                asyncTask = new UnfinishedToMeAsyncTask(context, adapter);
                asyncTask.execute();
            }
        }
    }
}
