package com.example.eduardo.messenger;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;

import java.util.HashMap;
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

    public Firebase getUserReference(String email){
        Firebase userReference =null;
        if (email != null){
            String emailKey= email.replace(".","_");
            userReference = dataReference.getRoot().child(USERS_PATH).child(emailKey);
        }
        return userReference;
    }

    public Firebase getMyUserReferece(){
        return getUserReference(getAuthUserEmail());
    }

    public Firebase getContactsReference(String email){
        return getUserReference(email).child(CONTACTS_PATH);
    }

    public Firebase getMyContactsReferece(){
        return getContactsReference(getAuthUserEmail());
    }

    public Firebase getOneContactsReferece(String mainEmail, String childEmail){
        String childKey= childEmail.replace(".","_");
        return getUserReference(mainEmail).child(CONTACTS_PATH).child(childKey);
    }

    public Firebase getChatsReference(String receiver){
        String keySender= getAuthUserEmail().replace(".","_");
        String keyReceiver= receiver.replace(".","_");

        String keyChat= keySender + SEPARATOR + keyReceiver;
        if (keySender.compareTo(keyReceiver) > 0){
            keyChat= keyReceiver + SEPARATOR + keySender;
        }
        return  dataReference.getRoot().child(CHATS_PATH).child(keyChat);
    }

    public void changeUserConnectionState(Boolean online){
        if (getMyUserReferece() != null){
             Map<String,Object> updates = new HashMap<String,Object>();
            updates.put("online",online);
            getMyUserReferece().updateChildren(updates);
        }
    }
}
