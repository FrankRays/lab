package shu.lab.entity;

/**
 * FieldProject entity. @author Jimmy J
 */

public class FieldProject implements java.io.Serializable {

	// Fields

	private Integer fpId;
	private Project project;
	private Field field;

	// Constructors

	/** default constructor */
	public FieldProject() {
	}

	/** full constructor */
	public FieldProject(Project project, Field field) {
		this.project = project;
		this.field = field;
	}

	// Property accessors

	public Integer getFpId() {
		return this.fpId;
	}

	public void setFpId(Integer fpId) {
		this.fpId = fpId;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Field getField() {
		return this.field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	@Override
	public String toString() {
		return "FieldProject{" +
				"fpId=" + fpId +
				", project=" + project +
				", field=" + field +
				'}';
	}
}