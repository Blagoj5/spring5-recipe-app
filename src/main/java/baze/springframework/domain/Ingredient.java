package baze.springframework.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private BigDecimal amount;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeassure unitOfMeassure;

    @ManyToOne
    private Recipe recipe;

    public Ingredient(){}

    public Ingredient(String description, BigDecimal amount, UnitOfMeassure unitOfMeassure) {
        this.description = description;
        this.amount = amount;
        this.unitOfMeassure = unitOfMeassure;
    }

}
