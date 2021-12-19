package magenta.distancecalculator.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import magenta.distancecalculator.domain.City;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDTO {
    @NotNull
    @NotEmpty
    private long id;

    @NotBlank
    @NotEmpty
    private String name;

    @NotNull
    @JsonIgnore
    private Double latitude;

    @NotNull
    @JsonIgnore
    private Double longitude;

    public CityDTO(City city) {
        this.id = city.getId();
        this.name = city.getName();
        this.longitude = city.getLongitude();
        this.latitude = city.getLatitude();
    }
}