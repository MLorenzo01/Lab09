package it.polito.tdp.borders.model;

public class Country {
	private int code;
	private String abbr;
	private String nome;
	
	public Country(int code, String abbr, String nome) {
		this.code = code;
		this.abbr = abbr;
		this.nome = nome;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Country [code=" + code + ", abbr=" + abbr + ", nome=" + nome + "]";
	}
}
