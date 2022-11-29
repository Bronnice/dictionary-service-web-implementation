package com.web.dictionaryservice.dictionaryservicewebimplementation.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;

@Entity
@Table(name = "Dictionary")
@Getter
public class Dictionary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private ArrayList<String> value;
    private long user_id;
}
