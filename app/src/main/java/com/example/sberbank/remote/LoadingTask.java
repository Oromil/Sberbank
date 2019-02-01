package com.example.sberbank.remote;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import org.simpleframework.xml.core.Persister;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

public class LoadingTask<R> extends AsyncTask<String, Void, LoadingTask.Result<R>> {
    private String REQUEST_TYPE = "GET";

    private Class<R> type;
    private ResponseCallback<R> mCallback;
    private HttpsURLConnection connection;

    public LoadingTask(@NonNull Class<R> responseClass,
                       @NonNull ResponseCallback<R> responseCallback) {
        type = responseClass;
        mCallback = responseCallback;
    }

    @Override
    protected Result<R> doInBackground(String... strings) {
        Result<R> result = new Result<>();
        try {
            createConnection(strings[0]);
            String responseString = loadData();
            result.result = parse(responseString);
            result.status = Result.Status.SUCCESS;
            return result;
        } catch (Exception e) {
            result.exception = e;
            return result;
        }
    }

    @Override
    protected void onPostExecute(Result<R> result) {
        if (result.status == Result.Status.SUCCESS)
            mCallback.onSuccess(result.result);
        else mCallback.onFailure(result.exception);
    }

    private void createConnection(String urlString) throws IOException {
        URL url = new URL(urlString);
        connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod(REQUEST_TYPE);
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

    static class Result<R> {
        enum Status {SUCCESS, FAILURE}

        private R result;
        private Exception exception;
        private Status status = Status.FAILURE;

        Result() {
            result = null;
            exception = new Exception("unknown exception");
        }
    }
}
