package br.ufba.dcc.mestrado.computacao.sourceforge.data.project;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(SourceforgeProject.NODE_NAME)
public class SourceforgeProject {

	public final static String NODE_NAME = "Project";

	private String name;
	private String descrition;
	private String id;
	
	private Long ranking;
	private String status;
	
	
	@XStreamAlias("shortdesc")
	private String shortDesc;
	
	@XStreamAlias("download-page")
	private String downloadPage;
	
	@XStreamAlias("support-page")
	private String supportPage;
	
	@XStreamAlias("summary-page")
	private String summaryPage;
	
	@XStreamAlias("mailing-list")
	private String mailingList;
	
	private String homepage;
	
	@XStreamAlias("base_url")
	private String baseUrl;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescrition() {
		return descrition;
	}

	public void setDescrition(String descrition) {
		this.descrition = descrition;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getRanking() {
		return ranking;
	}

	public void setRanking(Long ranking) {
		this.ranking = ranking;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getDownloadPage() {
		return downloadPage;
	}

	public void setDownloadPage(String downloadPage) {
		this.downloadPage = downloadPage;
	}

	public String getSupportPage() {
		return supportPage;
	}

	public void setSupportPage(String supportPage) {
		this.supportPage = supportPage;
	}

	public String getSummaryPage() {
		return summaryPage;
	}

	public void setSummaryPage(String summaryPage) {
		this.summaryPage = summaryPage;
	}

	public String getMailingList() {
		return mailingList;
	}

	public void setMailingList(String mailingList) {
		this.mailingList = mailingList;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	
	
	

}
