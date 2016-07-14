package com.example.eduardo.messenger;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;

import java.util.Map;

/**
 * Created by Eduardo on 7/14/16.
 */
public class FirebaseHelper
{
    private Firebase dataReference;
    private final static String SEPARATOR="___";
    private final static String CHATS_PATH="chats";
    private final static String USERS_PATH="users";
    private final static String CONTACTS_PATH="contacts";
    private final static String FIREBASE_URL="https://android-chat-c4109.firebaseio.com";

    public static  class SingletonHolder{
        private static final FirebaseHelper INSTANCE = new FirebaseHelper();
    }
    public static FirebaseHelper getInstance(){
        return SingletonHolder.INSTANCE;
    }
    public FirebaseHelper(){
        this.dataReference= new Firebase(FIREBASE_URL);
    }

    public Firebase getDataReference()
    {
        return dataReference;
    }

    public String getAuthUserEmail(){
        AuthData authData=dataReference.getAuth();
        String email =null;
        if (authData != null){
            Map<String,Object> providerData= authData.getProviderData();
            email= providerData.get("email").toString();
        }
        return email;
    }
}
