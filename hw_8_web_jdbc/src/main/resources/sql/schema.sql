CREATE SCHEMA hw_8_hospital;

CREATE TABLE hw_8_hospital.doctor
(
    id          BIGINT AUTO_INCREMENT
        PRIMARY KEY,
    created     DATETIME(6) NULL,
    updated     DATETIME(6) NULL,
    visible     BIT null,
    last_name   VARCHAR(255) NOT NULL,
    first_name  VARCHAR(255) NOT NULL,
    middle_name VARCHAR(255) NOT NULL,
    specialization VARCHAR(255) NOT NULL

);

create table hw_8_hospital.patient
(
    id         BIGINT AUTO_INCREMENT
        PRIMARY KEY,
    created    DATETIME(6) NULL,
    updated    DATETIME(6) NULL,
    visible    BIT null,
    last_name   VARCHAR(255) NOT NULL,
    first_name  VARCHAR(255) NOT NULL,
    age        INT          NOT NULL
);

create table hw_8_hospital.declaration
(
    id         BIGINT AUTO_INCREMENT
        PRIMARY KEY,
    doctor_id  BIGINT NOT NULL,
    patient_id BIGINT NOT NULL,
    FOREIGN KEY (doctor_id) REFERENCES doctor (id) ON DELETE CASCADE,
    FOREIGN KEY (patient_id) REFERENCES patient (id) ON DELETE CASCADE
);