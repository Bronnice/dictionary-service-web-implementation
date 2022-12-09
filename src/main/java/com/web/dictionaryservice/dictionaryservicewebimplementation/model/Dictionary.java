package com.web.dictionaryservice.dictionaryservicewebimplementation.model;

import com.web.dictionaryservice.dictionaryservicewebimplementation.Validation.Validator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dictionary")
@Getter
@Setter
@NoArgsConstructor
public class Dictionary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JoinTable(name = "users", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    private Long user_id;

    Validator<?> validator;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "keys_in_dictionary",
            joinColumns = @JoinColumn(name = "dictionary_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "key_id", referencedColumnName = "id"))
    private Set<Key> keys = new HashSet<>();

    public Dictionary(String name, Long user_id, Validator<?> validator) {
        this.name = name;
        this.user_id = user_id;
        this.validator = validator;
    }
}
