package iss4u.ehr.clinique_projet.exception;

public class RoomException extends RuntimeException{

    public String getmessage() {
        return getMessage();
    }

    public RoomException(String r, String rky, long rky1) {
    }

    public RoomException(String message) {
        super(message);
    }

}
