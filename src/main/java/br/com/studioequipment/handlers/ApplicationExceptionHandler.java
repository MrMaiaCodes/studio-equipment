package br.com.studioequipment.handlers;

import br.com.studioequipment.apis.dto.responses.errors.ErrorResponseDTO;
import br.com.studioequipment.apis.dto.responses.errors.ErrorSpecificationDTO;
import br.com.studioequipment.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ApplicationExceptionHandler {

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> PersonNotFoundExceptionHandler(PersonNotFoundException exception) {
        log.info("wound up on exception handler, for not having found a person");

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponseDTO.builder()
                        .data(ErrorSpecificationDTO.builder()
                                .errorCode("100")
                                .errorMessage(exception.getMessage())
                                .build())
                        .build());
    }

    @ExceptionHandler(EquipmentNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> EquipmentNotFoundExceptionHandler(EquipmentNotFoundException exception) {
        log.info("wound up on exception handler, for not having found a document");

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponseDTO.builder()
                        .data(ErrorSpecificationDTO.builder()
                                .errorCode("100")
                                .errorMessage(exception.getMessage())
                                .build())
                        .build());
    }

    @ExceptionHandler(MicrophoneNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> MicrophoneNotFoundExceptionHandler(MicrophoneNotFoundException exception) {
        log.info("wound up on exception handler for not having found a microphone");

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponseDTO.builder()
                        .data(ErrorSpecificationDTO.builder()
                                .errorCode("100")
                                .errorMessage(exception.getMessage())
                                .build())
                        .build());

    }

    @ExceptionHandler(EmptyListException.class)
    public ResponseEntity<ErrorResponseDTO>EmptyListExceptionHandler(EmptyListException exception) {
        log.info("wound up on exception handler, list is empty");

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(ErrorResponseDTO.builder()
                        .data(ErrorSpecificationDTO.builder()
                                .errorCode("100")
                                .errorMessage(exception.getMessage())
                                .build())
                        .build());
    }

    @ExceptionHandler(EveryoneHasEquipmentException.class)
    public ResponseEntity<ErrorResponseDTO>NoSuchObjectExceptionHandler(EveryoneHasEquipmentException exception) {
        log.info("wound up on exception handler, no objects with those specifications found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponseDTO.builder()
                        .data(ErrorSpecificationDTO.builder()
                                .errorCode("100")
                                .errorMessage(exception.getMessage())
                                .build())
                        .build());
    }

    @ExceptionHandler(SaveMethodException.class)
    public ResponseEntity<ErrorResponseDTO> SaveMethodExceptionHandler(SaveMethodException exception) {
        log.info("wound up on exception handler, for invalid input");

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponseDTO.builder()
                        .data(ErrorSpecificationDTO.builder()
                                .errorCode("100")
                                .errorMessage(exception.getMessage())
                                .build())
                        .build());
    }
}
