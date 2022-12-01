package com.atmproject.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.atmproject.main.dao.Mysqlconnection;

public class AtmFunc {
	
	public static boolean login(String userName, String pass) throws ClassNotFoundException, SQLException
	{
		Connection connection= Mysqlconnection.dbconnect();
		PreparedStatement statement=connection.prepareStatement("select * from User where username=?");
		statement.setString(1, userName);
		ResultSet result=statement.executeQuery();
		if(result.next())
		{
			if(result.getString("passcode").equals(pass))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}		
	}

	
	public static double balanceCheck(long accountId) throws ClassNotFoundException, SQLException
	{
		Connection connection= Mysqlconnection.dbconnect();
		
		PreparedStatement statement=connection.prepareStatement("select * from User where accid=?");
		statement.setLong(1, accountId);
		
		
		ResultSet result=statement.executeQuery();
		result.next();
		
		double balance=result.getDouble("balance");
		return balance;	
	}
		
	@SuppressWarnings("removal")
	public static double withdraw(long accountId, double withdrawalAmount) throws ClassNotFoundException, SQLException
	{
		Connection connection= Mysqlconnection.dbconnect();
		PreparedStatement statement=connection.prepareStatement("select * from User where accid=?");
		statement.setLong(1, accountId);
		
		ResultSet result=statement.executeQuery();
		result.next();
		double avilableBalance=result.getDouble("balance");
		
		if(withdrawalAmount<avilableBalance) 
		{
		   double remainingBalance=avilableBalance-withdrawalAmount;
		   statement=connection.prepareStatement("update User set balance=? where accid=?");
		   statement.setDouble(1, new Double(remainingBalance));
		   statement.setLong(2, accountId);
		   
		   if(statement.executeUpdate()>0)
		   {
			   return remainingBalance;  
		   }
		   else
		   {
			   return 0;
		   }
		}
		else
		{
			   return 0;
		}
	}
	
	@SuppressWarnings("removal")
	public static double deposit(long accountId, double depositAmount) throws ClassNotFoundException, SQLException
	{
		Connection connection= Mysqlconnection.dbconnect();
		PreparedStatement statement=connection.prepareStatement("select * from User where accid=?");
		statement.setLong(1, accountId);
		
		ResultSet result=statement.executeQuery();
		result.next();
		double avilableBalance=result.getDouble("balance");
		double newBalance=avilableBalance+depositAmount;
		
		  statement=connection.prepareStatement("update User set balance=? where accid=?");
		   statement.setDouble(1, new Double(newBalance));
		   statement.setLong(2, accountId);
		   
		   if(statement.executeUpdate()>0)
		   {
			   return newBalance;  
		   }
		   else
		   {
			   return 0;
		   }		  
	}
	


}
