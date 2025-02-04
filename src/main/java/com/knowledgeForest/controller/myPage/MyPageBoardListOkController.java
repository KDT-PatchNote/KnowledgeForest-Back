package com.knowledgeForest.controller.myPage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.knowledgeForest.Execute;
import com.knowledgeForest.Result;
import com.knowledgeForest.dao.MyPageDAO;
import com.knowledgeForest.dto.BoardDTO;

public class MyPageBoardListOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MyPageDAO mypageDAO = new MyPageDAO();
		Result result = new Result();

		HttpSession session = request.getSession(false); // 현재 세션 가져오기

		int userNum = (int) session.getAttribute("userNumber");

		List<BoardDTO> boardList = null;

		boardList = mypageDAO.getBoardList(userNum);
		System.out.println("boardList : " + boardList);
		request.setAttribute("boardList", boardList);
		result.setPath("/html/mypage/mypage-writtenboard.jsp");
		result.setRedirect(false);

		return result;
	}

}
