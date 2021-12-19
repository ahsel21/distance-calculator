package magenta.distancecalculator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import magenta.distancecalculator.domain.DistanceType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistanceDTO {
    @NotBlank
    @NotEmpty
    private String fromCity;

    @NotBlank
    @NotEmpty
    private String toCity;

    @NotNull
    @NotEmpty
    private double distance;

    @NotNull
    @NotEmpty
    private DistanceType calculationType;
}