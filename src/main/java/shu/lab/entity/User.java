package shu.lab.entity;

import org.apache.struts2.json.annotations.JSON;

import java.util.HashSet;
import java.util.Set;

/**
 * User entity. @author Jimmy J
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String username;
	private String engName;
	private String password;
	private String photoUrl;
	private String userType;
	private String userIntro;
	private Integer groupId;
	private Set userProjects = new HashSet(0);
	private Set informsForSenderId = new HashSet(0);
	private Set userPapers = new HashSet(0);
	private Set informsForReceiverId = new HashSet(0);
	private Set fieldUsers = new HashSet(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(String username, String engName, String password,
			String photoUrl, String userType, String userIntro,
			Integer groupId, Set userProjects, Set informsForSenderId,
			Set userPapers, Set informsForReceiverId, Set fieldUsers) {
		this.username = username;
		this.engName = engName;
		this.password = password;
		this.photoUrl = photoUrl;
		this.userType = userType;
		this.userIntro = userIntro;
		this.groupId = groupId;
		this.userProjects = userProjects;
		this.informsForSenderId = informsForSenderId;
		this.userPapers = userPapers;
		this.informsForReceiverId = informsForReceiverId;
		this.fieldUsers = fieldUsers;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEngName() {
		return this.engName;
	}

	public void setEngName(String engName) {
		this.engName = engName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhotoUrl() {
		return this.photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserIntro() {
		return this.userIntro;
	}

	public void setUserIntro(String userIntro) {
		this.userIntro = userIntro;
	}

	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	@JSON(serialize = false) //返回Json字符串的时候，略去这个属性
	public Set getUserProjects() {
		return this.userProjects;
	}

	public void setUserProjects(Set userProjects) {
		this.userProjects = userProjects;
	}

	@JSON(serialize = false)
	public Set getInformsForSenderId() {
		return this.informsForSenderId;
	}

	public void setInformsForSenderId(Set informsForSenderId) {
		this.informsForSenderId = informsForSenderId;
	}

	@JSON(serialize = false)
	public Set getUserPapers() {
		return this.userPapers;
	}

	public void setUserPapers(Set userPapers) {
		this.userPapers = userPapers;
	}

	@JSON(serialize = false)
	public Set getInformsForReceiverId() {
		return this.informsForReceiverId;
	}

	public void setInformsForReceiverId(Set informsForReceiverId) {
		this.informsForReceiverId = informsForReceiverId;
	}

	@JSON(serialize = false)
	public Set getFieldUsers() {
		return this.fieldUsers;
	}

	public void setFieldUsers(Set fieldUsers) {
		this.fieldUsers = fieldUsers;
	}

	@Override
	public String toString() {
		return "User{" +
				"userId=" + userId +
				", username='" + username + '\'' +
				", engName='" + engName + '\'' +
				", password='" + password + '\'' +
				", photoUrl='" + photoUrl + '\'' +
				", userType='" + userType + '\'' +
				", userIntro='" + userIntro + '\'' +
				", groupId=" + groupId +
				'}';
	}
}