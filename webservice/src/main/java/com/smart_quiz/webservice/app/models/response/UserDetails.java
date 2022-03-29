package com.smart_quiz.webservice.app.models.response;

import lombok.Data;

@Data
public class UserDetails {

	private Long userId;
	private String userName;
	private String firstName;
	private String lastName;

	public UserDetails(Long userId, String userName, String firstName, String lastName) {
		this.userId = userId;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
	}
}
