package com.example.thumbnailloaderapp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Iterator;

public class ImageRetrievalThread extends Thread {

    private RemoteUtilities remoteUtilities;
    private SearchResponseVM sViewModel;
    private ImageVM imageViewModel;
    private ErrorVM errorViewModel;
    private FragmentViewModel fragmentViewModel;
    private Activity uiActivity;

    public ImageRetrievalThread(Activity uiActivity, SearchResponseVM viewModel, ImageVM imageViewModel, ErrorVM errorViewModel, FragmentViewModel fragmentViewModel) {
        remoteUtilities = RemoteUtilities.getInstance(uiActivity);
        this.sViewModel = viewModel;
        this.imageViewModel = imageViewModel;
        this.errorViewModel = errorViewModel;
        this.fragmentViewModel = fragmentViewModel;
        this.uiActivity=uiActivity;
    }
    public void run(){
        ArrayList<String> endpoint = getEndpoint(sViewModel.getResponse());
        if(endpoint==null){
            uiActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(uiActivity,"No image found",Toast.LENGTH_LONG).show();
                    errorViewModel.setErrorCode(errorViewModel.getErrorCode()+1);
                }
            });
        }
        else {
            for (int i = 0; i < endpoint.size(); i++) {
                Bitmap image = getImageFromUrl(endpoint.get(i));

                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                }

                imageViewModel.setImage(image);
                if (i == endpoint.size() - 1) {
                    imageViewModel.setFinishSearch();
                }
            }
        }
    }

    private ArrayList<String> getEndpoint(String data){
        ArrayList<String> imageUrl = new ArrayList<>();
        try {
            JSONObject jBase = new JSONObject(data);
            JSONArray jHits = jBase.getJSONArray("hits");
            Log.d("json hit", jHits.getString(0));

            if(jHits.length()>0 && jHits.length() > 15){
                for (int i = 0; i < 15; i++) {
                    JSONObject jHitsItem = jHits.getJSONObject(i);
                    imageUrl.add(jHitsItem.getString("webformatURL"));
                }
            }
            else if (jHits.length() > 0 && jHits.length() <= 15) {
                for (int i = 0; i < jHits.length(); i++) {
                    JSONObject jHitsItem = jHits.getJSONObject(i);
                    imageUrl.add(jHitsItem.getString("webformatURL"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return imageUrl;
    }

    private Bitmap getImageFromUrl(String imageUrl){
        Bitmap image = null;
        Uri.Builder url = Uri.parse(imageUrl).buildUpon();
        String urlString = url.build().toString();
        HttpURLConnection connection = remoteUtilities.openConnection(urlString);
        if(connection!=null){
            if(remoteUtilities.isConnectionOkay(connection)==true){
                image = getBitmapFromConnection(connection);
                connection.disconnect();
            }
        }
        return image;
    }

    public Bitmap getBitmapFromConnection(HttpURLConnection conn){
        Bitmap data = null;
        try {
            InputStream inputStream = conn.getInputStream();
            byte[] byteData = getByteArrayFromInputStream(inputStream);
            data = BitmapFactory.decodeByteArray(byteData,0,byteData.length);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return data;
    }

    private byte[] getByteArrayFromInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[4096];
        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        return buffer.toByteArray();
    }

}
