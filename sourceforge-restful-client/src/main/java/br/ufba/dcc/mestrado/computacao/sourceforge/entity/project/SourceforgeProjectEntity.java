
package br.ufba.dcc.mestrado.computacao.sourceforge.entity.project;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.ufba.dcc.mestrado.computacao.sourceforge.entity.SourceforgeBaseEntity;

@Entity
@Table( name = SourceforgeProjectEntity.NODE_NAME)
public class SourceforgeProjectEntity extends SourceforgeBaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1326972589929575844L;

	public final static String NODE_NAME = "project";
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="svn_repository_id", referencedColumnName="id")
   	private SourceforgeSVNRepositoryEntity svnRepository;
   	
	@ManyToMany(cascade=CascadeType.ALL)	
   	private List<SourceforgeAudienceEntity> audiences;
	
	@Column(name = "base_url")
   	private String baseUrl;
	
	@ManyToMany(cascade=CascadeType.ALL)	
   	private List<SourceforgeCategoryEntity> categories;
	
	@Column(name = "created")
   	private String created;
   	
   	@Column(name = "created_timestamp")
   	private Long createdTimestamp;
   	
   	@ManyToMany(cascade=CascadeType.ALL)
   	private List<SourceforgeDatabaseEntity> databases;
   	
   	@Column(name = "description")
   	private String description;
   	
   	@ManyToMany(cascade=CascadeType.ALL)
   	@JoinTable(name="project_developers")
   	private List<SourceforgeDeveloperEntity> developers;
   	
   	@Column(name = "donation")
   	private SourceforgeDonationEntity donation;
   	
   	@Column(name = "download_page")
   	private String downloaPage;
   	
   	@ManyToMany(cascade=CascadeType.ALL)   	
   	private List<SourceforgeEnvironmentEntity> environments;
   	
   	@Column(name = "homepage")
   	private String homepage;
   	
   	
   	@ManyToMany(cascade=CascadeType.ALL)
   	private List<SourceforgeLicenseEntity> licenses;
   	
   	@Column(name = "mailing_list")
   	private String mailingList;
   	
   	@ManyToMany(cascade=CascadeType.ALL)
   	@JoinTable(name="project_maintainers")
   	private List<SourceforgeDeveloperEntity> maintainers;
   	
   	@Column(name = "name")
   	private String name;
   	
   	@ManyToMany(cascade=CascadeType.ALL)
   	private List<SourceforgeOSEntity> os;
   	
   	@Column(name = "percentile")
   	private Double percentile;
   	
   	@Column(name = "private")
   	private Number privateNumber;
   	
   	@ManyToMany(cascade=CascadeType.ALL)   	
   	private List<SourceforgeProgrammingLanguageEntity> programmingLanguages;
   	
   	@Column(name = "ranking")
   	private Double ranking;
   	
   	@Column(name = "shortdesc")
   	private String shortDesc;
   	
   	@Column(name = "status")
   	private String status;
   	
   	@Column(name = "summary_page")
   	private String summaryPage;
   	
   	@Column(name = "support_page")
   	private String supportPage;
   	
   	@ManyToMany(cascade=CascadeType.ALL)
   	private List<SourceforgeTopicEntity> topics;
   	
   	@OneToMany(cascade=CascadeType.ALL)
   	private List<SourceforgeTrackerEntity> trackers;
   	
   	@Column(name = "type")
   	private Number typeNumber;

	public SourceforgeSVNRepositoryEntity getSvnRepository() {
		return svnRepository;
	}

	public void setSvnRepository(SourceforgeSVNRepositoryEntity svnRepository) {
		this.svnRepository = svnRepository;
	}

	public List<SourceforgeAudienceEntity> getAudiences() {
		return audiences;
	}

	public void setAudiences(List<SourceforgeAudienceEntity> audiences) {
		this.audiences = audiences;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public List<SourceforgeCategoryEntity> getCategories() {
		return categories;
	}

	public void setCategories(List<SourceforgeCategoryEntity> categories) {
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

	public List<SourceforgeDatabaseEntity> getDatabases() {
		return databases;
	}

	public void setDatabases(List<SourceforgeDatabaseEntity> databases) {
		this.databases = databases;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<SourceforgeDeveloperEntity> getDevelopers() {
		return developers;
	}

	public void setDevelopers(List<SourceforgeDeveloperEntity> developers) {
		this.developers = developers;
	}

	public SourceforgeDonationEntity getDonation() {
		return donation;
	}

	public void setDonation(SourceforgeDonationEntity donation) {
		this.donation = donation;
	}

	public String getDownloaPage() {
		return downloaPage;
	}

	public void setDownloaPage(String downloaPage) {
		this.downloaPage = downloaPage;
	}

	public List<SourceforgeEnvironmentEntity> getEnvironments() {
		return environments;
	}

	public void setEnvironments(List<SourceforgeEnvironmentEntity> environments) {
		this.environments = environments;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public List<SourceforgeLicenseEntity> getLicenses() {
		return licenses;
	}

	public void setLicenses(List<SourceforgeLicenseEntity> licenses) {
		this.licenses = licenses;
	}

	public String getMailingList() {
		return mailingList;
	}

	public void setMailingList(String mailingList) {
		this.mailingList = mailingList;
	}

	public List<SourceforgeDeveloperEntity> getMaintainers() {
		return maintainers;
	}

	public void setMaintainers(List<SourceforgeDeveloperEntity> maintainers) {
		this.maintainers = maintainers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SourceforgeOSEntity> getOs() {
		return os;
	}

	public void setOs(List<SourceforgeOSEntity> os) {
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

	public List<SourceforgeProgrammingLanguageEntity> getProgrammingLanguages() {
		return programmingLanguages;
	}

	public void setProgrammingLanguages(List<SourceforgeProgrammingLanguageEntity> programmingLanguages) {
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

	public List<SourceforgeTopicEntity> getTopics() {
		return topics;
	}

	public void setTopics(List<SourceforgeTopicEntity> topics) {
		this.topics = topics;
	}

	public List<SourceforgeTrackerEntity> getTrackers() {
		return trackers;
	}

	public void setTrackers(List<SourceforgeTrackerEntity> trackers) {
		this.trackers = trackers;
	}

	public Number getTypeNumber() {
		return typeNumber;
	}

	public void setTypeNumber(Number typeNumber) {
		this.typeNumber = typeNumber;
	}
   	
   	
}
