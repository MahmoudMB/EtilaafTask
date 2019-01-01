package com.example.mahmoudbahaa.etilaaftask.utilities;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.mahmoudbahaa.etilaaftask.models.Story;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by MahmoudBahaa on 31/12/2018.
 */

public class StoryService {

    private Context context;
    private  static StoryService instance;




    private StoryService(Context context)
    {

        this.context = context;
    }



    public static synchronized StoryService getInstance(Context context)
    {
        if(instance == null)
        {
            instance = new StoryService(context);
        }

        return instance;
    }



    public void GetStories(String category,int pageSize,int page ,final OnEventListener<ArrayList<Story>> callBack) {


        String url = AppConstants.Base_Url+"&category="+category+"&pageSize="+pageSize+"&page="+page;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Response", response);

                try {

                    ArrayList<Story> stories = new ArrayList<>();
                    JSONObject initial = new JSONObject(response);

                    JSONArray storiesJsonArray = initial.getJSONArray("articles");


for (int i =0 ;i <storiesJsonArray.length();i++)
{

    JSONObject storyObject  = storiesJsonArray.getJSONObject(i);
    Story story = new Story();


    String title = storyObject.optString("title");
   // String description = storyObject.optString("description");
    String url = storyObject.optString("url");
    String urlToImage = storyObject.optString("urlToImage");
    String content = storyObject.optString("content");
    String publishedAt = storyObject.optString("publishedAt");


    story.setContent(content);
    story.setTitle(title);
    story.setUrl(url);
    story.setUrlToImage(urlToImage);

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    format.setTimeZone(TimeZone.getTimeZone("UTC"));
    try {


        Date date = format.parse(publishedAt);
        story.setPublishedAt(date);


    } catch (ParseException e) {
        e.printStackTrace();
    }


    stories.add(story);



}
                    callBack.onSuccess(stories);


                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error.Response", error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams()
            {


                Map<String,String> params = new HashMap<>();
  /*
                params.put("apiKey", "5546b67a373f489fb4aacbc93da14e57");
                params.put("category", "sports");
                params.put("country", "us");

*/
                return params;

            }};


        Log.v("stringreq",stringRequest.getUrl()+"");
        Log.v("stringreq",stringRequest.toString()+"");
        volley.getInstance(context).addToRequestQueue(stringRequest);


    }








}
