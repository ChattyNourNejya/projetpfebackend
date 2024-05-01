package iss4u.ehr.clinique_projet.patient.repositories;

import iss4u.ehr.clinique_projet.patient.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User,Long> {
}
