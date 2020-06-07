package com.nirvasoft.fi.util;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class TestPrint implements Printable{
public String password = "123456";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestPrint thistest = new TestPrint();
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
		
		g.drawString(password, 100, 100);

		return PAGE_EXISTS;
	}

}
