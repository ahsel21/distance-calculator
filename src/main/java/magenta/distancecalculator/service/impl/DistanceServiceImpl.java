package magenta.distancecalculator.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import magenta.distancecalculator.domain.City;
import magenta.distancecalculator.domain.Distance;
import magenta.distancecalculator.domain.DistanceType;
import magenta.distancecalculator.dto.RequestDTO;
import magenta.distancecalculator.dto.DistanceDTO;
import magenta.distancecalculator.exception.DataNotFoundException;
import magenta.distancecalculator.repository.CityRepo;
import magenta.distancecalculator.repository.DistanceRepo;
import magenta.distancecalculator.service.DistanceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DistanceServiceImpl implements DistanceService {
    public static final double EARTH_RADIUS = 6371; //в соответствии со стандартом WGS84
    private final DistanceRepo distanceRepo;
    private final CityRepo cityRepo;

    @Override
    public List<DistanceDTO> calculate(RequestDTO requestDTO) {
        List<DistanceDTO> distanceDTOList = new ArrayList<>();
        switch (requestDTO.getCalculationType()) {
            case ALL:
                distanceDTOList = all(requestDTO);
                break;

            case DISTANCE_MATRIX:
                distanceDTOList = distanceMatrix(requestDTO);
                break;

            case CROWFLIGHT:
                distanceDTOList = crowFlight(requestDTO);
                break;
        }
        return distanceDTOList;
    }

    private List<DistanceDTO> crowFlight(RequestDTO requestDTO) {
        List<DistanceDTO> distanceDTOList = new ArrayList<>();
        for (int i = 0; i < requestDTO.getFromCity().size(); i++) {
            for (int j = 0; j < requestDTO.getToCity().size(); j++) {
                City from = cityRepo.getByName(requestDTO.getFromCity().get(i));
                City to = cityRepo.getByName(requestDTO.getToCity().get(j));
                if (from != null && to != null) {
                    double fromLatitude = Math.toRadians(from.getLatitude());
                    double fromLongitude = Math.toRadians(from.getLongitude());
                    double toLatitude = Math.toRadians(to.getLatitude());
                    double toLongitude = Math.toRadians(to.getLongitude());
                    double latitudeDelta = toLatitude - fromLatitude;
                    double longitudeDelta = toLongitude - fromLongitude;
                    double distance = 2 * EARTH_RADIUS * Math.asin(
                            Math.sqrt(Math.pow(Math.sin(latitudeDelta / 2), 2)
                                    + Math.cos(fromLatitude)
                                    * Math.cos(toLatitude)
                                    * Math.pow(Math.sin(longitudeDelta / 2), 2)));
                    distanceDTOList.add(new DistanceDTO(
                            from.getName(),
                            to.getName(),
                            distance,
                            DistanceType.CROWFLIGHT));
                } else {
                    DataNotFoundException dataNotFoundException = new DataNotFoundException(
                            "Not found crowFlight distance between "
                                    + requestDTO.getFromCity().get(i)
                                    + " and " + requestDTO.getToCity().get(j));
                    log.warn(dataNotFoundException.toString());
                    throw dataNotFoundException;
                }
            }
        }
        return distanceDTOList;
    }

    private List<DistanceDTO> distanceMatrix(RequestDTO requestDTO) {
        List<DistanceDTO> distanceDTOList = new ArrayList<>();
        for (int i = 0; i < requestDTO.getFromCity().size(); i++) {
            for (int j = 0; j < requestDTO.getToCity().size(); j++) {
                City from = cityRepo.getByName(requestDTO.getFromCity().get(i));
                City to = cityRepo.getByName(requestDTO.getToCity().get(j));
                Optional<Distance> optionalDistance = distanceRepo.getDistanceByFromCityAndToCity(from, to);
                if (optionalDistance.isPresent()) {
                    distanceDTOList.add(
                            new DistanceDTO(
                                    from.getName(),
                                    to.getName(),
                                    optionalDistance.get().getDistance(),
                                    DistanceType.DISTANCE_MATRIX)
                    );
                } else if (distanceRepo.getDistanceByFromCityAndToCity(to, from).isPresent()) {
                    distanceRepo.getDistanceByFromCityAndToCity(to, from).ifPresent(distance -> {
                        distanceDTOList.add(
                                new DistanceDTO(
                                        from.getName(),
                                        to.getName(),
                                        distance.getDistance(),
                                        DistanceType.DISTANCE_MATRIX)
                        );
                    });
                } else {
                    DataNotFoundException dataNotFoundException = new DataNotFoundException(
                            "Not found distance in distanceMatrix between "
                                    + requestDTO.getFromCity().get(i)
                                    + " and " + requestDTO.getToCity().get(j));
                    log.warn(dataNotFoundException.toString());
                    throw dataNotFoundException;
                }
            }
        }
        return distanceDTOList;
    }

    private List<DistanceDTO> all(RequestDTO requestDTO) {
        List<DistanceDTO> distanceDTOList = crowFlight(requestDTO);
        distanceDTOList.addAll(distanceMatrix(requestDTO));
        return distanceDTOList;
    }
}
