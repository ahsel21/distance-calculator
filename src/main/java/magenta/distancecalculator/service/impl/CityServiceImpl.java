package magenta.distancecalculator.service.impl;

import lombok.RequiredArgsConstructor;
import magenta.distancecalculator.domain.City;
import magenta.distancecalculator.dto.CityDTO;
import magenta.distancecalculator.exception.DataNotFoundException;
import magenta.distancecalculator.repository.CityRepo;
import magenta.distancecalculator.service.CityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepo cityRepo;


    @Override
    public List<CityDTO> findAll() {
        List<CityDTO> cityDTOList = cityRepo.findAll()
                .stream()
                .map(CityDTO::new)
                .collect(Collectors.toList());
        if (cityDTOList.isEmpty()) {
            throw new DataNotFoundException("DataBase is empty!");
        }
        return cityDTOList;
    }
}