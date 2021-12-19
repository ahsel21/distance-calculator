package magenta.distancecalculator.repository;

import magenta.distancecalculator.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepo extends JpaRepository<City, Long> {
    City getByName(String name);
}