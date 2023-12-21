package com.BLI.Domain;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest {

    private String email;
    private String password;

}
