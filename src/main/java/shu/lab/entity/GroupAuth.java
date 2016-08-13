package shu.lab.entity;

/**
 * GroupAuth entity.
 * @author jimmy
 */

public class GroupAuth implements java.io.Serializable {

	// Fields

	private Integer groupAuthId;
	private Groups groups;
	private Authority authority;

	// Constructors

	/** default constructor */
	public GroupAuth() {
	}

	/** full constructor */
	public GroupAuth(Groups groups, Authority authority) {
		this.groups = groups;
		this.authority = authority;
	}

	// Property accessors

	public Integer getGroupAuthId() {
		return this.groupAuthId;
	}

	public void setGroupAuthId(Integer groupAuthId) {
		this.groupAuthId = groupAuthId;
	}

	public Groups getGroups() {
		return this.groups;
	}

	public void setGroups(Groups groups) {
		this.groups = groups;
	}

	public Authority getAuthority() {
		return this.authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

}