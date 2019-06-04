package service.impl;



import java.util.*;

import dao.AnswerDAO;
import service.AnswerManager;

/**
 * 
 */
public class AnswerManagerImpl implements AnswerManager{

	private AnswerDAO answerDAO;
    
    public AnswerManagerImpl() {
    }

	@Override
	public void uploadAnswer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkAnswer() {
		// TODO Auto-generated method stub
		
	}

	public AnswerDAO getAnswerDAO() {
		return answerDAO;
	}

	public void setAnswerDAO(AnswerDAO answerDAO) {
		this.answerDAO = answerDAO;
	}
	
	

}