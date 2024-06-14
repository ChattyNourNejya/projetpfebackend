package iss4u.ehr.clinique_projet.settings.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import iss4u.ehr.clinique_projet.stay.entities.Stay;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.*;
import java.util.List;

@Data
@Entity
@ToString
@Setter
@Getter
public class StayRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "stay_room_ky")
    private long StayRoom_Ky;



    @Column(name = "stay_room_nm")
    private String stayRoomNm;





    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_ky", referencedColumnName = "service_ky", nullable = false)
    @JsonBackReference(value = "leServiceStayRooms")
    private LeService leService;

    public StayRoom(String StayRoom_Nm, LeService leService) {
        this.stayRoomNm = StayRoom_Nm;
        this.leService = leService;
    }

    public StayRoom() {
    }

    public long getStayRoom_Ky() {
        return StayRoom_Ky;
    }

    public void setStayRoom_Ky(long StayRoom_Ky) {
        this.StayRoom_Ky = StayRoom_Ky;
    }

    public String getStayRoom_Nm() {
        return stayRoomNm;
    }

    public void setStayRoom_Nm(String StayRoom_Nm) {
        this.stayRoomNm = StayRoom_Nm;
    }

    public LeService getLeService() {
        return leService;
    }

    public void setLeService(LeService leService) {
        this.leService = leService;
    }

    @Override
    public String toString() {
        return "StayRoom{" +
                "StayRoom_Ky=" + StayRoom_Ky +
                ", StayRoom_Nm='" + stayRoomNm + '\'' +
                ", leService=" + leService +
                '}';
    }


    public void setStay(Stay stay) {
    }
}
