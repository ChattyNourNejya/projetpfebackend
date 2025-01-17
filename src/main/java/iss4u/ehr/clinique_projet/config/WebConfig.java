package iss4u.ehr.clinique_projet.config;


import iss4u.ehr.clinique_projet.insurance.entities.Insurance;
import iss4u.ehr.clinique_projet.insurance.repositories.InsuranceRepository;
import iss4u.ehr.clinique_projet.insurance.services.InsuranceService;
import iss4u.ehr.clinique_projet.insurance.services.implementations.InsuranceServiceImpl;
import iss4u.ehr.clinique_projet.patient.repositories.PatientRepository;
import iss4u.ehr.clinique_projet.patient.services.PatientService;
import iss4u.ehr.clinique_projet.patient.services.implementations.PatientServiceImpl;

import iss4u.ehr.clinique_projet.settings.entities.LeService;
import iss4u.ehr.clinique_projet.settings.repositories.LeServiceRepository;
import iss4u.ehr.clinique_projet.settings.repositories.StayRoomRepository;
import iss4u.ehr.clinique_projet.settings.services.LeServiceService;
import iss4u.ehr.clinique_projet.settings.services.ServicePlannerService;
import iss4u.ehr.clinique_projet.stay.repositories.StayRepository;
import iss4u.ehr.clinique_projet.stay.services.StayService;
import iss4u.ehr.clinique_projet.stay.services.implementations.StayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LeServiceRepository LeserviceRepository;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }
    @Bean
    public PatientService patientService(PatientRepository patientRepository) {
        return new PatientServiceImpl(patientRepository);
    }
    @Bean
    @Autowired
    public StayService stayService(StayRepository stayRepository) {
        return new StayServiceImpl(stayRepository);
    }

    @Bean
    public Insurance insuranceBean() {
        return new Insurance();
    }
    @Bean
    @Autowired
    public InsuranceService insuranceService(InsuranceRepository insuranceRepository) {
        return new InsuranceServiceImpl(insuranceRepository);
    }

    @Bean
    public LeService Leservice() {
        return new LeService();
    }

    @Bean
    @Autowired
    public LeServiceService LeserviceService(LeServiceRepository LeserviceRepository, StayRoomRepository stayRoomRepository) {
        return new LeServiceService(LeserviceRepository, stayRoomRepository);
    }

    @Bean
    @Autowired
    public ServicePlannerService servicePlannerService() {
        return new ServicePlannerService(LeserviceRepository);
    }



}