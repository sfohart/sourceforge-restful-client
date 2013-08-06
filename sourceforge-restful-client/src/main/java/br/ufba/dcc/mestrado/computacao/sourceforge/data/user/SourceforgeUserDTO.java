package br.ufba.dcc.mestrado.computacao.sourceforge.data.user;

import java.util.List;

import br.ufba.dcc.mestrado.computacao.sourceforge.data.SourceforgeDTO;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias(SourceforgeUserDTO.NODE_NAME)
public class SourceforgeUserDTO implements SourceforgeDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1163939909479145977L;

	public final static String NODE_NAME = "User";

	@XStreamAlias("disable_ads")
	private String disableAds;

	@XStreamAlias("id")
	private Number id;

	@XStreamAlias("language")
	private String language;

	@XStreamAlias("last_login")
	private Long lastLogin;

	@XStreamAlias("name")
	private String name;

	@XStreamAlias("page_entries")
	private Long pageEntries;

	@XStreamImplicit(itemFieldName = "projects")
	private List<SourceforgeUserProjectDTO> projects;

	@XStreamAlias("query_logger")
	private String queryLogger;

	@XStreamAlias("registered")
	private Long registered;

	@XStreamAlias("sf_email")
	private String sourceforgeEmail;

	@XStreamAlias("status")
	private String status;

	@XStreamAlias("timezone")
	private String timezone;

	@XStreamAlias("username")
	private String username;
	
	@XStreamAlias("preferred_mirror")
	private String preferredMirror;

	public String getDisableAds() {
		return disableAds;
	}

	public void setDisableAds(String disableAds) {
		this.disableAds = disableAds;
	}

	public Number getId() {
		return id;
	}

	public void setId(Number id) {
		this.id = id;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Long getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Long lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPageEntries() {
		return pageEntries;
	}

	public void setPageEntries(Long pageEntries) {
		this.pageEntries = pageEntries;
	}

	public List<SourceforgeUserProjectDTO> getProjects() {
		return projects;
	}

	public void setProjects(List<SourceforgeUserProjectDTO> projects) {
		this.projects = projects;
	}

	public String getQueryLogger() {
		return queryLogger;
	}

	public void setQueryLogger(String queryLogger) {
		this.queryLogger = queryLogger;
	}

	public Long getRegistered() {
		return registered;
	}

	public void setRegistered(Long registered) {
		this.registered = registered;
	}

	public String getSourceforgeEmail() {
		return sourceforgeEmail;
	}

	public void setSourceforgeEmail(String sourceforgeEmail) {
		this.sourceforgeEmail = sourceforgeEmail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPreferredMirror() {
		return preferredMirror;
	}

	public void setPreferredMirror(String preferredMirror) {
		this.preferredMirror = preferredMirror;
	}

}
