package com.knowledgeForest.controller.login;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.knowledgeForest.Execute;
import com.knowledgeForest.Result;

public class VerifyCodeController implements Execute{
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		System.out.println("========= VerifyCodeController =========");
		BufferedReader reader = request.getReader(); //getReader()는 HTTP 요청 본문을 읽을 수 있는 BufferedReader 반환
		String line = reader.readLine(); //JSON 데이터를 줄단위로 불러옴
		JsonElement jsonElement = JsonParser.parseString(line);	// 문자열 형태의 JSON을 JsonElement로 변환
		JsonObject jsonObject = jsonElement.getAsJsonObject();	// JsonObject 형태로 변환하여 key-value값을 쉽게 추출하도록 함
		
//		클라이언트로부터 받은 인증번호 저장
		String userCode = jsonObject.get("code").getAsString();	// code 필드 값을 string으로 가져옴
		System.out.println("입력받은 code : "+ userCode); //최종적으로 userCode에는 클라이언트가 보낸 인증번호가 저장됨
		
		HttpSession session = request.getSession();
		
		//세션에 저장된 인증번호
		String sessionCode = (String) session.getAttribute("verificationCode"); //"verificationCode"
		System.out.println("sessionCode : " + sessionCode);
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		
		//인증번호 일치 여부 검사
		if(sessionCode != null && sessionCode.equals(userCode)) {
			response.getWriter().write("{\"success\" : " + true + "}");
			System.out.println("인증 성공");
			
		}else {
			response.getWriter().write("{\"success\" : " + false + " } ");
			System.out.println("인증실패");
		}
		
		return null;
	}
}
