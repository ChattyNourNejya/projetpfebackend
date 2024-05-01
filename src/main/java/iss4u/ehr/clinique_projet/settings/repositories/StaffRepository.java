package iss4u.ehr.clinique_projet.settings.repositories;

import iss4u.ehr.clinique_projet.settings.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
   // Staff findByStaff_Nm(String staff_Nm); // Adjusted method name to match the property

}
