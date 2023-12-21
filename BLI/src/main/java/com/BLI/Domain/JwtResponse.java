package com.BLI.Domain;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

        private String jwtToken;
        private String username;

}
