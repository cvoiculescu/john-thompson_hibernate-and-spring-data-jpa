package org.voiculescu.orderservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
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
@Table(name = "customer")
public class Customer extends BaseEntity {

    @Length(max = 50)
    private String name;
    @Embedded
    private Address shipping;
    @Embedded
    private Address billTo;
    @Length(max = 20)
    private String phone;
    @Length(max = 20)
    private String email;

    @OneToMany(mappedBy = "customer")
    Set<OrderHeader> orders = new HashSet<>();

    @Version
    private Integer version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Customer customer = (Customer) o;

        if (!Objects.equals(name, customer.name)) return false;
        if (!Objects.equals(shipping, customer.shipping)) return false;
        if (!Objects.equals(billTo, customer.billTo)) return false;
        return Objects.equals(orders, customer.orders);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (shipping != null ? shipping.hashCode() : 0);
        result = 31 * result + (billTo != null ? billTo.hashCode() : 0);
        return result;
    }
}
