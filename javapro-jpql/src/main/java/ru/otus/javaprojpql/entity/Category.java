package ru.otus.javaprojpql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "CATEGORIES")
@NamedEntityGraph(
        name = "category-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("courseSet")
        }
)
@NoArgsConstructor
@Getter
@Setter
public class Category {

    @Id
    private UUID id;

    @Column(name = "NAME", length = 30, nullable = false)
    private String name;

//    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "category")
//    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private Set<Course> courseSet;

    public Category(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }
}
