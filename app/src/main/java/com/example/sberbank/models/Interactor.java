package com.example.sberbank.models;

import android.os.AsyncTask;
import android.util.Log;

import com.example.sberbank.Rss;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.simpleframework.xml.core.Persister;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Interactor {

    private HttpsURLConnection connection;
    private Gson gson;

    public Interactor(){

        new ProgressTask().execute("https://habrahabr.ru/rss/hubs/all/");
        gson = new GsonBuilder().create();
    }

    private class ProgressTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... path) {

            String content;
            try{
                content = getContent(path[0]);
            }
            catch (IOException ex){
                content = ex.getMessage();
            }

            return content;
        }
        @Override
        protected void onPostExecute(String content) {
            Log.d("Test", content);

            Reader reader = new StringReader(content);
            Persister serializer = new Persister();
            try{
                Rss responce = serializer.read(Rss.class, reader, false);
                Log.d("Parse", "success");
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        private String getContent(String path) throws IOException {
            BufferedReader reader=null;
            try {
                URL url=new URL(path);
                HttpsURLConnection c=(HttpsURLConnection)url.openConnection();
                c.setRequestMethod("GET");
                c.setReadTimeout(10000);
                c.connect();
                reader= new BufferedReader(new InputStreamReader(c.getInputStream()));
                StringBuilder buf=new StringBuilder();
                String line=null;
                while ((line=reader.readLine()) != null) {
                    buf.append(line + "\n");
                }
                return(buf.toString());
            }
            finally {
                if (reader != null) {
                    reader.close();
                }
            }
        }
    }
}
