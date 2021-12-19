package magenta.distancecalculator.service;

import magenta.distancecalculator.dto.CityDTO;

import java.util.List;

public interface CityService {
    List<CityDTO> findAll();
}