package shu.lab.entity;

/**
 * UserPaper entity.
 * @author jimmy
 */

public class UserPaper implements java.io.Serializable {

	// Fields

	private Integer upId;
	private User user;
	private Paper paper;
	private Integer upOrder;
	private Integer isCorr;

	// Constructors

	/** default constructor */
	public UserPaper() {
	}

	/** full constructor */
	public UserPaper(User user, Paper paper, Integer upOrder, Integer isCorr) {
		this.user = user;
		this.paper = paper;
		this.upOrder = upOrder;
		this.isCorr = isCorr;
	}

	// Property accessors

	public Integer getUpId() {
		return this.upId;
	}

	public void setUpId(Integer upId) {
		this.upId = upId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Paper getPaper() {
		return this.paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public Integer getUpOrder() {
		return this.upOrder;
	}

	public void setUpOrder(Integer upOrder) {
		this.upOrder = upOrder;
	}

	public Integer getIsCorr() {
		return this.isCorr;
	}

	public void setIsCorr(Integer isCorr) {
		this.isCorr = isCorr;
	}

}