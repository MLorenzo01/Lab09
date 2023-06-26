package it.polito.tdp.borders.model;

public class Border {
	private int codeA;
	private int codeB;
	public Border(int codeA, int codeB) {
		this.codeA = codeA;
		this.codeB = codeB;
	}
	public int getCodeA() {
		return codeA;
	}
	public void setCodeA(int codeA) {
		this.codeA = codeA;
	}
	public int getCodeB() {
		return codeB;
	}
	public void setCodeB(int codeB) {
		this.codeB = codeB;
	}
	@Override
	public String toString() {
		return "Border [codeA=" + codeA + ", codeB=" + codeB + "]";
	}
	
	

}
