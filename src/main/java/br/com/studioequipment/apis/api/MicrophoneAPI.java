package br.com.studioequipment.apis.api;

import br.com.studioequipment.apis.dto.requests.MicrophoneDTO;
import br.com.studioequipment.service.interfaces.IMicrophoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/V1/Microphone")
public class MicrophoneAPI {

    @Autowired
    private IMicrophoneService microphoneService;

    @PostMapping("/new")
    public ResponseEntity newMicrophone(@RequestBody MicrophoneDTO microphoneDTO)
}
