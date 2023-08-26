package otus.crm.model;

import lombok.*;

import javax.persistence.*;

import lombok.*;
import javax.persistence.*;
import java.util.Objects;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "phone")
public class Phone implements Cloneable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phone_id")
    private Long id;

    @Column(name = "number")
    private String number;

    public Phone(Long id, String number) {
        this.id = id;
        this.number = number;
    }
}