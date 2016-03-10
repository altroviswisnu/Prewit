package com.altrovis.prewit.Entities;

import java.util.Date;

/**
 * Created by Wisnu on 10/03/2016.
 */
public class WorkItem {

    private int ID;
    private String Description;
    private Date Created;
    private String AssignedBy;
    private String ProjectName;

    public WorkItem() {
    }

    public WorkItem(int ID, String description, Date created, String assignedBy, String projectName) {
        this.ID = ID;
        Description = description;
        Created = created;
        AssignedBy = assignedBy;
        ProjectName = projectName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Date getCreated() {
        return Created;
    }

    public void setCreated(Date created) {
        Created = created;
    }

    public String getAssignedBy() {
        return AssignedBy;
    }

    public void setAssignedBy(String assignedBy) {
        AssignedBy = assignedBy;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }
}
