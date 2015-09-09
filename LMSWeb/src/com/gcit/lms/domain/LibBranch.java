package com.gcit.lms.domain;

import java.lang.reflect.Method;

public class LibBranch {
	
	private int libraryBranchId;
	private String libraryBranchName;
	private String libraryBranchAddress;
	
	
	
	public String getLibraryBranchAddress() {
		return libraryBranchAddress;
	}

	public void setLibraryBranchAddress(String libraryBranchAdress) {
		this.libraryBranchAddress = libraryBranchAdress;
	}

	public int getLibraryBranchId() {
		return libraryBranchId;
	}
	
	public void setLibraryBranchId(int libraryBranchId) {
		this.libraryBranchId = libraryBranchId;
	}
	
	public String getLibraryBranchName() {
		return libraryBranchName;
	}
	
	public void setLibraryBranchName(String libraryBranchName) {
		this.libraryBranchName = libraryBranchName;
	}
	
	public static void main(String[] args){
		try{
			String className = "com.gcit.lms.domain.LibraryBranch";
			String attrib = "LibraryBranchName";
			
			LibBranch a = new LibBranch();
			Class c = Class.forName(className);
			Object obj = c.newInstance();
			
			a.setLibraryBranchName("test");
			Method setter = c.getMethod("set"+attrib.substring(0,1).toUpperCase()+attrib.substring(1, attrib.length()), String.class);
			setter.invoke(obj, "test");
			
			System.out.println(a.getLibraryBranchId());
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
}
