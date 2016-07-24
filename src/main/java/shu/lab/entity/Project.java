package shu.lab.entity;

import org.apache.struts2.json.annotations.JSON;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Project entity. @author Jimmy J
 */

public class Project implements java.io.Serializable {

	// Fields

	private Integer proId;
	private String proName;
	private String extraDirectors;
	private Timestamp startDate;
	private Timestamp endDate;
	private String proFee;
	private String proType;
	private String proLevel;
	private Set userProjects = new HashSet(0);
	private Set fieldProjects = new HashSet(0);

	// Constructors

	/** default constructor */
	public Project() {
	}

	/** full constructor */
	public Project(String proName, String extraDirectors, Timestamp startDate,
			Timestamp endDate, String proFee, String proType, String proLevel,
			Set userProjects, Set fieldProjects) {
		this.proName = proName;
		this.extraDirectors = extraDirectors;
		this.startDate = startDate;
		this.endDate = endDate;
		this.proFee = proFee;
		this.proType = proType;
		this.proLevel = proLevel;
		this.userProjects = userProjects;
		this.fieldProjects = fieldProjects;
	}

	// Property accessors

	public Integer getProId() {
		return this.proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	public String getProName() {
		return this.proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getExtraDirectors() {
		return this.extraDirectors;
	}

	public void setExtraDirectors(String extraDirectors) {
		this.extraDirectors = extraDirectors;
	}

	public Timestamp getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public String getProFee() {
		return this.proFee;
	}

	public void setProFee(String proFee) {
		this.proFee = proFee;
	}

	public String getProType() {
		return this.proType;
	}

	public void setProType(String proType) {
		this.proType = proType;
	}

	public String getProLevel() {
		return this.proLevel;
	}

	public void setProLevel(String proLevel) {
		this.proLevel = proLevel;
	}

	@JSON(serialize = false)
	public Set getUserProjects() {
		return this.userProjects;
	}

	public void setUserProjects(Set userProjects) {
		this.userProjects = userProjects;
	}

	@JSON(serialize = false)
	public Set getFieldProjects() {
		return this.fieldProjects;
	}

	public void setFieldProjects(Set fieldProjects) {
		this.fieldProjects = fieldProjects;
	}

	@Override
	public String toString() {
		return "Project{" +
				"proId=" + proId +
				", proName='" + proName + '\'' +
				", extraDirectors='" + extraDirectors + '\'' +
				", startDate=" + startDate +
				", endDate=" + endDate +
				", proFee='" + proFee + '\'' +
				", proType='" + proType + '\'' +
				", proLevel='" + proLevel + '\'' +
				'}';
	}
}