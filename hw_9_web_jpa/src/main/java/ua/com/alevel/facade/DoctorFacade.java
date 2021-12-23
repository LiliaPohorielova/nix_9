package ua.com.alevel.facade;

import ua.com.alevel.view.dto.request.DoctorRequestDto;
import ua.com.alevel.view.dto.response.DoctorResponseDto;
import ua.com.alevel.view.dto.response.PatientResponseDto;

import java.util.List;

public interface DoctorFacade extends BaseFacade<DoctorRequestDto, DoctorResponseDto> {

    List<PatientResponseDto> getPatients(Long id);

    void addPatient(Long doctorId, Long patientId);

    void removePatient(Long doctorId, Long patientId);
}