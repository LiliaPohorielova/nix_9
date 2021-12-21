package ua.com.alevel.facade;

import ua.com.alevel.view.dto.request.DeclarationRequestDto;
import ua.com.alevel.view.dto.response.DeclarationResponseDto;
import ua.com.alevel.view.dto.response.DoctorResponseDto;
import ua.com.alevel.view.dto.response.PatientResponseDto;

import java.util.List;

public interface DeclarationFacade extends BaseFacade<DeclarationRequestDto, DeclarationResponseDto> {

    List<PatientResponseDto> findByDoctorId(Long doctorId);

    List<DoctorResponseDto> findByPatientId(Long patientId);

    DeclarationResponseDto findByDoctorIdAndPatientId(Long patientId, Long doctorId);
}
