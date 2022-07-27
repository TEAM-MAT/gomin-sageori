package MAT.gominsageori.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "stars")
public class stars {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int order;

    @Column(nullable = false)
    private Long userPK;

    @Column(nullable = false)
    private Long restaurantPK;

    @Column(nullable = false)
    private float stars = 0.0F;

}
