package com.nirvasoft.fi.shared;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement

public class PagerData {
private int current;
private int prev;
private int last;
private int next;
private int start;
private int end;
private int size;
private int totalcount;

public PagerData(){
	this.current=1;
	this.prev=1;
	this.last=1;
	this.next=1;
	this.start=1;
	this.end=1;
	this.size=1;
    this.totalcount=0;
}

public int getCurrent() { return current; } 
public void setCurrent(int p) {this.current= p;}

public int getPrev() { return prev; } 
public void setPrev(int p) {this.prev= p;}

public int getLast() { return last; } 
public void setLast(int p) {this.last= p;}

public int getNext() { return next; } 
public void setNext(int p) {this.next= p;}

public int getStart() { return start; } 
public void setStart(int p) {this.start= p;}

public int getEnd() { return end; } 
public void setEnd(int p) {this.end= p;}

public int getSize() { return size; } 
public void setSize(int p) {this.size= p;}

public int getTotalCount() { return totalcount; } 
public void setTotalCount(int p) {this.totalcount= p;}
}
