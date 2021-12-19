package magenta.distancecalculator.service;

import magenta.distancecalculator.dto.RequestDTO;
import magenta.distancecalculator.dto.DistanceDTO;

import java.util.List;


public interface DistanceService {
    List<DistanceDTO> calculate(RequestDTO requestDTO);
}