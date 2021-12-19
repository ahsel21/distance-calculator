package magenta.distancecalculator.controller;

import lombok.RequiredArgsConstructor;
import magenta.distancecalculator.dto.CityDTO;
import magenta.distancecalculator.service.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("city")
public class CityController {
    private final CityService cityService;

    @GetMapping
    public ResponseEntity<List<CityDTO>> getCityList() {
        List<CityDTO> citiesDTOList = cityService.findAll();
        return ResponseEntity.ok(citiesDTOList);
    }
}
