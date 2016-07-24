package shu.lab.entity;


/**
 * FieldPaper entity. @author Jimmy J
 */

public class FieldPaper implements java.io.Serializable {

	// Fields

	private Integer fpId;
	private Paper paper;
	private Field field;

	// Constructors

	/** default constructor */
	public FieldPaper() {
	}

	/** full constructor */
	public FieldPaper(Paper paper, Field field) {
		this.paper = paper;
		this.field = field;
	}

	// Property accessors

	public Integer getFpId() {
		return this.fpId;
	}

	public void setFpId(Integer fpId) {
		this.fpId = fpId;
	}

	public Paper getPaper() {
		return this.paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public Field getField() {
		return this.field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	@Override
	public String toString() {
		return "FieldPaper{" +
				"fpId=" + fpId +
				", paper=" + paper +
				", field=" + field +
				'}';
	}
}