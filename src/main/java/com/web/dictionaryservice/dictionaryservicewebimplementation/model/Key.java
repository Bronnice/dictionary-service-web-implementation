package com.web.dictionaryservice.dictionaryservicewebimplementation.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "keys", uniqueConstraints = {
        @UniqueConstraint(columnNames = "key")
})
@Getter
@Setter
@NoArgsConstructor
public class Key {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String key;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "key_value",
            joinColumns = @JoinColumn(name = "key_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "value_id", referencedColumnName = "id"))
    private Set<Value> values = new HashSet<>();

    public Key(String key) {
        this.key = key;
    }
}
