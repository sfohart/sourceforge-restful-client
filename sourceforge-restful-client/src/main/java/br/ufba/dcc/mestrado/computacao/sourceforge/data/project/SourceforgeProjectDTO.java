
package br.ufba.dcc.mestrado.computacao.sourceforge.data.project;

import java.util.List;

import br.ufba.dcc.mestrado.computacao.sourceforge.data.SourceforgeDTO;
import br.ufba.dcc.mestrado.computacao.xstream.converters.NullableDoubleConverter;
import br.ufba.dcc.mestrado.computacao.xstream.converters.NullableLongConverter;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias(SourceforgeProjectDTO.NODE_NAME)
public class SourceforgeProjectDTO implements SourceforgeDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6400624151546879027L;

	public final static String NODE_NAME = "Project";
	
	@XStreamAlias("SVNRepository")
   	private SourceforgeSVNRepositoryDTO svnRepository;
   	
	@XStreamImplicit(itemFieldName = "audiences")
   	private List<SourceforgeAudienceDTO> audiences;
	
	@XStreamAlias("base_url")
   	private String baseUrl;
	
	@XStreamImplicit(itemFieldName = "categories")
   	private List<SourceforgeCategoryDTO> categories;
	
	@XStreamAlias("created")
   	private String created;
   	
   	@XStreamAlias("created_timestamp")
   	@XStreamConverter(value = NullableLongConverter.class)
   	private Long createdTimestamp;
   	
   	@XStreamImplicit(itemFieldName = "databases")
   	private List<SourceforgeDatabaseDTO> databases;
   	
   	@XStreamAlias("description")
   	private String description;
   	
   	@XStreamImplicit(itemFieldName = "developers")
   	private List<SourceforgeDeveloperDTO> developers;
   	
   	@XStreamAlias("donation")
   	private SourceforgeDonationDTO donation;
   	
   	@XStreamAlias("download-page")
   	private String downloadPage;
   	
   	@XStreamImplicit(itemFieldName = "environments")
   	private List<SourceforgeEnvironmentDTO> environments;
   	
   	@XStreamAlias("homepage")
   	private String homepage;
   	
   	@XStreamAlias("id")
   	@XStreamConverter(value = NullableLongConverter.class)
   	private Long id;
   	
   	@XStreamImplicit(itemFieldName ="licenses")
   	private List<SourceforgeLicenseDTO> licenses;
   	
   	@XStreamAlias("mailing-list")
   	private String mailingList;
   	
   	@XStreamImplicit(itemFieldName = "maintainers")
   	private List<SourceforgeDeveloperDTO> maintainers;
   	
   	@XStreamAlias("name")
   	private String name;
   	
   	@XStreamAlias("os")
   	private List<SourceforgeOSDTO> os;
   	
   	@XStreamAlias("percentile")
   	@XStreamConverter(value = NullableDoubleConverter.class)
   	private Double percentile;
   	
   	@XStreamAlias("private")
   	@XStreamConverter(value = NullableLongConverter.class)
   	private Long privateNumber;
   	
   	@XStreamImplicit(itemFieldName = "programming-languages")
   	private List<SourceforgeProgrammingLanguageDTO> programmingLanguages;
   	
   	@XStreamAlias("ranking")
   	@XStreamConverter(value = NullableDoubleConverter.class)
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
   	private List<SourceforgeTopicDTO> topics;
   	
   	@XStreamImplicit(itemFieldName = "trackers")
   	private List<SourceforgeTrackerDTO> trackers;
   	
   	@XStreamAlias("type")
   	@XStreamConverter(value = NullableLongConverter.class)
   	private Long typeNumber;

	public SourceforgeSVNRepositoryDTO getSvnRepository() {
		return svnRepository;
	}

	public void setSvnRepository(SourceforgeSVNRepositoryDTO svnRepository) {
		this.svnRepository = svnRepository;
	}

	public List<SourceforgeAudienceDTO> getAudiences() {
		return audiences;
	}

	public void setAudiences(List<SourceforgeAudienceDTO> audiences) {
		this.audiences = audiences;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public List<SourceforgeCategoryDTO> getCategories() {
		return categories;
	}

	public void setCategories(List<SourceforgeCategoryDTO> categories) {
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

	public List<SourceforgeDatabaseDTO> getDatabases() {
		return databases;
	}

	public void setDatabases(List<SourceforgeDatabaseDTO> databases) {
		this.databases = databases;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<SourceforgeDeveloperDTO> getDevelopers() {
		return developers;
	}

	public void setDevelopers(List<SourceforgeDeveloperDTO> developers) {
		this.developers = developers;
	}

	public SourceforgeDonationDTO getDonation() {
		return donation;
	}

	public void setDonation(SourceforgeDonationDTO donation) {
		this.donation = donation;
	}

	public String getDownloadPage() {
		return downloadPage;
	}

	public void setDownloadPage(String downloadPage) {
		this.downloadPage = downloadPage;
	}

	public List<SourceforgeEnvironmentDTO> getEnvironments() {
		return environments;
	}

	public void setEnvironments(List<SourceforgeEnvironmentDTO> environments) {
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

	public List<SourceforgeLicenseDTO> getLicenses() {
		return licenses;
	}

	public void setLicenses(List<SourceforgeLicenseDTO> licenses) {
		this.licenses = licenses;
	}

	public String getMailingList() {
		return mailingList;
	}

	public void setMailingList(String mailingList) {
		this.mailingList = mailingList;
	}

	public List<SourceforgeDeveloperDTO> getMaintainers() {
		return maintainers;
	}

	public void setMaintainers(List<SourceforgeDeveloperDTO> maintainers) {
		this.maintainers = maintainers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SourceforgeOSDTO> getOs() {
		return os;
	}

	public void setOs(List<SourceforgeOSDTO> os) {
		this.os = os;
	}

	public Double getPercentile() {
		return percentile;
	}

	public void setPercentile(Double percentile) {
		this.percentile = percentile;
	}

	public Long getPrivateNumber() {
		return privateNumber;
	}

	public void setPrivateNumber(Long privateNumber) {
		this.privateNumber = privateNumber;
	}

	public List<SourceforgeProgrammingLanguageDTO> getProgrammingLanguages() {
		return programmingLanguages;
	}

	public void setProgrammingLanguages(List<SourceforgeProgrammingLanguageDTO> programmingLanguages) {
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

	public List<SourceforgeTopicDTO> getTopics() {
		return topics;
	}

	public void setTopics(List<SourceforgeTopicDTO> topics) {
		this.topics = topics;
	}

	public List<SourceforgeTrackerDTO> getTrackers() {
		return trackers;
	}

	public void setTrackers(List<SourceforgeTrackerDTO> trackers) {
		this.trackers = trackers;
	}

	public Long getTypeNumber() {
		return typeNumber;
	}

	public void setTypeNumber(Long typeNumber) {
		this.typeNumber = typeNumber;
	}
   	
   	
}
