package com.atmproject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.atmproject.main.dao.AtmFunc;

public class App 
{
	
	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	
    public static void main( String[] args ) throws IOException, ClassNotFoundException, SQLException
    {
		System.out.println("************** WELCOME TO ATM ***************");
		boolean status = false;
		do {
			System.out.print("\t\t  User Name : ");
			String userName = bufferedReader.readLine();
			System.out.println("");
			System.out.print("\t\t please Enter Your Pin Number: ");
			String pin = bufferedReader.readLine();
			System.out.println(" ");
			status = AtmFunc.login(userName, pin);

			if (status) {
				do {
					System.out.println(" TRICHY BRANCH");
					System.out.println(" ");
					System.out.println("**** MENU ****");
					System.out.println(" ");
					System.out.println("\t\t  Select 1-> TO Withdraw ");
					System.out.println("\t\t  Select 2-> To Deposit");
					System.out.println("\t\t  Select 3-> To Check Balance ");
				    System.out.println("\t\t  Select 4-> Exit"); 
					System.out.println("  ");
					System.out.println("\t\t  Enter a valid Button:");
					
					
					int choice = Integer.parseInt(bufferedReader.readLine());

					switch (choice) {
					case 1:
						System.out.println("Enter Account Number:");
						long accountId = Integer.parseInt(bufferedReader.readLine());
						System.out.println("Enter amount to withdraw:");
						double withdrawalAmount = Double.parseDouble(bufferedReader.readLine());
						double result = AtmFunc.withdraw(accountId, withdrawalAmount);

						if (result == 0) {
							System.out.println("Insufficient Balance");
							System.out.println("Check Account Balance");							
							System.out.println("Transaction is unsuccessfull");
						} else {
							System.out.println("Transaction successfull");
							System.out.println("Available Balance:" + result);
						}
						break;

					case 2:
						System.out.println("Enter Account No:");
						accountId = Integer.parseInt(bufferedReader.readLine());
						System.out.println("Enter Deposit Amount:");
						double depositAmount = Double.parseDouble(bufferedReader.readLine());
						result = AtmFunc.deposit(accountId, depositAmount);

						if (result == 0) {

							System.out.println("Transaction is unsuccess");
							System.out.println("Try again");
						} else {
							System.out.println("Transaction success");
							System.out.println("Available Balance :" + result);
						}
						break;
						
					case 3:
						System.out.println("Enter Account Number:");
						accountId = Integer.parseInt(bufferedReader.readLine());
						System.out.println("Current Available Balance is :" + AtmFunc.balanceCheck(accountId));
						break;

					case 4:
						status = false;
						System.out.println(" ");
						System.out.println("Exit successfully");
						System.out.println(" ");
						break;

					default:
						
						System.out.println("Please select the correct number");
						
					}

				} while (status);
			}

			else {				
				System.out.println("Incorrect User Name or pincode");				
			}
		} while (status);
	}

    }


