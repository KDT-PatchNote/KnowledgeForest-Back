package com.knowledgeForest.controller.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.reflection.SystemMetaObject;

import com.knowledgeForest.Result;
import com.knowledgeForest.controller.notice.NoticeDetailOkController;
import com.knowledgeForest.controller.notice.NoticeListOkController;
import com.knowledgeForest.dao.BoardDAO;
import com.knowledgeForest.dto.BoardDTO;

public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      this.doProcess(request, response);
	   }

	   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      this.doProcess(request, response);
	   }
	   
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	      String target = request.getRequestURI().substring(request.getContextPath().length());
	      Result result = null;
	      request.setCharacterEncoding("UTF-8");
	      
	      switch(target) {
	      case "/board/boarddetail.bo":
	    	  result = new BoardDetailOkController().execute(request, response);
	    	  break;
	    	  
	      case "/board/boardlist.bo" :
	    	  result = new BoardListOkController().execute(request, response);
	    	  break;
	      
		case "/board/boardDeleteOk.bo" :
			result = new BoardDeleteOkController().execute(request, response);
			break;
	      
		case "/board/boardWrite.bo" :
			request.getRequestDispatcher("/html/board/boardlist-write.jsp").forward(request, response);
			break;
			
		case "/board/boardWriteOk.bo" :
			result = new BoardWriteOkController().execute(request, response);
			break;
			
		case "/board/boardEdit.bo" :
			result = new BoardEditController().execute(request, response);
			break;

		case "/board/boardEditOk.bo" :
			result = new BoardEditOkController().execute(request, response);
			break;
			
	    case "/board/boardReplyWriteOk.bo":
	    	result = new BoardReplyWriteOkController().execute(request, response);
	    	break;
	    	
	    case "/board/boardReplyListOk.bo":
	    	result = new BoardReplyListOkController().execute(request, response);
	    	break;
	    	
	    case "/board/boardReplyDeleteOk.bo":
	    	System.out.println("댓글 삭제 컨틀롤러 진입");
	    	result = new BoardReplyDeleteOkController().execute(request, response);
	    	break;
	    
	    case "/board/boardReplyUpdateOk.bo":
	    	result = new BoardReaplyUpdateOkController().execute(request, response);
	    	break;
	    	
	}

	      if (result != null) {
	         if (result.isRedirect()) {
	            response.sendRedirect(result.getPath());
	         } else {
	            request.getRequestDispatcher(result.getPath()).forward(request, response);
	         }
	      }
	}
}
