package iss4u.ehr.clinique_projet.settings.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class RoomGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long RoomGroup_Ky;
    @Column(nullable = false)
    private int RoomGroup_PrntKy;
    private String RoomGroup_Nm;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "functionalUnit_id")
    private FunctionalUnit functionalUnit;

    @OneToMany(mappedBy = "roomGroup", cascade = CascadeType.ALL)
    private List<Room> rooms;

    public RoomGroup(int iRoomGroup_PrntKy, String iRoomGroup_Nm) {
        RoomGroup_PrntKy = iRoomGroup_PrntKy;
        RoomGroup_Nm = iRoomGroup_Nm;
    }

    public RoomGroup() {}

    public long getRoomGroup_Ky() {
        return RoomGroup_Ky;
    }

    public void setRoomGroup_Ky(long iRoomGroup_Ky) {
        RoomGroup_Ky = iRoomGroup_Ky;
    }

    public int getRoomGroup_PrntKy() {
        return RoomGroup_PrntKy;
    }

    public void setRoomGroup_PrntKy(int iRoomGroup_PrntKy) {
        RoomGroup_PrntKy = iRoomGroup_PrntKy;
    }

    public String getRoomGroup_Nm() {
        return RoomGroup_Nm;
    }

    public void setRoomGroup_Nm(String iRoomGroup_Nm) {RoomGroup_Nm = iRoomGroup_Nm;}


    @Override
    public String toString() {
        return "RoomGroup{" +
                "RoomGroup_Ky=" + RoomGroup_Ky +
                ", RoomGroup_PrntKy=" + RoomGroup_PrntKy +
                ", RoomGroup_Nm='" + RoomGroup_Nm + '\'' +
                ", functionalUnit=" + functionalUnit +
                ", rooms=" + rooms +
                '}';
    }

    public void setFunctionalUnit(FunctionalUnit functionalUnit) {this.functionalUnit = functionalUnit;}
    public List<Room> getRooms() {return rooms;}
    public void setRooms(List<Room> rooms) {this.rooms = rooms;}
}




