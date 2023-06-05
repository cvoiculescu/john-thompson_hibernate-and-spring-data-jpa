package org.voiculescu.orderservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@AttributeOverrides({
        @AttributeOverride(name = "shipping.address", column = @Column(name = "shipping_address")),
        @AttributeOverride(name = "shipping.city", column = @Column(name = "shipping_city")),
        @AttributeOverride(name = "shipping.state", column = @Column(name = "shipping_state")),
        @AttributeOverride(name = "shipping.zipCode", column = @Column(name = "shipping_zip_code")),
        @AttributeOverride(name = "billTo.address", column = @Column(name = "bill_to_address")),
        @AttributeOverride(name = "billTo.city", column = @Column(name = "bill_to_city")),
        @AttributeOverride(name = "billTo.state", column = @Column(name = "bill_to_state")),
        @AttributeOverride(name = "billTo.zipCode", column = @Column(name = "bill_to_zip_code"))
})
@Table(name = "order_header")
public class OrderHeader extends BaseEntity {

    private String customerName;
    @Embedded
    private Address shipping;
    @Embedded
    private Address billTo;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @OneToMany(mappedBy = "orderHeader", cascade = CascadeType.PERSIST)
    private Set<OrderLine> orderLines;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        OrderHeader that = (OrderHeader) o;

        if (!Objects.equals(customerName, that.customerName)) return false;
        if (!Objects.equals(shipping, that.shipping)) return false;
        if (!Objects.equals(billTo, that.billTo)) return false;
        if (orderStatus != that.orderStatus) return false;
        return Objects.equals(orderLines, that.orderLines);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (customerName != null ? customerName.hashCode() : 0);
        result = 31 * result + (shipping != null ? shipping.hashCode() : 0);
        result = 31 * result + (billTo != null ? billTo.hashCode() : 0);
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        result = 31 * result + (orderLines != null ? orderLines.hashCode() : 0);
        return result;
    }

}
