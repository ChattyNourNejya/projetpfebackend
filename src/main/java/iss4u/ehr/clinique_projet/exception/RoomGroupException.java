package iss4u.ehr.clinique_projet.exception;

    public class RoomGroupException extends RuntimeException {

        public String getmessage() {
            return getMessage();
        }

        public RoomGroupException(String r, String rky, long rky1) {
        }

        public RoomGroupException(String message) {
            super(message);
        }


    }


