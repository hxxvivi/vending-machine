package com.techelevator;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class SalesReportFile {


	public static void writeReportEntry(List<VendingItem> c) {

		try {
			File file = new File("Sales Report.txt");

			if(!file.exists()) {

				file.createNewFile();
			}
			double salesTotal = 0.00;

			PrintWriter pw = new PrintWriter(file);
			for(int i = 0; i < c.size(); i++) {
				pw.println(c.get(i).getName() + " | " + c.get(i).getQuantity());
				salesTotal += c.get(i).getPrice();
			}
			pw.println("**TOTAL SALES** " + " $" + salesTotal);
			pw.flush();
			pw.close();
		}catch (IOException e) {
			e.printStackTrace();
		}


	}
}







