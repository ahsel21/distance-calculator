package magenta.distancecalculator.repository;

import magenta.distancecalculator.domain.City;
import magenta.distancecalculator.domain.Distance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DistanceRepo extends JpaRepository<Distance, Long> {
    Optional<Distance> getDistanceByFromCityAndToCity(City fromCity, City toCity);
}