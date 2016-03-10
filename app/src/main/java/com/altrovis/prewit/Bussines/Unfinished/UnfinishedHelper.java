package com.altrovis.prewit.Bussines.Unfinished;

import com.altrovis.prewit.Bussines.GlobalFunction;
import com.altrovis.prewit.Entities.WorkItem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Wisnu on 10/03/2016.
 */
public class UnfinishedHelper {

    private static String TAG_ID = "ID";
    private static String TAG_DESCRIPTION = "Description";
    private static String TAG_CREATED = "Created";
    private static String TAG_ASSIGNED_BY = "AssignedBy";
    private static String TAG_PROJECT_NAME = "ProjectName";

    public static ArrayList<WorkItem> getListOfWorkItem(String url) throws Exception {

        JSONArray arrayOfWorkItem = GlobalFunction.GetJSONArray(url);
        ArrayList<WorkItem> listOfWorkItem = new ArrayList<WorkItem>();

        if (arrayOfWorkItem != null) {
            if (arrayOfWorkItem.length() > 0) {
                for (int j = 0; j < arrayOfWorkItem.length(); j++) {

                    JSONObject detailWorkItem = arrayOfWorkItem.getJSONObject(j);

                    WorkItem workItem = new WorkItem();

                    workItem.setID(detailWorkItem.getInt(TAG_ID));
                    workItem.setDescription(detailWorkItem.getString(TAG_DESCRIPTION));
                    workItem.setAssignedBy(detailWorkItem.getString(TAG_ASSIGNED_BY));
                    workItem.setProjectName(detailWorkItem.getString(TAG_PROJECT_NAME));

                    String dateString = detailWorkItem.getString(TAG_CREATED).replaceAll("\\D", "");
                    long dateTimeStamp = Long.parseLong(dateString);
                    Timestamp timestamp = new Timestamp(dateTimeStamp);
                    workItem.setCreated(new Date(timestamp.getTime()));

                    listOfWorkItem.add(workItem);
                }
            }
        }

        return listOfWorkItem;
    }
}
