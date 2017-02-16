package com.definityfirst.jesusgonzalez.tibiastats;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by jesus.gonzalez on 16/02/2017.
 */

public class NewsFragment extends Fragment {

    private ProgressDialog pDialog;
    private List<News> listanews;
    private List<News> listacontent;
    private ListView lv;
    private static String url = "https://api.tibiadata.com/v1/news.json";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_news, parent, false);
        return view;
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
           new GetNews().execute();
    }

    private class GetNews extends AsyncTask<Void, Void, Void> {
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
                AllNews allNews = gson.fromJson(jsonStr, AllNews.class);
                listanews = allNews.getNews();


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
            ListAdapter eventAdapter = new ListAdapter(getContext(), R.layout.news_item, listanews);
            lv = (ListView) getActivity().findViewById(R.id.newslist);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String contentid = listanews.get(position).getApiurl();
                    new GetNewsContents().execute(contentid);

                }
            });
            lv.setAdapter(eventAdapter);
        }

        private class GetNewsContents extends AsyncTask<String, Void, Void> {
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
            protected Void doInBackground(String... params) {
                HttpHandler sh = new HttpHandler();

                // Making a request to url and getting response
                String jsonStr = sh.makeServiceCall(params[0]);
                Log.e(TAG, "Response from url: " + jsonStr);

                if (jsonStr != null) {

                    Gson gson = new Gson();
                    AllNews allNews = gson.fromJson(jsonStr, AllNews.class);
                    listacontent = allNews.getNews();

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
                AlertDialog alert = new AlertDialog.Builder(getActivity()).create();
                alert.setTitle(listacontent.get(0).getTitle());
                alert.setMessage(html2text(listacontent.get(0).getContent().toString()));
                alert.show();
            }

        }

        public class ListAdapter extends ArrayAdapter<News> {

            public ListAdapter(Context context, int textViewResourceId) {
                super(context, textViewResourceId);
            }

            public ListAdapter(Context context, int resource, List<News> items) {
                super(context, resource, items);
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = convertView;

                if (v == null) {
                    LayoutInflater vi;
                    vi = LayoutInflater.from(getContext());
                    v = vi.inflate(R.layout.news_item, null);
                }

                News p = getItem(position);

                if (p != null) {
                    TextView tt1 = (TextView) v.findViewById(R.id.name);
                    TextView tt2 = (TextView) v.findViewById(R.id.date);


                    if (tt1 != null) {
                        tt1.setText(p.getNews());
                        tt2.setText(p.getDate().getDate());
                    }

                }

                return v;
            }

        }

        public String html2text(String html) {
            return Jsoup.parse(html).text();
        }
    }

}
