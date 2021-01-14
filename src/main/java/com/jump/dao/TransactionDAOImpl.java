package com.jump.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.jump.model.Transaction;
import com.jump.util.ConnectionManager;

public class TransactionDAOImpl implements TransactionDAO{
	
	Connection conn = ConnectionManager.getConnection();
	
	private static final String  INSERT_TRANSACTION = "INSERT INTO TRANSACTION VALUES (null,?,?,?,?)";
	
	private static final String DISPLAY_LASTFIVE_TXNS = "select * from transaction \n"
			                                            + "\n"
			                                            + "where rownum <=5 and userid=?\n"
			                                            + "order by transactionid"	;

	@Override
	public void registerTransaction(Transaction txn) {
		
		try(PreparedStatement pstmt = conn.prepareStatement(INSERT_TRANSACTION)){
			
			pstmt.setInt(1, txn.getUserId());
			pstmt.setInt(2, txn.getAccountId());
			
			Date date = java.sql.Date.valueOf(txn.getDateOfTxn());

			pstmt.setDate(3,date);
			pstmt.setString(4, txn.getTxnType());
			pstmt.executeUpdate();
			//System.out.println ("The Transaction execution output value is ");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}
	
	
	public List<Transaction> displayLastFiveTransactions(int userId) {
		ResultSet rs = null;
		List<Transaction> result = new ArrayList<>();
		
		try(PreparedStatement pstmt = conn.prepareStatement(DISPLAY_LASTFIVE_TXNS)){
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				LocalDate localDate = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate(4)) );
				
				result.add(new Transaction(
						
						rs.getLong(1),
						rs.getInt(2),
						rs.getInt(3),
						localDate,
						rs.getString(5)
						
						));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	} 
	
	

}
