package com.web.dictionaryservice.dictionaryservicewebimplementation.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "value")
@Getter
@Setter
@NoArgsConstructor
public class Value {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    public Value(String value) {
        this.value = value;
    }
}
