package com.web.dictionaryservice.dictionaryservicewebimplementation.pojo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.util.Set;

@Getter
@Setter
public class SignupRequest {
    @Nullable
    private String username;
    @Nullable
    private String email;

    private Set<String> roles;
    private String password;
}
