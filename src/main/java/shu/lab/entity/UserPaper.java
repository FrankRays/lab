package shu.lab.entity;

/**
 * UserPaper entity. @author Jimmy J
 */

public class UserPaper implements java.io.Serializable {

	// Fields

	private Integer upId;
	private User user;
	private Paper paper;
	private Integer order;
	private Integer isCorr;

	// Constructors

	/** default constructor */
	public UserPaper() {
	}

	/** full constructor */
	public UserPaper(User user, Paper paper, Integer isCorr) {
		this.user = user;
		this.paper = paper;
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

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getIsCorr() {
		return this.isCorr;
	}

	public void setIsCorr(Integer isCorr) {
		this.isCorr = isCorr;
	}

	@Override
	public String toString() {
		return "UserPaper{" +
				"upId=" + upId +
				", user=" + user +
				", paper=" + paper +
				", order=" + order +
				", isCorr=" + isCorr +
				'}';
	}
}