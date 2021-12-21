package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Declaration;

public class DeclarationResponseDto extends ResponseDto {

    private Long doctorId;
    private Long patientId;

    public DeclarationResponseDto(Declaration declaration) {
        if (declaration != null) {
            super.setId(declaration.getId());
            this.doctorId = declaration.getDoctorId();
            this.patientId = declaration.getPatientId();
        }
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }
}
