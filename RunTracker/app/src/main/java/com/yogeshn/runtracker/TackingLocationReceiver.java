package com.yogeshn.runtracker;

import android.content.Context;
import android.location.Location;

public class TackingLocationReceiver extends LocationReciever {

    @Override
    protected void onLocationReceived(Context context, Location loc) {
        RunManager.get(context).insertLocation(loc);
    }
}
