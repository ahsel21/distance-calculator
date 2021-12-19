package magenta.distancecalculator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import magenta.distancecalculator.domain.DistanceType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO {
    @JsonProperty("from")
    @NotNull
    @NotEmpty
    private List<String> fromCity;

    @JsonProperty("to")
    @NotNull
    @NotEmpty
    private List<String> toCity;

    @JsonProperty("type")
    @NotNull
    @NotEmpty
    private DistanceType calculationType;
}