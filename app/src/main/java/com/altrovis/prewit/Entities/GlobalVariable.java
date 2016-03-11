package com.altrovis.prewit.Entities;

import java.util.ArrayList;

/**
 * Created by Wisnu on 10/03/2016.
 */
public class GlobalVariable {

    public static String UrlLogin = "http://apps.dev.altrovis.com/prewit/prewitservice.asmx/Login";
    public static String UrlLogout = "http://apps.dev.altrovis.com/prewit/prewitservice.asmx/Logout";

    public static String UrlGetAllFinishedWorkItems = "http://apps.dev.altrovis.com/prewit/prewitservice.asmx/GetAllFinishedWorksItem";
    public static String UrlGetAllFinishedWorkItemsByMe = "http://apps.dev.altrovis.com/prewit/prewitservice.asmx/GetAllFinishedWorksItemByMe";
    public static String UrlGetAllFinishedWorkItemsToMe = "http://apps.dev.altrovis.com/prewit/prewitservice.asmx/GetAllFinishedWorksItemToMe";

    public static String UrlGetAllUnFinishedWorkItems = "http://apps.dev.altrovis.com/prewit/prewitservice.asmx/GetAllUnFinishedWorksItem";
    public static String UrlGetAllUnFinishedWorkItemsByMe = "http://apps.dev.altrovis.com/prewit/prewitservice.asmx/GetAllUnFinishedWorksItemByMe";
    public static String UrlGetAllUnFinishedWorkItemsToMe = "http://apps.dev.altrovis.com/prewit/prewitservice.asmx/GetAllUnFinishedWorksItemToMe";

    public static String UrlGetAllProjects = "http://apps.dev.altrovis.com/prewit/prewitservice.asmx/GetAllProjects";
    public static String UrlGetAllProjectMembers = "http://apps.dev.altrovis.com/prewit/prewitservice.asmx/GetAllProjectMembers";
    public static String UrlAddNewWorkItem = "http://apps.dev.altrovis.com/prewit/prewitservice.asmx/AddNewWorkItem";

    public static int LastID_Finished_All = -1;
    public static int LastID_Finished_ByMe = -1;
    public static int LastID_Finished_ToMe = -1;

    public static int LastID_UnFinished_All = -1;
    public static int LastID_UnFinished_ByMe = -1;
    public static int LastID_UnFinished_ToMe = -1;

    public static ArrayList<Project> listOfProjects = new ArrayList<Project>();
    public static ArrayList<ProjectMember> listOfProjectMembers = new ArrayList<ProjectMember>();

    public static ArrayList<WorkItem> listOfFinished = new ArrayList<WorkItem>();
    public static ArrayList<WorkItem> listOfFinishedByMe = new ArrayList<WorkItem>();
    public static ArrayList<WorkItem> listOfFinishedToMe = new ArrayList<WorkItem>();

    public static ArrayList<WorkItem> listOfUnfinished = new ArrayList<WorkItem>();
    public static ArrayList<WorkItem> listOfUnfinishedByMe = new ArrayList<WorkItem>();
    public static ArrayList<WorkItem> listOfUnfinishedToMe = new ArrayList<WorkItem>();

    public static boolean All_UnFinished_Retrieved = false;
    public static boolean All_UnFinishedByMe_Retrieved = false;
    public static boolean All_UnFinishedToMe_Retrieved = false;

    public static boolean All_Finished_Retrieved = false;
    public static boolean All_FinishedByMe_Retrieved = false;
    public static boolean All_FinishedToMe_Retrieved = false;

}
