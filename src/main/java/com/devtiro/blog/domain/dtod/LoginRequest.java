package com.devtiro.blog.domain.dtod;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {
    private String email;
    private String password;
}
