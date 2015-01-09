package com.yogeshn.criminalintent;

import android.content.Context;
import android.os.Environment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by yogesh on 20/08/2014.
 */
public class CriminalIntentJSONSerializer {

    private Context mContext;
    private String mFilename;

    public CriminalIntentJSONSerializer(Context c, String f) {
        this.mContext = c;
        this.mFilename = f;
    }

    public void saveCrimes(ArrayList<Crime> crimes) throws JSONException, IOException {
        Boolean isSDPresent = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        // Build an array in JSON
        JSONArray array = new JSONArray();
        for (Crime c: crimes) {
            array.put(c.toJSON());
        }
        //Write the file to disk
        Writer writer = null;
        try {
            if (isSDPresent) {
                File extDataDir = new File(mContext.getExternalFilesDir(null), mFilename);
                File extCrimeFile = new File(extDataDir.toString());
                FileOutputStream extFOS = new FileOutputStream(extCrimeFile);
                writer = new OutputStreamWriter(extFOS);
            } else {
                OutputStream out = mContext.openFileOutput(mFilename, Context.MODE_PRIVATE);
                writer = new OutputStreamWriter(out);
            }
            writer.write(array.toString());
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public ArrayList<Crime> loadCrimes() throws IOException, JSONException {
        Boolean isSDPresent = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        // Set variable to see if the file exists
        File extCrimeFile = new File(mContext.getExternalFilesDir(null), mFilename);
        ArrayList<Crime> crimes = new ArrayList<Crime>();
        BufferedReader reader = null;
        try {
            if (isSDPresent && extCrimeFile.exists()) {
                // Open and read the file into a StringBuilder
                FileInputStream extFileInputStream = new FileInputStream(extCrimeFile);
                reader = new BufferedReader(new InputStreamReader(extFileInputStream));
            } else {
                // Open and read the file into a StringBuilder
                InputStream in = mContext.openFileInput(mFilename);
                reader = new BufferedReader(new InputStreamReader(in));
            }
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                // line breaks are omitted and irrelevant
                jsonString.append(line);
            }
            // parse the JSON using JSONTokener
            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            // build the array of crimes from JSONObjects
            System.out.println(array.length());
            for (int i = 0; i < array.length(); i++) {
                System.out.println(array.getJSONObject(i));
                crimes.add(new Crime(array.getJSONObject(i)));
            }
        } catch (FileNotFoundException e) {
            // we will ignore this one, since it happens when we start fresh
        } finally {
            if (reader != null)
                reader.close();
        }
        return crimes;
    }
}
