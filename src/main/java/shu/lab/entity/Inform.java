package shu.lab.entity;

import java.sql.Timestamp;

/**
 * Inform entity. @author Jimmy J
 */

public class Inform implements java.io.Serializable {

	// Fields

	private Integer informId;
	private User userBySenderId;
	private User userByReceiverId;
	private String informType;
	private String content;
	private Timestamp sendDate;

	// Constructors

	/** default constructor */
	public Inform() {
	}

	/** full constructor */
	public Inform(User userBySenderId, User userByReceiverId,
			String informType, Timestamp sendDate) {
		this.userBySenderId = userBySenderId;
		this.userByReceiverId = userByReceiverId;
		this.informType = informType;
		this.sendDate = sendDate;
	}

	// Property accessors

	public Integer getInformId() {
		return this.informId;
	}

	public void setInformId(Integer informId) {
		this.informId = informId;
	}

	public User getUserBySenderId() {
		return this.userBySenderId;
	}

	public void setUserBySenderId(User userBySenderId) {
		this.userBySenderId = userBySenderId;
	}

	public User getUserByReceiverId() {
		return this.userByReceiverId;
	}

	public void setUserByReceiverId(User userByReceiverId) {
		this.userByReceiverId = userByReceiverId;
	}

	public String getInformType() {
		return this.informType;
	}

	public void setInformType(String informType) {
		this.informType = informType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getSendDate() {
		return this.sendDate;
	}

	public void setSendDate(Timestamp sendDate) {
		this.sendDate = sendDate;
	}

	@Override
	public String toString() {
		return "Inform{" +
				"informId=" + informId +
				", userBySenderId=" + userBySenderId +
				", userByReceiverId=" + userByReceiverId +
				", informType='" + informType + '\'' +
				", content='" + content + '\'' +
				", sendDate=" + sendDate +
				'}';
	}
}