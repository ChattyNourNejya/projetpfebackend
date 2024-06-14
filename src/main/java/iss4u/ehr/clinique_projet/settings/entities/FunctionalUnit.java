package iss4u.ehr.clinique_projet.settings.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.ToString;

import jakarta.persistence.*;
import java.util.List;


@Data
@Entity
@ToString
public class FunctionalUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long FunctionalUnit_Ky;
    private int FunctionalUnit_PrntKy;



    private String FunctionalUnit_Nm;

    @OneToMany(mappedBy = "functionalUnit", cascade = CascadeType.ALL)
    private List<RoomGroup> roomList;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "serviceZoneFunctinalUnit")

    private ServiceZone serviceZone;


    public FunctionalUnit(int iFunctionalUnit_PrntKy, String iFunctionalUnit_Nm) {
        FunctionalUnit_PrntKy = iFunctionalUnit_PrntKy;
        FunctionalUnit_Nm = iFunctionalUnit_Nm;
    }

    public FunctionalUnit() {
    }

    public long getFunctionalUnit_Ky() {
        return FunctionalUnit_Ky;
    }

    public void setFunctionalUnit_Ky(long iFunctionalUnit_Ky) {
        FunctionalUnit_Ky = iFunctionalUnit_Ky;
    }

    public int getFunctionalUnit_PrntKy() {
        return FunctionalUnit_PrntKy;
    }

    public void setFunctionalUnit_PrntKy(int iFunctionalUnit_PrntKy) {
        FunctionalUnit_PrntKy = iFunctionalUnit_PrntKy;
    }

    public String getFunctionalUnit_Nm() {
        return FunctionalUnit_Nm;
    }

    public void setFunctionalUnit_Nm(String iFunctionalUnit_Nm) {
        FunctionalUnit_Nm = iFunctionalUnit_Nm;
    }
    public ServiceZone getServiceZone() {
        return serviceZone;
    }

    public void setServiceZone(ServiceZone serviceZone) {
        this.serviceZone = serviceZone;
    }


    @Override
    public String toString() {
        return "FunctionalUnit{" +
                "FunctionalUnit_Ky=" + FunctionalUnit_Ky +
                ", FunctionalUnit_PrntKy=" + FunctionalUnit_PrntKy +
                ", FunctionalUnit_Nm='" + FunctionalUnit_Nm + '\'' +
                ", roomList=" + roomList +
                ", serviceZone=" + serviceZone +
                '}';
    }

    public List<RoomGroup> getRoomList() {
        return roomList;
    }
    public void setRoomList(List<RoomGroup> roomList) {
        this.roomList = roomList;
    }

    public List<RoomGroup> getRoomGroups() {return roomList;}
}