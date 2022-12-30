package com.scheduler.app;

import java.util.Dictionary;
import java.util.Hashtable;

public class Translation {

    public enum Language {
        ENGLISH,
        FRENCH
    }
    public enum LanguageKey {
        LOGIN_FORM_HEADER,
        USERNAME_LABEL,
        PASSWORD_LABEL,
        SIGN_IN_BUTTON
    }
    private Dictionary engDict = new Hashtable();
    private Dictionary frDict = new Hashtable();

    public Translation() {

        // set up the dictionary
        // English
        engDict.put(LanguageKey.LOGIN_FORM_HEADER,"User Login");
        engDict.put(LanguageKey.USERNAME_LABEL, "Username");
        engDict.put(LanguageKey.PASSWORD_LABEL, "Password");
        engDict.put(LanguageKey.SIGN_IN_BUTTON, "Sign In");

        //French
        frDict.put(LanguageKey.LOGIN_FORM_HEADER,"Utilisateur en ligne");
        frDict.put(LanguageKey.USERNAME_LABEL, "Nom d'utilisateur");
        frDict.put(LanguageKey.PASSWORD_LABEL, "Mot de passe");
        frDict.put(LanguageKey.SIGN_IN_BUTTON, "S'identifier");

    }

    public String getText(Language language, LanguageKey languageKey) {

        String returnVal;

        switch (language) {

            case FRENCH:
                returnVal =  (String) frDict.get(languageKey);
            case ENGLISH:
            default:
                returnVal =  (String) engDict.get(languageKey);
        }

        return returnVal;
    }

}
