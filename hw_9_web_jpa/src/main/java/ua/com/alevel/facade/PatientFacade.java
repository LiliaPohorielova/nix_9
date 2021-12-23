package ua.com.alevel.facade;

import ua.com.alevel.view.dto.request.PatientRequestDto;
import ua.com.alevel.view.dto.response.DoctorResponseDto;
import ua.com.alevel.view.dto.response.PatientResponseDto;

import java.util.List;

public interface PatientFacade extends BaseFacade<PatientRequestDto, PatientResponseDto>{

    List<DoctorResponseDto> getDoctors(Long id);
}