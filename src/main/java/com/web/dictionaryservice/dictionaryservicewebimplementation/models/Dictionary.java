package com.web.dictionaryservice.dictionaryservicewebimplementation.models;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "dictionary")
@Getter
public class Dictionary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String value;
    @JoinTable(name = "user", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    private long user_id;
}
