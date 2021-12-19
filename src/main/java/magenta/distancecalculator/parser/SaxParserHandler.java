package magenta.distancecalculator.parser;

import lombok.extern.slf4j.Slf4j;
import magenta.distancecalculator.dto.CityDTO;
import magenta.distancecalculator.dto.DistanceDTO;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SaxParserHandler extends DefaultHandler {
    private final static String TAG_CITY_ELEMENT = "cityElement";
    private final static String TAG_NAME = "name";
    private final static String TAG_LATITUDE = "latitude";
    private final static String TAG_LONGITUDE = "longitude";

    private final static String TAG_DISTANCE_ELEMENT = "distanceElement";
    private final static String TAG_FROM = "cityFrom";
    private final static String TAG_TO = "cityTo";
    private final static String TAG_DISTANCE = "distance";

    private String currentTagName;
    private boolean isCity;
    private boolean isDistance;
    private String name;
    private double longitude;
    private double latitude;
    private double distance;
    private String fromCity;
    private String toCity;

    public List<CityDTO> cityDTOList = new ArrayList<>();
    public List<DistanceDTO> distanceDTOList = new ArrayList<>();

    @Override
    public void startDocument() throws SAXException {
        log.info("Start parsing");
    }

    @Override
    public void endDocument() throws SAXException {
        log.info("End parsing");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentTagName = qName;
        if (currentTagName.equals(TAG_CITY_ELEMENT)) {
            isCity = true;
        } else if (currentTagName.equals(TAG_DISTANCE_ELEMENT)) {
            isDistance = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case TAG_CITY_ELEMENT:
                isCity = false;
                CityDTO newCity = new CityDTO();
                newCity.setLatitude(latitude);
                newCity.setLongitude(longitude);
                newCity.setName(name);
                cityDTOList.add(newCity);
                break;

            case TAG_DISTANCE_ELEMENT:
                isDistance = false;
                DistanceDTO newDistance = new DistanceDTO();
                newDistance.setDistance(distance);
                newDistance.setFromCity(fromCity);
                newDistance.setToCity(toCity);
                distanceDTOList.add(newDistance);
                break;
        }
        currentTagName = null;
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentTagName == null) {
            return;
        }
        if (isCity) {
            switch (currentTagName) {
                case TAG_NAME:
                    name = new String(ch, start, length);
                    break;

                case TAG_LATITUDE:
                    latitude = Double.parseDouble(new String(ch, start, length));
                    break;

                case TAG_LONGITUDE:
                    longitude = Double.parseDouble(new String(ch, start, length));
                    break;
            }
        }
        if (isDistance) {
            switch (currentTagName) {
                case TAG_FROM:
                    fromCity = new String(ch, start, length);
                    break;

                case TAG_TO:
                    toCity = new String(ch, start, length);
                    break;

                case TAG_DISTANCE:
                    this.distance = Double.parseDouble(new String(ch, start, length));
                    break;
            }
        }
    }

    public List<CityDTO> getCityDTOList() {
        return cityDTOList;
    }

    public List<DistanceDTO> getDistanceDTOList() {
        return distanceDTOList;
    }
}