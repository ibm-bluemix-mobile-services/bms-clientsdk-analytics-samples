package com.acme.bank.service;

/**
 * Created by norton on 10/24/17.
 */

public class AcmeBankManager {

    public String applicationUser;

    private static AcmeBankManager thisInstance=null;

    private AcmeBankManager() {
    }

    public static AcmeBankManager getInstance(){
        if(thisInstance==null){
            thisInstance= new AcmeBankManager();
        }
        return thisInstance;
    }

    public String getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(String applicationUser) {
        this.applicationUser = applicationUser;
    }
}
