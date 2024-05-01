package iss4u.ehr.clinique_projet.settings.services;
import java.util.List;

import iss4u.ehr.clinique_projet.exception.UserNotFoundException;
import iss4u.ehr.clinique_projet.settings.entities.Staff;
import iss4u.ehr.clinique_projet.settings.entities.StaffGroup;
import iss4u.ehr.clinique_projet.settings.repositories.StaffGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class StaffGroupManagement {
	@Autowired
	StaffGroupRepository staffGroupRepository;
	
	public StaffGroup addStaffGroup(StaffGroup staffGroup) {
		System.out.println("Staff added successfully");
		return staffGroupRepository.save(staffGroup);
	}
	
	
	public List<StaffGroup> findallStaffGroup(){
		return staffGroupRepository.findAll();
	}
	
	
	public StaffGroup updateStaffGroup(StaffGroup staffGroup) {
		System.out.println("Staff updated successfully");
		return staffGroupRepository.save(staffGroup);
	}
	
	public StaffGroup findStaffGroupById(int StaffGroup_Ky) {
		return staffGroupRepository.findById(StaffGroup_Ky).orElseThrow(()->new UserNotFoundException(StaffGroup_Ky +"Not Found"));
		
	}
	

	public void deleteStaffGroup (int StaffGroup_Ky) {
		System.out.println("Staff deleted");
		staffGroupRepository.deleteById(StaffGroup_Ky);
	}


	public Staff getStaffById(int i) {
		// TODO Auto-generated method stub
		return null;
	}

}
