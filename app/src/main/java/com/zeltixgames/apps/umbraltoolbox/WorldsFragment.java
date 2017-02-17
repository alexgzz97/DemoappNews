package com.zeltixgames.apps.umbraltoolbox;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by jesus.gonzalez on 16/02/2017.
 */

public class WorldsFragment extends Fragment {
     ProgressDialog pDialog;
     List<Allworld> listaworlds;
    Worlds totalworlds;
     static String url = "https://api.tibiadata.com/v1/worlds.json";
     ListView lwv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_worlds, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    new GetWorlds().execute();
    }

    private class GetWorlds extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);
            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {

                Gson gson = new Gson();
                JSONWorld allWorlds = gson.fromJson(jsonStr, JSONWorld.class);
                totalworlds = allWorlds.getWorlds();
                listaworlds = totalworlds.getAllworlds();
                System.out.println(listaworlds);


            } else {
                Log.e(TAG, "Couldn't get json from server.");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            WorldsAdapter worldsAdapter = new WorldsAdapter(getContext(), R.layout.worlds_item, listaworlds);
            lwv = (ListView) getActivity().findViewById(R.id.worldslist);

            lwv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            });
            lwv.setAdapter(worldsAdapter);
        }
    }


    public class WorldsAdapter extends ArrayAdapter<Allworld> {

        public WorldsAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        public WorldsAdapter(Context context, int resource, List<Allworld> items) {
            super(context, resource, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = convertView;

            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                v = vi.inflate(R.layout.worlds_item, null);
            }

            Allworld p = getItem(position);

            if (p != null) {
                TextView tt1 = (TextView) v.findViewById(R.id.worldname);
                TextView tt2 = (TextView) v.findViewById(R.id.online);


                if (tt1 != null) {
                    Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/martel.ttf");

                    tt1.setTypeface(custom_font);
                    tt1.setTextSize(TypedValue.COMPLEX_UNIT_SP,28);
                    tt1.setTextColor(Color.BLACK);
                    tt1.setText(p.getName());
                    tt2.setText(p.getOnline());
                }

            }

            return v;
        }

    }
}

