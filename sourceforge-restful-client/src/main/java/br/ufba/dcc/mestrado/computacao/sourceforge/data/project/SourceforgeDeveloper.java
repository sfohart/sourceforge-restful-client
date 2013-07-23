package br.ufba.dcc.mestrado.computacao.sourceforge.data.project;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(SourceforgeDeveloper.NODE_NAME)
public class SourceforgeDeveloper {
	
	public final static String NODE_NAME = "developer";
	
	@XStreamAlias("homepage")
	private String homepage;
	
	@XStreamAlias("mbox_sha1sum")
	private String mboxSHA1Sum;
	
	@XStreamAlias("name")
	private String name;

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getMboxSHA1Sum() {
		return mboxSHA1Sum;
	}

	public void setMboxSHA1Sum(String mboxSHA1Sum) {
		this.mboxSHA1Sum = mboxSHA1Sum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
