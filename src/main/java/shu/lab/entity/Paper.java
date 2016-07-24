package shu.lab.entity;

import org.apache.struts2.json.annotations.JSON;

import java.util.HashSet;
import java.util.Set;

/**
 * Paper entity. @author Jimmy J
 */

public class Paper implements java.io.Serializable {

	// Fields

	private Integer paperId;
	private String extraAuthor;
	private String extraCorrAuthor;
	private String title;
	private String type;
	private String category;
	private String ccfStatus;
	private String postYear;
	private String volNum;
	private String issueNum;
	private Integer startPage;
	private Integer endPage;
	private String sourceUrl;
	private Set userPapers = new HashSet(0);
	private Set fieldPapers = new HashSet(0);

	// Constructors

	/** default constructor */
	public Paper() {
	}

	/** full constructor */
	public Paper(String extraAuthor,String extraCorrAuthor, String title, String type,
			String category, String ccfStatus, String postYear, String volNum,
			String issueNum, Integer startPage, Integer endPage,
			Set userPapers, Set fieldPapers) {
		this.extraAuthor = extraAuthor;
		this.extraCorrAuthor = extraCorrAuthor;
		this.title = title;
		this.type = type;
		this.category = category;
		this.ccfStatus = ccfStatus;
		this.postYear = postYear;
		this.volNum = volNum;
		this.issueNum = issueNum;
		this.startPage = startPage;
		this.endPage = endPage;
		this.userPapers = userPapers;
		this.fieldPapers = fieldPapers;
	}

	// Property accessors

	public Integer getPaperId() {
		return this.paperId;
	}

	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}

	public String getExtraAuthor() {
		return this.extraAuthor;
	}

	public void setExtraAuthor(String extraAuthor) {
		this.extraAuthor = extraAuthor;
	}

	public String getExtraCorrAuthor() {
		return extraCorrAuthor;
	}

	public void setExtraCorrAuthor(String extraCorrAuthor) {
		this.extraCorrAuthor = extraCorrAuthor;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCcfStatus() {
		return this.ccfStatus;
	}

	public void setCcfStatus(String ccfStatus) {
		this.ccfStatus = ccfStatus;
	}

	public String getPostYear() {
		return this.postYear;
	}

	public void setPostYear(String postYear) {
		this.postYear = postYear;
	}

	public String getVolNum() {
		return this.volNum;
	}

	public void setVolNum(String volNum) {
		this.volNum = volNum;
	}

	public String getIssueNum() {
		return this.issueNum;
	}

	public void setIssueNum(String issueNum) {
		this.issueNum = issueNum;
	}

	public Integer getStartPage() {
		return this.startPage;
	}

	public void setStartPage(Integer startPage) {
		this.startPage = startPage;
	}

	public Integer getEndPage() {
		return this.endPage;
	}

	public void setEndPage(Integer endPage) {
		this.endPage = endPage;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	@JSON(serialize = false)
	public Set getUserPapers() {
		return this.userPapers;
	}

	public void setUserPapers(Set userPapers) {
		this.userPapers = userPapers;
	}

	@JSON(serialize = false)
	public Set getFieldPapers() {
		return this.fieldPapers;
	}

	public void setFieldPapers(Set fieldPapers) {
		this.fieldPapers = fieldPapers;
	}

	@Override
	public String toString() {
		return "Paper{" +
				"paperId=" + paperId +
				", extraAuthor='" + extraAuthor + '\'' +
				", extraCorrAuthor='" + extraCorrAuthor + '\'' +
				", title='" + title + '\'' +
				", type='" + type + '\'' +
				", category='" + category + '\'' +
				", ccfStatus='" + ccfStatus + '\'' +
				", postYear='" + postYear + '\'' +
				", volNum='" + volNum + '\'' +
				", issueNum='" + issueNum + '\'' +
				", startPage=" + startPage +
				", endPage=" + endPage +
				", sourceUrl='" + sourceUrl + '\'' +
				'}';
	}
}