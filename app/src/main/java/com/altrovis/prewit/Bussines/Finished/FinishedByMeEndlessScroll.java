package com.altrovis.prewit.Bussines.Finished;

import android.os.AsyncTask;
import android.widget.AbsListView;

import com.altrovis.prewit.ActivityHome;
import com.altrovis.prewit.Entities.GlobalVariable;

/**
 * Created by Wisnu on 10/03/2016.
 */
public class FinishedByMeEndlessScroll implements AbsListView.OnScrollListener {

    private int visibleThreshold = 2;

    private ActivityHome context;
    private FinishedByMeAsyncTask asyncTask;
    private FinishedAdapter adapter;

    public FinishedByMeEndlessScroll(ActivityHome context, FinishedAdapter adapter) {
        this.context = context;
        this.adapter = adapter;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {

        if (!GlobalVariable.All_FinishedByMe_Retrieved &&
                totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold){
            if (asyncTask == null || asyncTask.getStatus() == AsyncTask.Status.FINISHED) {
                asyncTask = new FinishedByMeAsyncTask(context, adapter);
                asyncTask.execute();
            }
        }
    }
}
