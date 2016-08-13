package shu.lab.entity;

import org.apache.struts2.json.annotations.JSON;

import java.util.HashSet;
import java.util.Set;

/**
 * Groups entity.
 * @author jimmy
 */

public class Groups implements java.io.Serializable {

	// Fields

	private Integer groupId;
	private String groupName;
	private Integer leader;
	private Set groupAuths = new HashSet(0);
	private Set groupUsers = new HashSet(0);

	// Constructors

	/** default constructor */
	public Groups() {
	}

	/** full constructor */
	public Groups(String groupName, Integer leader, Set groupAuths,
				  Set groupUsers) {
		this.groupName = groupName;
		this.leader = leader;
		this.groupAuths = groupAuths;
		this.groupUsers = groupUsers;
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

	public Integer getLeader() {
		return this.leader;
	}

	public void setLeader(Integer leader) {
		this.leader = leader;
	}

	@JSON(serialize = false)
	public Set getGroupAuths() {
		return this.groupAuths;
	}

	public void setGroupAuths(Set groupAuths) {
		this.groupAuths = groupAuths;
	}

	@JSON(serialize = false)
	public Set getGroupUsers() {
		return this.groupUsers;
	}

	public void setGroupUsers(Set groupUsers) {
		this.groupUsers = groupUsers;
	}

	@Override
	public String toString() {
		return "Groups{" +
				"groupId=" + groupId +
				", groupName='" + groupName + '\'' +
				", leader=" + leader +
				'}';
	}
}