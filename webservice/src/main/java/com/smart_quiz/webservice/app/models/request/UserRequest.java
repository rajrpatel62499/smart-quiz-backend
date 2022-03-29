package com.smart_quiz.webservice.app.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String email;

    private String firstName;

    private String lastName;

    private String authRole;
}
