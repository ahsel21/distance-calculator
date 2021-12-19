package magenta.distancecalculator.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "DISTANCE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Distance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FROM_CITY")
    private City fromCity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TO_CITY")
    private City toCity;

    @Column(name = "DISTANCE")
    private Double distance;

}
