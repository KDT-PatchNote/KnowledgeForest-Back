package com.knowledgeForest.controller.login;

import java.util.HashMap;
import java.util.Random;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class SmsService {
	
	private static final String API_KEY = ""; 
	private static final String API_SECRET = "";
	private static final String FROM_NUMBER = "";
	
	public String sendVerificationSms(String to) throws CoolsmsException{
		System.out.println("========= SmsService =========");
//		Message coolsms = new Message(API_KEY, API_SECRET);
		String verificationCode =  "408877";
		
		HashMap<String, String> params = new HashMap<>();
		params.put("to", to);
		params.put("from", FROM_NUMBER);
		params.put("type","SMS");
		params.put("text", "인증번호는 [" + verificationCode + "] 입니다.");
		
//		JSONObject obj = (JSONObject)coolsms.send(params);
		System.out.println("+++++문자발송 완료 +++++");
		System.out.println("생성된 인증번호 : " + verificationCode);
//		System.out.println(obj.toString());
		return verificationCode;
	}
	
//	인증번호 6자리 생성하는 메소드
	private String generateVerificationCode() {
		Random random = new Random();
		StringBuilder code = new StringBuilder();
		for(int i = 0 ; i<6; i++) {
			code.append(random.nextInt(10));
			
		}
		return code.toString();
	}
	
	
	
	
}
