package iss4u.ehr.clinique_projet.settings.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Room {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long Room_Ky;
        @Column(nullable = false)
        private int Room_PrntKy;
        private String Room_Nm;
        @JsonIgnore
        @ManyToOne
        @JoinColumn(name = "iRoomGroup_Ky", referencedColumnName = "RoomGroup_Ky")
        private RoomGroup roomGroup;

    public Room( int iRoom_PrntKy, String iRoom_Nm) {
        Room_PrntKy = iRoom_PrntKy;
        Room_Nm = iRoom_Nm;
    }

    public Room() {
    }

    public long getRoom_Ky() {
        return Room_Ky;
    }

    public void setRoom_Ky(long  iRoom_Ky) {
        Room_Ky = iRoom_Ky;
    }

    public int getRoom_PrntKy() {
        return Room_PrntKy;
    }

    public void setRoom_PrntKy(int iRoom_PrntKy) {
        Room_PrntKy = iRoom_PrntKy;
    }

    public String getRoom_Nm() {
        return Room_Nm;
    }

    public void setRoom_Nm(String iRoom_Nm) {
        Room_Nm = iRoom_Nm;
    }

    @Override
    public String toString() {
        return "Room{" +
                "Room_Ky=" + Room_Ky +
                ", Room_PrntKy=" + Room_PrntKy +
                ", Room_Nm='" + Room_Nm + '\'' +
                ", roomGroup=" + roomGroup +
                '}';
    }

    public void setRoomGroup(RoomGroup roomGroup) {this.roomGroup = roomGroup;}

    public RoomGroup getRoomGroup() {return roomGroup;}
}
