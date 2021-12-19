package magenta.distancecalculator.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import magenta.distancecalculator.domain.City;
import magenta.distancecalculator.domain.Distance;
import magenta.distancecalculator.dto.CityDTO;
import magenta.distancecalculator.dto.DistanceDTO;
import magenta.distancecalculator.parser.SaxParserHandler;
import magenta.distancecalculator.repository.CityRepo;
import magenta.distancecalculator.repository.DistanceRepo;
import magenta.distancecalculator.service.InputService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class InputServiceImpl implements InputService {
    private final DistanceRepo distanceRepo;
    private final CityRepo cityRepo;


    @Override
    public void input(MultipartFile document) throws IOException {
        File file = File.createTempFile("data", "xml");
        document.transferTo(file);
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SaxParserHandler handler = new SaxParserHandler();
        SAXParser parser = null;
        try {
            parser = factory.newSAXParser();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (parser != null) {
                parser.parse(file, handler);
                distanceRepo.deleteAll();
                cityRepo.deleteAll();
                List<City> cityList = new ArrayList<>();
                List<Distance> distanceList = new ArrayList<>();
                for (CityDTO cityDto : handler.getCityDTOList()) {
                    City newCity = new City();
                    newCity.setName(cityDto.getName());
                    newCity.setLongitude(cityDto.getLongitude());
                    newCity.setLatitude(cityDto.getLatitude());
                    cityList.add(newCity);
                }
                try {
                    cityRepo.saveAll(cityList);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                log.info("Table 'City' was update");
                for (DistanceDTO distanceDTO : handler.getDistanceDTOList()) {
                    Distance newDistance = new Distance();
                    newDistance.setDistance(distanceDTO.getDistance());
                    newDistance.setFromCity(cityRepo.getByName(distanceDTO.getFromCity()));
                    newDistance.setToCity(cityRepo.getByName(distanceDTO.getToCity()));
                    distanceList.add(newDistance);
                }
                distanceRepo.saveAll(distanceList);
                log.info("Table 'Distance' was update");
            }
        } catch (SAXException e) {
            e.printStackTrace();
        }
        file.deleteOnExit();

    }
}