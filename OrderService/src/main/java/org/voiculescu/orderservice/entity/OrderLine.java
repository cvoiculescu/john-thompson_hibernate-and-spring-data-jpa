package org.voiculescu.orderservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "order_line")
public class OrderLine extends BaseEntity{
    private Integer quantityOrdered;

    @ManyToOne
    private OrderHeader orderHeader;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        OrderLine orderLine = (OrderLine) o;

        return Objects.equals(quantityOrdered, orderLine.quantityOrdered);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (quantityOrdered != null ? quantityOrdered.hashCode() : 0);
        return result;
    }
}
