package giezz.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_info")
public class OrderInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "order_cost")
    private double cost;

    public OrderInfo(Client client, Product product) {
        this.client = client;
        this.product = product;
    }

    public OrderInfo() {
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Заказ номер " + id +
                ", имя клиента =" + client.getName() +
                ", название продукта =" + product.getName() +
                ", стоимость заказа = " + cost +
                "\n";
    }
}
