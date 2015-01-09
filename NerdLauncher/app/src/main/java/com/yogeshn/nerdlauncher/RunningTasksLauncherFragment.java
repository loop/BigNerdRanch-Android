package com.yogeshn.nerdlauncher;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by yogesh on 27/08/2014.
 */
public class RunningTasksLauncherFragment extends ListFragment {

    private static final String TAG = "RunningTasksLauncherFragment";
    List<ActivityManager.RunningTaskInfo> tasks;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent startupItent = new Intent(Intent.ACTION_MAIN);
        startupItent.addCategory(Intent.CATEGORY_LAUNCHER);

        ActivityManager actvityManager = (ActivityManager) getActivity().getSystemService(Activity.ACTIVITY_SERVICE);
        tasks = actvityManager.getRunningTasks(100);

        Log.i(TAG, "I've found " + tasks.size() + " tasks");

        for (int ii = 0; ii < tasks.size(); ii++) {
            Log.i(TAG, getAppName(tasks.get(ii).baseActivity.getPackageName()));

        }

        ArrayAdapter<ActivityManager.RunningTaskInfo> adapter = new ArrayAdapter<ActivityManager.RunningTaskInfo>(
                getActivity(), android.R.layout.activity_list_item, android.R.id.text1, tasks) {
            public View getView(int pos, View convertView, ViewGroup parent) {
                View v = super.getView(pos, convertView, parent);

                // Documentations says that activity_list_item is a LinearLayout
                LinearLayout l = (LinearLayout) v;

                ActivityManager.RunningTaskInfo ri = getItem(pos);

                ImageView iv = (ImageView) l.findViewById(android.R.id.icon);
                iv.setImageDrawable(getAppIcon(ri.baseActivity.getPackageName()));

                TextView tv = (TextView) l.findViewById(android.R.id.text1);
                tv.setText(getAppName(ri.baseActivity.getPackageName()));

                return v;
            }
        };
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ActivityManager actvityManager = (ActivityManager) getActivity().getSystemService(Activity.ACTIVITY_SERVICE);
        actvityManager.moveTaskToFront(tasks.get(position).id, ActivityManager.MOVE_TASK_WITH_HOME);
    }

    private String getAppName(String packageName) {
        final PackageManager pm = getActivity().getPackageManager();
        ApplicationInfo ai;
        try {
            ai = pm.getApplicationInfo(packageName, 0);
        } catch (final PackageManager.NameNotFoundException e) {
            ai = null;
        }
        final String applicationName = (String) (ai != null ? pm.getApplicationLabel(ai) : "(unknown)");
        return applicationName;
    }

    private Drawable getAppIcon(String packageName) {
        final PackageManager pm = getActivity().getPackageManager();
        ApplicationInfo ai;
        try {
            ai = pm.getApplicationInfo(packageName, 0);
        } catch (final PackageManager.NameNotFoundException e) {
            ai = null;
        }
        final Drawable applicationName = (Drawable) (ai != null ? pm.getApplicationIcon(ai) : "(unknown)");
        return applicationName;
    }

}
