package ua.com.alevel.facade;

import ua.com.alevel.view.dto.request.DoctorRequestDto;
import ua.com.alevel.view.dto.response.DoctorResponseDto;

import java.util.List;

public interface DoctorFacade extends BaseFacade<DoctorRequestDto, DoctorResponseDto> {

    List<DoctorResponseDto> findAll();
}