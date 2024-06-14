package iss4u.ehr.clinique_projet.settings.entities;

import com.fasterxml.jackson.annotation.*;
import iss4u.ehr.clinique_projet.stay.entities.Stay;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class LeService {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int service_ky;

    @Column(name = "serviceName")
    private String service_Nm;

    @JsonBackReference(value = "Siteservice")
    @ManyToOne(fetch = FetchType.LAZY)
    private Site site;

    @JsonManagedReference(value = "serviceServiceZone")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Leservice", fetch = FetchType.LAZY)
    @JsonProperty("servicezone")
    @JsonIgnore
    @JsonIgnoreProperties(value = "Leservice")
    private List<ServiceZone> servicezone;

    @JsonIgnore
    @OneToMany(mappedBy = "leService", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = "leService")
    @JsonManagedReference(value = "serviceStaff")
    private List<Staff> staff ;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stay_id")
    @JsonBackReference(value = "Stayservice")
    @JsonIgnoreProperties("stayPertinentService")
    private Stay stay;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "leService", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "leServiceStayRooms")

    private List<StayRoom> stayRooms = new ArrayList<>();


    public List<StayRoom> getStayRooms() {
        return stayRooms;
    }


    public LeService(int service_ky, String service_Nm, Site site, List<ServiceZone> servicezone, List<Staff> staff, List<StayRoom> stayRooms) {
        this.service_ky = service_ky;
        this.service_Nm = service_Nm;
        this.site = site;
        this.servicezone = servicezone;
        this.staff = staff;
        this.stayRooms = stayRooms;
    }


    public void addServiceZone(ServiceZone newServiceZone) {
        servicezone.add(newServiceZone);
        newServiceZone.setLeservice(this);
    }


    public void addStaff(Staff newStaff) {
        staff.add(newStaff);
        newStaff.setLeService(this);
    }


    public void addStayRoom(StayRoom newStayRoom){
        stayRooms.add(newStayRoom);
        newStayRoom.setLeService(this);

    }
    public StayRoom getStayRoomByKey(long stayRoomKey) {
        return stayRooms.stream().filter(room -> room.getStayRoom_Ky() == stayRoomKey).findFirst().orElse(null);
    }
    @Override
    public String toString() {
        return "LeService{" +
                "service_ky=" + service_ky +
                ", service_Nm='" + service_Nm + '\'' +
                '}';
    }
}
