package com.techelevator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LogTheLogger {

	
	File logFile = new File("Log File.txt");
	DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	Date dateobj = new Date();
	String action = null;
	BigDecimal transactionBalance = null;
	BigDecimal machineBalance = null;

	public void logMethod(String action, BigDecimal transactionBalance, BigDecimal machineBalance) throws IOException {
		logFile.createNewFile();
		Calendar calobj = Calendar.getInstance();

		try (PrintWriter writer = new PrintWriter(new FileWriter(logFile, true))) {
			this.action = action;
			this.transactionBalance = transactionBalance;
			this.machineBalance = machineBalance;
			
			writer.println(df.format(calobj.getTime()) + " " + action + " : $" + transactionBalance + "     " + machineBalance + "\n");
			writer.flush();
			//writer.close();
		}
	}
}
