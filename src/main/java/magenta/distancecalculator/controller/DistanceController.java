package magenta.distancecalculator.controller;

import lombok.RequiredArgsConstructor;
import magenta.distancecalculator.dto.RequestDTO;
import magenta.distancecalculator.dto.DistanceDTO;
import magenta.distancecalculator.service.DistanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("distance")
@RequiredArgsConstructor
public class DistanceController {
    private final DistanceService distanceService;

    @PostMapping
    public ResponseEntity<List<DistanceDTO>> calculateDistance(@RequestBody @Valid RequestDTO requestDTO) {
        return ResponseEntity.ok(distanceService.calculate(requestDTO));
    }
}
