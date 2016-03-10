package com.altrovis.prewit.Bussines.Finished;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.AbsListView;
import android.widget.ListView;

import com.altrovis.prewit.ActivityHome;

/**
 * Created by Wisnu on 10/03/2016.
 */
public class FinishedEndlessScroll implements AbsListView.OnScrollListener {

    private int visibleThreshold = 2;

    private ActivityHome context;
    private ListView listViewWorkItem;
    private SwipeRefreshLayout refreshLayout;
    private FinishedByMeAsyncTask asyncTask;
    private FinishedAdapter adapter;

    public FinishedEndlessScroll(ActivityHome context, SwipeRefreshLayout refreshLayout,
                                     ListView listViewWorkItem, FinishedAdapter adapter) {
        this.context = context;
        this.listViewWorkItem = listViewWorkItem;
        this.refreshLayout = refreshLayout;
        this.adapter = adapter;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {

    /*    if (!GlobalVariables.ALL_BERITA_RETRIEVED && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) {
            GlobalVariables.LAST_VIEW_POSITION = firstVisibleItem;

            if (beritaAsyncTask == null || beritaAsyncTask.getStatus() == AsyncTask.Status.FINISHED) {
                beritaAsyncTask = new BeritaAsyncTaskAllNews(context, swipeRefreshLayout, listViewBerita, beritaAdapter);
                beritaAsyncTask.execute();
            }
        }*/
    }
}
