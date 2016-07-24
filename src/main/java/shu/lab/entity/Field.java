package shu.lab.entity;

import org.apache.struts2.json.annotations.JSON;

import java.util.HashSet;
import java.util.Set;

/**
 * Field entity. @author Jimmy J
 */

public class Field implements java.io.Serializable {

	// Fields

	private Integer fieldId;
	private String fieldDescr;
	private Integer count;
	private Set fieldProjects = new HashSet(0);
	private Set fieldUsers = new HashSet(0);
	private Set fieldPapers = new HashSet(0);

	// Constructors

	/** default constructor */
	public Field() {
	}

	/** full constructor */
	public Field(String fieldDescr, Integer count, Set fieldProjects,
			Set fieldUsers, Set fieldPapers) {
		this.fieldDescr = fieldDescr;
		this.count = count;
		this.fieldProjects = fieldProjects;
		this.fieldUsers = fieldUsers;
		this.fieldPapers = fieldPapers;
	}

	// Property accessors

	public Integer getFieldId() {
		return this.fieldId;
	}

	public void setFieldId(Integer fieldId) {
		this.fieldId = fieldId;
	}

	public String getFieldDescr() {
		return this.fieldDescr;
	}

	public void setFieldDescr(String fieldDescr) {
		this.fieldDescr = fieldDescr;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@JSON(serialize = false)
	public Set getFieldProjects() {
		return this.fieldProjects;
	}

	public void setFieldProjects(Set fieldProjects) {
		this.fieldProjects = fieldProjects;
	}

	@JSON(serialize = false)
	public Set getFieldUsers() {
		return this.fieldUsers;
	}

	public void setFieldUsers(Set fieldUsers) {
		this.fieldUsers = fieldUsers;
	}

	@JSON(serialize = false)
	public Set getFieldPapers() {
		return this.fieldPapers;
	}

	public void setFieldPapers(Set fieldPapers) {
		this.fieldPapers = fieldPapers;
	}

	@Override
	public String toString() {
		return "Field{" +
				"fieldId=" + fieldId +
				", fieldDescr='" + fieldDescr + '\'' +
				", count=" + count +
				'}';
	}
}