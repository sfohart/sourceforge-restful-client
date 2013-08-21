package br.ufba.dcc.mestrado.computacao.sourceforge.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "crawler_project")
public class SourceforgeCrawlerProjectEntity extends SourceforgeBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7460084877197154384L;

	@Column(name = "project_limit_number")
	private Long limit;

	@Column(name = "project_offset_number")
	private Long offset;
	
	@Column(name = "new_since")
	private Long newSince;
	
	@Column(name = "changed_since")
	private Long changedSince;

	public Long getLimit() {
		return limit;
	}

	public void setLimit(Long limit) {
		this.limit = limit;
	}

	public Long getOffset() {
		return offset;
	}

	public void setOffset(Long offset) {
		this.offset = offset;
	}

	public Long getNewSince() {
		return newSince;
	}

	public void setNewSince(Long newSince) {
		this.newSince = newSince;
	}

	public Long getChangedSince() {
		return changedSince;
	}

	public void setChangedSince(Long changedSince) {
		this.changedSince = changedSince;
	}
	
	

}
