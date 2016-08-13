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

	// Constructors

	/** default constructor */
	public UserProject() {
	}

	/** full constructor */
	public UserProject(Project project, User user) {
		this.project = project;
		this.user = user;
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

}