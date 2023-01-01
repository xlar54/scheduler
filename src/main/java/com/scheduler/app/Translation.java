package com.scheduler.app;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * translation class encapsulates language switching
 * based on user system language
 */
public class Translation {

    public enum Language {
        ENGLISH,
        FRENCH
    }
    public enum LanguageKey {
        LOGIN_FORM_HEADER,
        USERNAME_LABEL,
        PASSWORD_LABEL,
        SIGN_IN_BUTTON,
        LOGIN_SUCCESSFUL,
        LOGIN_TRY_AGAIN
    }
    private Dictionary engDict = new Hashtable();
    private Dictionary frDict = new Hashtable();

    private static Translation single_instance = null;

    /**
     * translation should only be a single instance
     * in this application
     * @return
     */
    public static Translation getInstance()
    {
        if (single_instance == null)
            single_instance = new Translation();

        single_instance.initialize();

        return single_instance;
    }

    /**
     * initialize uses the javaFX api to create the dictionary objects
     * that will be used to switch between english and french
     */
    private void initialize() {

        // set up the dictionary
        // English
        engDict.put(LanguageKey.LOGIN_FORM_HEADER,"User Login");
        engDict.put(LanguageKey.USERNAME_LABEL, "Username");
        engDict.put(LanguageKey.PASSWORD_LABEL, "Password");
        engDict.put(LanguageKey.SIGN_IN_BUTTON, "Sign In");
        engDict.put(LanguageKey.LOGIN_SUCCESSFUL, "Login Successful");
        engDict.put(LanguageKey.LOGIN_TRY_AGAIN, "Username or password not found. Try again.");

        //French
        frDict.put(LanguageKey.LOGIN_FORM_HEADER,"Utilisateur en ligne");
        frDict.put(LanguageKey.USERNAME_LABEL, "Nom d'utilisateur");
        frDict.put(LanguageKey.PASSWORD_LABEL, "Mot de passe");
        frDict.put(LanguageKey.SIGN_IN_BUTTON, "S'identifier");
        frDict.put(LanguageKey.LOGIN_SUCCESSFUL, "Connexion réussie");
        frDict.put(LanguageKey.LOGIN_TRY_AGAIN, "Nom d'utilisateur ou mot de passe introuvable. Réessayer.");

    }

    /**
     * getText gets the language text to be used for each element on the sign in form
     * @param language
     * @param languageKey
     * @return
     */
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
