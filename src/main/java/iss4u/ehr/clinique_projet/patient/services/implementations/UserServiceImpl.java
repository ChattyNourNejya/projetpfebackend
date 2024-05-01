package iss4u.ehr.clinique_projet.patient.services.implementations;



import iss4u.ehr.clinique_projet.patient.repositories.UserRepository;
import iss4u.ehr.clinique_projet.patient.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
