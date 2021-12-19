package magenta.distancecalculator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import magenta.distancecalculator.dto.DistanceDTO;
import magenta.distancecalculator.service.InputService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("input")
@RequiredArgsConstructor
public class InputController {
    private final InputService inputService;

    @PostMapping
    public ResponseEntity<List<DistanceDTO>> calculateDistance(@RequestPart MultipartFile document) throws IOException {
        //log.info(new String(document.getBytes()));
        if (!document.isEmpty()) {
            inputService.input(document);
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}