package com.example.hospital;

import com.example.hospital.entities.*;
import com.example.hospital.repositories.MedcinRepository;
import com.example.hospital.repositories.PatientRepository;
import com.example.hospital.repositories.RendezVousRepository;
import com.example.hospital.service.IHopitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class hospital_new {

    public static void main(String[] args) {
        SpringApplication.run(hospital_new.class, args);
    }

    @Bean
    CommandLineRunner start(IHopitalService hopitalService,
                            PatientRepository patientRepository,
                            RendezVousRepository rendezVousRepository,
                            MedcinRepository medcinRepository) {
        return args -> {
            Stream.of("Mohamed", "Hassan", "Najat")
                    .forEach(name->{
                        Patient patient=new Patient();
                        patient.setNom(name);
                        patient.setDateNaissance(new Date());
                        patient.setMalade(false);
                        hopitalService.savePatient(patient);
                    });
            Stream.of("Ayman", "Hanane", "Yassmine")
                    .forEach(name->{
                        Medcin medcin=new Medcin();
                        medcin.setNom(name);
                        medcin.setEmail(name+"@gmail.com");
                        medcin.setSpecialite(Math.random()>0.5?"Cardio":"Dentiste");
                        hopitalService.saveMedcin(medcin);
                    });
            Patient patient=patientRepository.findById(1L).orElse(null);
            Patient patient1=patientRepository.findByNom("Mohamed");

            Medcin medcin=medcinRepository.findByNom("Yassmine");

            RendezVous rendezVous=new RendezVous();
            rendezVous.setDate(new Date());
            rendezVous.setStatus(StatusRDV.PENDING);
            rendezVous.setMedcin(medcin);
            rendezVous.setPatient(patient);
            RendezVous savedRDV = rendezVousRepository.save(rendezVous);
            System.out.println(savedRDV.getId());



            RendezVous rendezVous1=rendezVousRepository.findById(1L).orElse(null);
            Consultation consultation=new Consultation();
            consultation.setDateConsultation(new Date());
            consultation.setRendezVous(rendezVous1);
            consultation.setRapport("Rapport de la consultation .....");
            hopitalService.saveConsultation(consultation);
        };
    }
}
