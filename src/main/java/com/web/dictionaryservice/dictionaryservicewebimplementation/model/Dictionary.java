package com.web.dictionaryservice.dictionaryservicewebimplementation.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "dictionary")
@Getter
@NoArgsConstructor
public class Dictionary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinTable(name = "users", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    private UUID user_id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "keys_in_dictionary",
            joinColumns = @JoinColumn(name = "dictionary_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "key_id", referencedColumnName = "id"))
    private List<Key> keys = new ArrayList<>();

    public Dictionary(UUID user_id) {
        this.user_id = user_id;
    }
}
