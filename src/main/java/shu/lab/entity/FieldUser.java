package shu.lab.entity;

/**
 * FieldUser entity. @author Jimmy J
 */

public class FieldUser implements java.io.Serializable {

	// Fields

	private Integer fuId;
	private User user;
	private Field field;

	// Constructors

	/** default constructor */
	public FieldUser() {
	}

	/** full constructor */
	public FieldUser(User user, Field field) {
		this.user = user;
		this.field = field;
	}

	// Property accessors

	public Integer getFuId() {
		return this.fuId;
	}

	public void setFuId(Integer fuId) {
		this.fuId = fuId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Field getField() {
		return this.field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	@Override
	public String toString() {
		return "FieldUser{" +
				"fuId=" + fuId +
				", user=" + user +
				", field=" + field +
				'}';
	}
}