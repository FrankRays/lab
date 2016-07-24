package shu.lab.entity;

import org.apache.struts2.json.annotations.JSON;

import java.util.HashSet;
import java.util.Set;

/**
 * Group entity. @author Jimmy J
 */

public class Group implements java.io.Serializable {

	// Fields

	private Integer groupId;
	private String groupName;
	private Set groupAuths = new HashSet(0);

	// Constructors

	/** default constructor */
	public Group() {
	}

	/** full constructor */
	public Group(String groupName, Set groupAuths) {
		this.groupName = groupName;
		this.groupAuths = groupAuths;
	}

	// Property accessors

	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@JSON(serialize = false)
	public Set getGroupAuths() {
		return this.groupAuths;
	}

	public void setGroupAuths(Set groupAuths) {
		this.groupAuths = groupAuths;
	}

	@Override
	public String toString() {
		return "Group{" +
				"groupId=" + groupId +
				", groupName='" + groupName + '\'' +
				'}';
	}
}