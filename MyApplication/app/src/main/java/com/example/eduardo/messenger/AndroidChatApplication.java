package com.example.eduardo.messenger;

import android.app.Application;

import com.firebase.client.Firebase;

import butterknife.ButterKnife;

/**
 * Created by Eduardo on 7/14/16.
 */
public class AndroidChatApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        setUpFirebase();


    }

    private void setUpFirebase()
    {
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);



    }


}
