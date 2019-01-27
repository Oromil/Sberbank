package com.example.sberbank.remote;

import android.os.AsyncTask;
import org.simpleframework.xml.core.Persister;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

public class LoadingTask<R> extends AsyncTask<String, Void, R> {
    private String REQUEST_TYPE = "GET";
    private int TIMEOUT = 10000;

    private Class<R> type;
    private ResponseCallback<R> mCallback;
    private HttpsURLConnection connection;

    public LoadingTask(Class<R> responseClass, ResponseCallback<R> responseCallback) {
        type = responseClass;
        mCallback = responseCallback;
    }

    @Override
    protected R doInBackground(String... strings) {
        try {
            createConnection(strings[0]);
            String responseString = loadData();
            return parse(responseString);
        } catch (Exception e) {
            mCallback.onFailure(e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(R response) {
        if (response != null)
            mCallback.onSuccess(response);
    }

    private void createConnection(String urlString) throws IOException {
        URL url = new URL(urlString);
        connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod(REQUEST_TYPE);
        connection.setReadTimeout(TIMEOUT);
        connection.connect();
    }

    private String loadData() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder buf = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            buf.append(line + "\n");
        }
        reader.close();
        return buf.toString();
    }

    private R parse(String content) throws Exception {
        Reader reader = new StringReader(content);
        Persister serializer = new Persister();
        return serializer.read(type, reader, false);
    }
}
