package com.altrovis.prewit.Bussines.Finished;

import android.os.AsyncTask;
import android.widget.AbsListView;

import com.altrovis.prewit.ActivityHome;
import com.altrovis.prewit.Entities.GlobalVariable;

/**
 * Created by Wisnu on 10/03/2016.
 */
public class FinishedToMeEndlessScroll implements AbsListView.OnScrollListener {

    private int visibleThreshold = 2;

    private ActivityHome context;
    private FinishedToMeAsyncTask asyncTask;
    private FinishedAdapter adapter;

    public FinishedToMeEndlessScroll(ActivityHome context, FinishedAdapter adapter) {
        this.context = context;
        this.adapter = adapter;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {

        if (!GlobalVariable.All_UnFinishedToMe_Retrieved &&
                totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold){
            if (asyncTask == null || asyncTask.getStatus() == AsyncTask.Status.FINISHED) {
                asyncTask = new FinishedToMeAsyncTask(context, adapter);
                asyncTask.execute();
            }
        }
    }
}
