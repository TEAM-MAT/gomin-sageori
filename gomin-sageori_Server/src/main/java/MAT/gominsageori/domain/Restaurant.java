package MAT.gominsageori.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "Restaurant")
@Getter
@Setter
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Long id;

    @Column( nullable= false , unique = false , length = 50)
    private String name;

    @Column (nullable = true)
    private boolean Franchise; // 프랜차이즈 여부

    @Column (nullable = true)
    private Float external_star; //외부 평점

    @Column (nullable = true)
    private Float internal_start; //내부 평점

    @Column (nullable = true)
    private Time startTime; //영업시작시간

    @Column (nullable = true)
    private Time finTime; //영업 종료 시간
/*
    @ManyToOne
    @JoinColumn(name = "bestMenu" , nullable = true)
    private Menu bestMenu;
*/
    @Column (nullable= true)
    private String restaurant_type;

    @Column (nullable = true)
    private String call_number;

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}
