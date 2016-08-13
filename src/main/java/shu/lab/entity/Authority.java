package shu.lab.entity;

import org.apache.struts2.json.annotations.JSON;

import java.util.HashSet;
import java.util.Set;

/**
 * Authority entity.
 * @author jimmy
 */

public class Authority implements java.io.Serializable {

	// Fields

	private Integer authId;
	private String authDescr;
	private Set groupAuths = new HashSet(0);

	// Constructors

	/** default constructor */
	public Authority() {
	}

	/** full constructor */
	public Authority(String authDescr, Set groupAuths) {
		this.authDescr = authDescr;
		this.groupAuths = groupAuths;
	}

	// Property accessors

	public Integer getAuthId() {
		return this.authId;
	}

	public void setAuthId(Integer authId) {
		this.authId = authId;
	}

	public String getAuthDescr() {
		return this.authDescr;
	}

	public void setAuthDescr(String authDescr) {
		this.authDescr = authDescr;
	}

	@JSON(serialize = false)
	public Set getGroupAuths() {
		return this.groupAuths;
	}

	public void setGroupAuths(Set groupAuths) {
		this.groupAuths = groupAuths;
	}

	@Override
	public String toString() {
		return "Authority{" +
				"authDescr='" + authDescr + '\'' +
				", authId=" + authId +
				'}';
	}
}