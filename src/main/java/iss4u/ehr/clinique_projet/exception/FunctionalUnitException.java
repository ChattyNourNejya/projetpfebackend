package iss4u.ehr.clinique_projet.exception;

public class FunctionalUnitException extends RuntimeException {

    public FunctionalUnitException(String functionalUnit, String functionalUnitNm, String functionalUnitNm1) {
    }

    public FunctionalUnitException(String functionalUnit, String functionalUnitKy, long functionalUnitKy1) {
    }

    public FunctionalUnitException(String message) {
        super(message);
    }



    public String getmessage() {
        return getMessage();
    }

}
