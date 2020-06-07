package com.nirvasoft.fi.util;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;



public class PrintFile implements Printable {
	public static String pwd = "";
	public void printPassword(String password) {
		// TODO Auto-generated method stub
		PrintFile thistest = new PrintFile();
		pwd = password;
		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPrintable(thistest);
		boolean ok = job.printDialog();
		
		if (ok) {
			try {
				job.print();
			} catch (PrinterException ex) {
				/* The job did not successfully complete */
			}
		}
	}

	public int print(Graphics g, PageFormat pf, int page) throws PrinterException {

		if (page > 0) {
			return NO_SUCH_PAGE;
		}
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(pf.getImageableX(), pf.getImageableY());
		g.drawString( pwd , 20, 50);

		return PAGE_EXISTS;
	}

}
