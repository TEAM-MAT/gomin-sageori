package MAT.gominsageori.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "stars", uniqueConstraints = {
        @UniqueConstraint(
                name = "uqUserRestaurant",
                columnNames = {"userPK","restaurantPK"}
        )
})
public class Stars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int star_order;

    @Column(nullable = false)
    private Long userPK;

    @Column(nullable = false)
    private Long restaurantPK;

    @Column(nullable = false)
    private float stars = 0.0F;

}
