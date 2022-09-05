package giezz.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "client_name")
    private String name;

    @OneToMany(mappedBy = "client")
    private Set<OrderInfo> orders = new HashSet<>();

    public Client(String name) {
        this.name = name;
    }

    public Client() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<OrderInfo> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderInfo> orders) {
        this.orders = orders;
    }
}
