package iss4u.ehr.clinique_projet.settings.services;


import iss4u.ehr.clinique_projet.exception.UserNotFoundException;
import iss4u.ehr.clinique_projet.settings.entities.Staff;
import iss4u.ehr.clinique_projet.settings.repositories.StaffGroupRepository;
import iss4u.ehr.clinique_projet.settings.repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class StaffManagement  {
    @Autowired
    private StaffRepository staffRepository;
    
    @Autowired
    private StaffGroupRepository staffGroupRepository;

	
	public Staff addStaff(Staff aStaff) {
		System.out.println("Staff added successfully");
		return staffRepository.save(aStaff);
	}
	
	public List<Staff> findallStaff(){
		return staffRepository.findAll();
	}
	
	public Staff updateStaff(Staff aStaff) {
		System.out.println("Staff updated successfully");
		return staffRepository.save(aStaff);
	}
	
	public Staff findStaffById(int aStaff_Ky) {
		return staffRepository.findById(aStaff_Ky).orElseThrow(()->new UserNotFoundException(aStaff_Ky +"Not Found"));
		
	}
	/*public Staff findStaffByName(String Staff_Nm){
		return staffRepository.findByStaff_Nm(Staff_Nm);
	}*/
	
	public void deleteStaff (int aStaff_Ky) {
		System.out.println("Staff deleted");
		staffRepository.deleteById(aStaff_Ky);
	}
	
	//L'administrateur sélectionne le personnel qu'il souhaite activé
	public Staff enableStaff (int aStaff_Ky) {
		System.out.println("Staff enabled successfully");
		Staff staff= staffRepository.findById(aStaff_Ky).orElseThrow(()->new UserNotFoundException(aStaff_Ky +"Not Found"));
		staff.setStaff_status(true);
		return staffRepository.save(staff);

	}
		//L'administrateur sélectionne le personnel qu'il souhaite désactiver.
    	public Staff disableStaff (int aStaff_Ky) {
		System.out.println("Staff disable successfully");
		Staff staff= staffRepository.findById(aStaff_Ky).orElseThrow(()->new UserNotFoundException(aStaff_Ky +"Not Found"));
		staff.setStaff_status(false);
		return staffRepository.save(staff);

	}


    public Optional<Staff> getStaffById(int id) {
        return staffRepository.findById(id);
    }

    public void setStaffRepository(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }


	
}
