
package br.ufba.dcc.mestrado.computacao.sourceforge.data.project;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias(SourceforgeProject.NODE_NAME)
public class SourceforgeProject{
	
	public final static String NODE_NAME = "Project";
	
	@XStreamAlias("SVNRepository")
   	private SourceforgeSVNRepository svnRepository;
   	
	@XStreamImplicit(itemFieldName = "audiences")
   	private List<String> audiences;
	
	@XStreamAlias("base_url")
   	private String baseUrl;
	
	@XStreamImplicit(itemFieldName = "categories")
   	private List<String> categories;
	
	@XStreamAlias("created")
   	private String created;
   	
   	@XStreamAlias("created_timestamp")
   	private Long createdTimestamp;
   	
   	@XStreamImplicit(itemFieldName = "databases")
   	private List<String> databases;
   	
   	@XStreamAlias("description")
   	private String description;
   	
   	@XStreamImplicit(itemFieldName = "developers")
   	private List<SourceforgeDeveloper> developers;
   	
   	@XStreamAlias("donation")
   	private SourceforgeDonation donation;
   	
   	@XStreamAlias("download-page")
   	private String downloaPage;
   	
   	@XStreamImplicit(itemFieldName = "environments")
   	private List<String> environments;
   	
   	@XStreamAlias("homepage")
   	private String homepage;
   	
   	@XStreamAlias("id")
   	private Long id;
   	
   	@XStreamImplicit(itemFieldName ="licenses")
   	private List<SourceforgeLicense> licenses;
   	
   	@XStreamAlias("mailing-list")
   	private String mailingList;
   	
   	@XStreamImplicit(itemFieldName = "maintainers")
   	private List<SourceforgeDeveloper> maintainers;
   	
   	@XStreamAlias("name")
   	private String name;
   	
   	@XStreamAlias("os")
   	private List<String> os;
   	
   	@XStreamAlias("percentile")
   	private Double percentile;
   	
   	@XStreamAlias("private")
   	private Number privateNumber;
   	
   	@XStreamImplicit(itemFieldName = "programming-languages")
   	private List<String> programmingLanguages;
   	
   	@XStreamAlias("ranking")
   	private Double ranking;
   	
   	@XStreamAlias("shortdesc")
   	private String shortDesc;
   	
   	@XStreamAlias("status")
   	private String status;
   	
   	@XStreamAlias("summary-page")
   	private String summaryPage;
   	
   	@XStreamAlias("support-page")
   	private String supportPage;
   	
   	@XStreamImplicit(itemFieldName = "topics")
   	private List<String> topics;
   	
   	@XStreamImplicit(itemFieldName = "trackers")
   	private List<SourceforgeTracker> trackers;
   	
   	@XStreamAlias("type")
   	private Number typeNumber;

	public SourceforgeSVNRepository getSvnRepository() {
		return svnRepository;
	}

	public void setSvnRepository(SourceforgeSVNRepository svnRepository) {
		this.svnRepository = svnRepository;
	}

	public List<String> getAudiences() {
		return audiences;
	}

	public void setAudiences(List<String> audiences) {
		this.audiences = audiences;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public Long getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Long createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public List<String> getDatabases() {
		return databases;
	}

	public void setDatabases(List<String> databases) {
		this.databases = databases;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<SourceforgeDeveloper> getDevelopers() {
		return developers;
	}

	public void setDevelopers(List<SourceforgeDeveloper> developers) {
		this.developers = developers;
	}

	public SourceforgeDonation getDonation() {
		return donation;
	}

	public void setDonation(SourceforgeDonation donation) {
		this.donation = donation;
	}

	public String getDownloaPage() {
		return downloaPage;
	}

	public void setDownloaPage(String downloaPage) {
		this.downloaPage = downloaPage;
	}

	public List<String> getEnvironments() {
		return environments;
	}

	public void setEnvironments(List<String> environments) {
		this.environments = environments;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<SourceforgeLicense> getLicenses() {
		return licenses;
	}

	public void setLicenses(List<SourceforgeLicense> licenses) {
		this.licenses = licenses;
	}

	public String getMailingList() {
		return mailingList;
	}

	public void setMailingList(String mailingList) {
		this.mailingList = mailingList;
	}

	public List<SourceforgeDeveloper> getMaintainers() {
		return maintainers;
	}

	public void setMaintainers(List<SourceforgeDeveloper> maintainers) {
		this.maintainers = maintainers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getOs() {
		return os;
	}

	public void setOs(List<String> os) {
		this.os = os;
	}

	public Double getPercentile() {
		return percentile;
	}

	public void setPercentile(Double percentile) {
		this.percentile = percentile;
	}

	public Number getPrivateNumber() {
		return privateNumber;
	}

	public void setPrivateNumber(Number privateNumber) {
		this.privateNumber = privateNumber;
	}

	public List<String> getProgrammingLanguages() {
		return programmingLanguages;
	}

	public void setProgrammingLanguages(List<String> programmingLanguages) {
		this.programmingLanguages = programmingLanguages;
	}

	public Double getRanking() {
		return ranking;
	}

	public void setRanking(Double ranking) {
		this.ranking = ranking;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSummaryPage() {
		return summaryPage;
	}

	public void setSummaryPage(String summaryPage) {
		this.summaryPage = summaryPage;
	}

	public String getSupportPage() {
		return supportPage;
	}

	public void setSupportPage(String supportPage) {
		this.supportPage = supportPage;
	}

	public List<String> getTopics() {
		return topics;
	}

	public void setTopics(List<String> topics) {
		this.topics = topics;
	}

	public List<SourceforgeTracker> getTrackers() {
		return trackers;
	}

	public void setTrackers(List<SourceforgeTracker> trackers) {
		this.trackers = trackers;
	}

	public Number getTypeNumber() {
		return typeNumber;
	}

	public void setTypeNumber(Number typeNumber) {
		this.typeNumber = typeNumber;
	}
   	
   	
}
