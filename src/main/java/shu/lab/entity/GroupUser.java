package shu.lab.entity;

/**
 * GroupUser entity.
 * @author jimmy
 */

public class GroupUser implements java.io.Serializable {

	// Fields

	private Integer guId;
	private User user;
	private Groups groups;

	// Constructors

	/** default constructor */
	public GroupUser() {
	}

	/** full constructor */
	public GroupUser(User user, Groups groups) {
		this.user = user;
		this.groups = groups;
	}

	// Property accessors

	public Integer getGuId() {
		return this.guId;
	}

	public void setGuId(Integer guId) {
		this.guId = guId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Groups getGroups() {
		return this.groups;
	}

	public void setGroups(Groups groups) {
		this.groups = groups;
	}

}