package com.example.mahmoudbahaa.etilaaftask.utilities;

import org.json.JSONException;

/**
 * Created by MahmoudBahaa on 31/12/2018.
 */

public interface OnEventListener<T> {

    public void onSuccess(T object) throws JSONException;
    public void onFailure(Exception e);




}
