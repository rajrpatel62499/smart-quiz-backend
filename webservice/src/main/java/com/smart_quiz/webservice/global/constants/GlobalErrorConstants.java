package com.smart_quiz.webservice.global.constants;

public class GlobalErrorConstants {
	private GlobalErrorConstants() {
	}

	public static final String BAD_CREDENTIALS = "Wrong username or password.";

	public static final String USER_GOOGLE_LOGIN_NOT_AURTHORIZED = "Invalid Google Login Credentials";
	public static final String INVALID_USERNAME = "Username is Invalid";
	public static final String USERNAME_ALREADY_EXIST = "Username already Exist";
	public static final String INVALID_FORGET_PASSWORD_OTP = "Invalid Forget Password OTP. Please try again with new OTP";
	public static final String INVALID_EMAIL = "OTP cannot be sent.please check emailID is correct!";

	public static final String OTP_NOT_SENT = "Unable to send OTP at this moment.Please try again!";
	public static final String INVALID_OTP = "Invalid Otp! Please Try Again!";

	public static final String QUIZ_DOES_NOT_EXIST = "Quiz does not Exist!";
	public static final String QUIZ_NOT_EXIST = "Quiz does not Exist for the Username!";
	public static final String QUIZ_SETTING_NOT_EXIST = "Quiz Setting doest not Exist!";
	public static final String QUIZ_NOT_PUBLISHED = "Publish the quiz before you share!";
	public static final String QUIZ_HAS_NOT_STARTED = "The quiz has not started yet!";
	public static final String QUIZ_NOT_AVAILABLE = "The quiz is no longer available!";
	public static final String QUIZ_DUPLICATE_ATTEMPT = "The same quiz cannot be taken twice!";

	public static final String QUIZ_QUESTION_NOT_EXIST = "Quiz Question not Exist for UserName!";
	public static final String QUIZ_IMAGE_NOT_EXIST = "Quiz Image not Exist for UserName!";
	public static final String QUIZ_OPTION_NOT_EXIST = "Quiz Option not Exist for UserName!";

	public static final String QUIZ_USER_MAPPING_NOT_EXIST = "User is not registered to Quiz!";
	public static final String INVALID_USERNAME_ID = "User Id is Invalid";

	public static final String ANSWER_MARKING_NOT_FOUND = "Answer Marking Not Found";

	public static final String QUESTION_MARKS_NOT_ASSIGNED = "Question Marks not Assigned";
	public static final String ASSIGNED_MARK_MORE_THAN_QUESTION_MARK = "Assigned Mark more than Question Mark";

	public static final String QUIZ_NO_MAPPING_EXIST = "No Mapping Exist for Quiz!";
	public static final String QUESTION_TYPE_NOT_EXIST = "Question Type not Exist for Question!";

	public static final String USER_ACCOUNT_DELETED = "This username already exists. To log in to your account again, please reset your password";
	public static final String USER_ACCOUNT_EXISTS = "Username already exists.";
	public static final String USER_ACCOUNT_NOT_EXIST = "No such user exists.";

	public static final String EMAIL_EXISTS = "Email is already in use.";

	public static final String OTP_EXPIRED = "OTP has expired.";
	public static final String MOBILE_INVALID = "OTP can not be sent! Check your mobile number!!";

	public static final String UNAUTHORIZED_API_ACCESS = "Action is not authorized.";
	public static final String COMPREHENSION_DOES_NOT_EXIST = "Comprehension does not exist!";
	public static final String QUESTION_DOES_NOT_EXIST = "Question does not exist!";

	public static final String DEFAULT_CLASS_DOES_NOT_EXIST = "No default class exists for the user!";

}
