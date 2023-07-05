package br.com.studioequipment.apis.api;


import br.com.studioequipment.adapters.EquipmentAdapter;
import br.com.studioequipment.adapters.EquipmentDTOAdapter;
import br.com.studioequipment.apis.dto.requests.EquipmentDTO;
import br.com.studioequipment.apis.dto.responses.responses.DeleteResponseDTO;
import br.com.studioequipment.apis.dto.responses.responses.EquipmentListResponseDTO;
import br.com.studioequipment.apis.dto.responses.responses.EquipmentResponseDTO;
import br.com.studioequipment.exceptions.EquipmentNotFoundException;
import br.com.studioequipment.exceptions.PersonNotFoundException;
import br.com.studioequipment.exceptions.SaveMethodException;
import br.com.studioequipment.repository.entities.Equipment;
import br.com.studioequipment.service.interfaces.IEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/V1/Equipment")
public class EquipmentAPI {

    @Autowired
    private IEquipmentService equipmentService;

    @PostMapping("/new")
    public EquipmentResponseDTO addWithBody(@RequestBody EquipmentDTO equipmentDTO)
            throws SaveMethodException, PersonNotFoundException {
        return EquipmentResponseDTO.builder()
                .data(
                        EquipmentDTOAdapter.convertTo(
                                equipmentService.save(EquipmentAdapter.convertTo(equipmentDTO))
                        )
                )
                .build();
    }

    @GetMapping("/find/{document}")
    public EquipmentResponseDTO find(@PathVariable("equipment") String equipmentNumber)
        throws EquipmentNotFoundException {
        return EquipmentResponseDTO.builder()
                .data(
                        EquipmentDTOAdapter.convertTo(
                                equipmentService.findEquipmentByName(equipmentNumber)
                        )
                )
                .build();
    }

    @GetMapping("/list")
    public EquipmentListResponseDTO listAllEquipment() {
        return EquipmentListResponseDTO.builder()
                .data(
                        EquipmentDTOAdapter.convertToList(
                                equipmentService.listAll()
                        )
                )
                .build();
    }

    @PutMapping("/change/{name-change}")
    public EquipmentResponseDTO changeWithBody(@RequestBody EquipmentDTO equipmentDTO)
        throws EquipmentNotFoundException, PersonNotFoundException {
        return EquipmentResponseDTO.builder()
                .data(
                        EquipmentDTOAdapter.convertTo(
                                equipmentService.update(
                                        EquipmentAdapter.convertTo(equipmentDTO)
                                )
                        )
                ).build();
    }

    @DeleteMapping("/delete/{equipmentId}")
    public ResponseEntity<DeleteResponseDTO> delete(@PathVariable("equipmentId") String equipmentId)
        throws PersonNotFoundException, EquipmentNotFoundException {
        equipmentService.delete(Equipment.builder().idNumber(equipmentId).build());

        return ResponseEntity.ok(DeleteResponseDTO.builder()
                .deleteSuccessMessage("Document successfully deleted")
                .build());
    }
}
