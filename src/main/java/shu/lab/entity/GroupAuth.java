package shu.lab.entity;

/**
 * GroupAuth entity. @author Jimmy J
 */

public class GroupAuth implements java.io.Serializable {

	// Fields

	private Integer groupAuthId;
	private Group group;
	private Authority authority;

	// Constructors

	/** default constructor */
	public GroupAuth() {
	}

	/** full constructor */
	public GroupAuth(Group group, Authority authority) {
		this.group = group;
		this.authority = authority;
	}

	// Property accessors

	public Integer getGroupAuthId() {
		return this.groupAuthId;
	}

	public void setGroupAuthId(Integer groupAuthId) {
		this.groupAuthId = groupAuthId;
	}

	public Group getGroup() {
		return this.group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Authority getAuthority() {
		return this.authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "GroupAuth{" +
				"groupAuthId=" + groupAuthId +
				", group=" + group +
				", authority=" + authority +
				'}';
	}
}