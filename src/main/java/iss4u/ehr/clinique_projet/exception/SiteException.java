package iss4u.ehr.clinique_projet.exception;

public class SiteException extends RuntimeException {
    public String getmessage() {
        return getMessage();
    }

    public SiteException(String r, String rky, long rky1) {
    }

    public SiteException(String message) {
        super(message);
    }
}
