package com.yogeshn.criminalintent;

import android.app.Fragment;

/**
 * Created by yogesh on 14/08/2014.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
