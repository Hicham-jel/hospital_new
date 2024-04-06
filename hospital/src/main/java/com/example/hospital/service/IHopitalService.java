package com.example.hospital.service;

import com.example.hospital.entities.Consultation;
import com.example.hospital.entities.Medcin;
import com.example.hospital.entities.Patient;
import com.example.hospital.entities.RendezVous;

public interface IHopitalService {
     Patient savePatient(Patient patient);
     Medcin saveMedcin(Medcin medcin);
     RendezVous saveRendezVous(RendezVous rendezVous);
     Consultation saveConsultation(Consultation consultation);
}
