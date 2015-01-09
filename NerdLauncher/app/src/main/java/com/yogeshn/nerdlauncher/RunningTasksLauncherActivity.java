package com.yogeshn.nerdlauncher;

import android.support.v4.app.Fragment;

/**
 * Created by yogesh on 27/08/2014.
 */
public class RunningTasksLauncherActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new RunningTasksLauncherFragment();
    }
}
