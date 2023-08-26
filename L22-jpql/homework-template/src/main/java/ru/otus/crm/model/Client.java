package ru.otus.crm.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "client")
public class Client implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne(fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval = false)
    @JoinColumn(name = "client_id", nullable = false, updatable = false)
    private List<Phone> phone;

    public Client(String name) {
        this.id = null;
        this.name = name;
    }

    public Client(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Client(Long id, String name,Address address,List<Phone> phone) {
        this.id = id;
        this.name = name;
        this.address=address;
        this.phone=phone;
    }

    @Override
    public Client clone() {
        return new Client(this.id, this.name,this.address,this.phone);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
