package org.voiculescu.sdjpainheritance.domain.msuper;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "order")
public class Order extends BaseEntity {

    private String customerName;

}
