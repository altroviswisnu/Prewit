package com.altrovis.prewit;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.altrovis.prewit.Bussines.AddNewWorkItem.AddNewWorkItemAsyncTask;
import com.altrovis.prewit.Bussines.AddNewWorkItem.GetAllProjectMembersAsyncTask;
import com.altrovis.prewit.Bussines.AddNewWorkItem.GetAllProjectsAsyncTask;
import com.altrovis.prewit.Bussines.Logout.LogoutAsyncTask;
import com.altrovis.prewit.Entities.GlobalVariable;
import com.altrovis.prewit.Entities.Project;
import com.altrovis.prewit.Entities.ProjectMember;

import java.util.ArrayList;

public class ActivityHome extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    ActivityHome context;

    EditText editTextDescription;
    Spinner spinnerProject;
    Spinner spinnerAssignTo;
    Button buttonTambah;

    String username = "";
    String accessToken = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        context = this;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initiatePopupWindow();
            }
        });

    }

    private void initiatePopupWindow() {
        try {
            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.form_popup);
            dialog.setTitle("Tambah Work Item");

            LayoutInflater li = LayoutInflater.from(context);
            View promptsView = li.inflate(R.layout.form_popup, null);

            SharedPreferences login = context.getSharedPreferences("login", context.MODE_PRIVATE);
            username = login.getString("username", "");
            accessToken = login.getString("accesstoken","");

            editTextDescription = (EditText) promptsView.findViewById(R.id.EditTextDescription);
            spinnerProject = (Spinner) promptsView.findViewById(R.id.SpinnerProject);
            spinnerAssignTo = (Spinner) promptsView.findViewById(R.id.SpinnerAssignedTo);
            buttonTambah = (Button) promptsView.findViewById(R.id.ButtonTambah);

            ArrayAdapter<Project> adapterProject = new ArrayAdapter<Project>(context,
                    android.R.layout.simple_spinner_item, new ArrayList<Project>());
            adapterProject.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerProject.setAdapter(adapterProject);

            new GetAllProjectsAsyncTask(context, adapterProject).execute();

            final ArrayAdapter<ProjectMember> adapterAssignTo = new ArrayAdapter<ProjectMember>(context,
                    android.R.layout.simple_spinner_item, new ArrayList<ProjectMember>());
            adapterAssignTo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerAssignTo.setAdapter(adapterAssignTo);

            spinnerProject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Project selectedProject = (Project) spinnerProject.getSelectedItem();
                    new GetAllProjectMembersAsyncTask(context, selectedProject.getID(), adapterAssignTo).execute();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            buttonTambah.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String description = editTextDescription.getText().toString();
                    Project selectedProject = (Project) spinnerProject.getSelectedItem();

                    int projectID = selectedProject.getID();
                    int assignedByID = GetAssignedByID(username);

                    ProjectMember selectedMember = (ProjectMember) spinnerAssignTo.getSelectedItem();
                    int assignedToID = selectedMember.getID();

                    new AddNewWorkItemAsyncTask(context, description, projectID, assignedByID,
                            assignedToID, dialog).execute();
                }
            });

            dialog.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private int GetAssignedByID(String username){

        for (int i = 0; i < GlobalVariable.listOfProjectMembers.size(); i++) {
            ProjectMember member = GlobalVariable.listOfProjectMembers.get(i);
            if(member.getUsername().equalsIgnoreCase(username)){
                return member.getID();
            }
        }

        return 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.actionLogout) {
            new LogoutAsyncTask(context).execute();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        }
        else
        {
            super.onBackPressed();
        }

        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {

            LinearLayout mainContainer = (LinearLayout) findViewById(R.id.LinearLayoutFragmentUnFinished);
            mainContainer.setVisibility(LinearLayout.VISIBLE);
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0){
                return new FragmentUnfinished();
            }else if(position == 1) {
                return new FragmentFinished();
            }else{
                return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Unfinished";
                case 1:
                    return "Finished";
            }
            return null;
        }
    }
}
