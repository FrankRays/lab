package shu.lab.entity;

/**
 * UserProject entity.
 * @author jimmy
 */

public class UserProject implements java.io.Serializable {

	// Fields

	private Integer upId;
	private Project project;
	private User user;
	private Integer upOrder;

	// Constructors

	/** default constructor */
	public UserProject() {
	}

	/** full constructor */
	public UserProject(Project project, User user, Integer upOrder) {
		this.project = project;
		this.user = user;
		this.upOrder = upOrder;
	}

	// Property accessors

	public Integer getUpId() {
		return this.upId;
	}

	public void setUpId(Integer upId) {
		this.upId = upId;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getUpOrder() {
		return upOrder;
	}

	public void setUpOrder(Integer upOrder) {
		this.upOrder = upOrder;
	}
}