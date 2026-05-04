package com.paula.checkmc.model;

import java.util.ArrayList;
import java.util.List;

public class Results<T> {

private List<T> page = null;
private int total = 0;



public Results() {	
	
	page  = new ArrayList<T>();
	
}

public int getTotal() {
	return total;
	
}

public void setTotal(int total) {
	this.total = total;
}

public List<T> getPage() {
	return page;
}

public void setPage(List<T> page) {
	this.page = page;
}
	
	
}
