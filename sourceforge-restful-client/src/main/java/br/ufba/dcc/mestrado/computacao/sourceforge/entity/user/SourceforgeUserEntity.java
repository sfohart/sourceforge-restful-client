package br.ufba.dcc.mestrado.computacao.sourceforge.entity.user;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import br.ufba.dcc.mestrado.computacao.sourceforge.entity.SourceforgeBaseEntity;

@Entity
@Table(name = SourceforgeUserEntity.NODE_NAME)
public class SourceforgeUserEntity extends SourceforgeBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7352870885203552547L;

	public final static String NODE_NAME = "sourceforge_user";

	@Column(name = "disable_ads")
	private String disableAds;

	@Column(name = "user_language")
	private String language;

	@Column(name = "last_login")
	private Long lastLogin;

	@Column(name = "fullname")
	private String name;

	@Column(name = "page_entries")
	private Long pageEntries;

	@ManyToMany
	@JoinTable(name = "sourceforge_user_project_info")
	private List<SourceforgeUserProjectEntity> projects;

	@Column(name = "query_logger")
	private String queryLogger;

	@Column(name = "registered")
	private Long registered;

	@Column(name = "sf_email")
	private String sourceforgeEmail;

	@Column(name = "status")
	private String status;

	@Column(name = "timezone")
	private String timezone;

	@Column(name = "username")
	private String username;

	@Column(name = "preferred_mirror")
	private String preferredMirror;

	public String getDisableAds() {
		return disableAds;
	}

	public void setDisableAds(String disableAds) {
		this.disableAds = disableAds;
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

	public List<SourceforgeUserProjectEntity> getProjects() {
		return projects;
	}

	public void setProjects(List<SourceforgeUserProjectEntity> projects) {
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
