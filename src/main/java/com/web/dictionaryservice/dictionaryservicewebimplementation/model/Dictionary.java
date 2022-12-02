package com.web.dictionaryservice.dictionaryservicewebimplementation.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dictionary", uniqueConstraints = {
        @UniqueConstraint(columnNames = "key")
})
@Getter
@NoArgsConstructor
public class Dictionary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    ///TODO Remake for complex keys
    private String key;

    @JoinTable(name = "users", joinColumns = {@JoinColumn(name = "user_id")})
    private long user_id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "dictionary_values",
            joinColumns = @JoinColumn(name = "dictionary_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "value_id"))
    private List<Value> values = new ArrayList<>();

    public Dictionary(String key, long user_id) {
        this.key = key;
        this.user_id = user_id;
    }
}
