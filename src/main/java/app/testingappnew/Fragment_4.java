package app.testingappnew;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by ASUS on 20-Apr-16.
 */
public class Fragment_4 extends Fragment {

    Context context;
    Button btn;
    ListView listview;
    Adapter adapter;
    ArrayList<Bean> arrayList = new ArrayList<>();
    ArrayList<NameValuePair> parameters = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_4,container,false);
        context = getActivity();
        super.onCreate(savedInstanceState);

        listview = (ListView) rootView.findViewById(R.id.listviewdata_frag4);
        DoBackGround backGround = new DoBackGround();
        backGround.execute();

        return rootView;
    }
    class DoBackGround extends AsyncTask<Void,Void, String> {
        boolean status = true;
        ProgressDialog progressDialog;
        String response;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Please wait . . .");
        }

        @Override
        protected String doInBackground(Void... params)
        {
            /*for (int i=0; i< 100; i++){
                arrayList.add("Hello World");
            }*/

            response = post("http://api.geonames.org/citiesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&lang=de&username=demo");
            return response;
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            Log.e("Response", response);
            JSONObject jSon = null;
            try {
                jSon = new JSONObject(response);
                JSONArray jSonArray = jSon.getJSONArray("geonames");
                if (jSonArray.length() >0)
                {
                    for (int i = 0; i < jSonArray.length(); i++)
                    {
                        JSONObject object = jSonArray.getJSONObject(i);
                        Bean bean = new Bean();
                        String txtcityname = object.getString("toponymName");
                        String txtpopulation = object.getString("population");
                        String txtlang = object.getString("lng");

                        bean.setcityname(txtcityname);
                        bean.setpopulation(txtpopulation);
                        bean.setlng(txtlang);

                        arrayList.add(bean);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("Exception", e.toString());
            }
            adapter = new Adapter(context,arrayList);

            listview.setAdapter(adapter);
            progressDialog.dismiss();
        }


        private String post(String address) {
            String response = null;
            try {

                //for load uri
                URL url = new URL(address);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(getQuery(parameters));
                writer.flush();
                writer.close();
                os.close();
                conn.connect();

                int response_code = conn.getResponseCode();
                if (response_code == HttpsURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response_sb = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response_sb.append(inputLine);
                    }
                    in.close();
                    response = response_sb.toString();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("HttpConnection_Res_Exe", "" + e);
            }
            Log.e("HttpConnection_Response", "" + response);
            return response;
        }

        private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException {
            StringBuilder result = new StringBuilder();
            boolean first = true;
            for (NameValuePair pair : params)
            {
                if (first)
                    first = false;
                else
                    result.append("&");
                result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
            }
            return result.toString();
        }
    }
}
