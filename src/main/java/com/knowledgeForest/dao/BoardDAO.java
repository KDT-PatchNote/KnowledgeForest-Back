package com.knowledgeForest.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.knowledgeForest.config.MyBatisConfig;
import com.knowledgeForest.dto.BoardDTO;
import com.knowledgeForest.dto.BoardUserDTO;

public class BoardDAO {

    public SqlSession sqlsession;

    public BoardDAO() {
        sqlsession = MyBatisConfig.getSqlSessionFactory().openSession(true);
    }

    // 게시글 목록 조회
    public List<BoardUserDTO> getBoard() {
    	System.out.println("boardListDao______________________");
        List<BoardUserDTO> boardList = sqlsession.selectList("BoardMapper.getBoardList");
        return boardList;
    }

	//	해당 게시글 상세 조회
	public BoardUserDTO selectBoard(int boardNum) {
		return sqlsession.selectOne("BoardMapper.selectBoard", boardNum);
	}
	// 등록
	public void uploadBoard(BoardDTO BoardDTO) {
		sqlsession.insert("BoardMapper.uploadBoard");
	}
	
}