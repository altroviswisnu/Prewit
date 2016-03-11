package com.altrovis.prewit.Bussines.Finished;

import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.AbsListView;
import android.widget.ListView;

import com.altrovis.prewit.ActivityHome;
import com.altrovis.prewit.Entities.GlobalVariable;

/**
 * Created by Wisnu on 10/03/2016.
 */
public class FinishedEndlessScroll implements AbsListView.OnScrollListener {

    private int visibleThreshold = 2;

    private ActivityHome context;
    private ListView listViewWorkItem;
    private SwipeRefreshLayout refreshLayout;
    private FinishedAsyncTask asyncTask;
    private FinishedAdapter adapter;

    public FinishedEndlessScroll(ActivityHome context, FinishedAdapter adapter) {
        this.context = context;
        this.adapter = adapter;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {

        if (!GlobalVariable.All_Finished_Retrieved &&
                totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold){
            if (asyncTask == null || asyncTask.getStatus() == AsyncTask.Status.FINISHED) {
                asyncTask = new FinishedAsyncTask(context, adapter);
                asyncTask.execute();
            }
        }

    }
}
