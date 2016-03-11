package com.altrovis.prewit.Bussines.Unfinished;

import android.os.AsyncTask;
import android.widget.AbsListView;

import com.altrovis.prewit.ActivityHome;

/**
 * Created by Wisnu on 10/03/2016.
 */
public class UnfinishedByMeEndlessScroll implements AbsListView.OnScrollListener {

    private int visibleThreshold = 2;

    private ActivityHome context;
    private UnfinishedByMeAsyncTask asyncTask;
    private UnfinishedAdapter adapter;

    public UnfinishedByMeEndlessScroll(ActivityHome context, UnfinishedAdapter adapter) {
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
                asyncTask = new UnfinishedByMeAsyncTask(context, adapter);
                asyncTask.execute();
            }
        }
    }
}
