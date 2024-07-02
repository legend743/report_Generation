package Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "WoReportDetails")
public class reportDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Assuming you have an ID field

    @Column(name = "wo_raised_by")
    private String woRaisedBy;

    @Column(name = "wo_raised_on")
    private String woRaisedOn;

    @Column(name = "circle")
    private String circle;

    @Column(name = "card_profile")
    private String cardProfile;

    @Column(name = "sim_card_man")
    private String simCardMan;

    @Column(name = "l_size")
    private String lSize;

    @Column(name = "p_n")
    private String pN;

    @Column(name = "invoice")
    private String invoice;

    @Column(name = "start_iccid")
    private String startIccid;

    @Column(name = "end_iccid")
    private String endIccid;

    @Column(name = "t_complete_date")
    private String tCompletedate;

    @Column(name = "testing_complete_date")
    private String testingCompleteDate;

    @Column(name = "testing_method")
    private String testingMethod;

    @Column(name = "test_result")
    private String testResult;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWoRaisedBy() {
		return woRaisedBy;
	}

	public void setWoRaisedBy(String woRaisedBy) {
		this.woRaisedBy = woRaisedBy;
	}

	public String getWoRaisedOn() {
		return woRaisedOn;
	}

	public void setWoRaisedOn(String woRaisedOn) {
		this.woRaisedOn = woRaisedOn;
	}

	public String getCircle() {
		return circle;
	}

	public void setCircle(String circle) {
		this.circle = circle;
	}

	public String getCardProfile() {
		return cardProfile;
	}

	public void setCardProfile(String cardProfile) {
		this.cardProfile = cardProfile;
	}

	public String getSimCardMan() {
		return simCardMan;
	}

	public void setSimCardMan(String simCardMan) {
		this.simCardMan = simCardMan;
	}

	public String getlSize() {
		return lSize;
	}

	public void setlSize(String lSize) {
		this.lSize = lSize;
	}

	public String getpN() {
		return pN;
	}

	public void setpN(String pN) {
		this.pN = pN;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getStartIccid() {
		return startIccid;
	}

	public void setStartIccid(String startIccid) {
		this.startIccid = startIccid;
	}

	public String getEndIccid() {
		return endIccid;
	}

	public void setEndIccid(String endIccid) {
		this.endIccid = endIccid;
	}

	public String gettCompletedate() {
		return tCompletedate;
	}

	public void settCompletedate(String tCompletedate) {
		this.tCompletedate = tCompletedate;
	}

	public String getTestingCompleteDate() {
		return testingCompleteDate;
	}

	public void setTestingCompleteDate(String testingCompleteDate) {
		this.testingCompleteDate = testingCompleteDate;
	}

	public String getTestingMethod() {
		return testingMethod;
	}

	public void setTestingMethod(String testingMethod) {
		this.testingMethod = testingMethod;
	}

	public String getTestResult() {
		return testResult;
	}

	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}

	public reportDetails(Long id, String woRaisedBy, String woRaisedOn, String circle, String cardProfile,
			String simCardMan, String lSize, String pN, String invoice, String startIccid, String endIccid,
			String tCompletedate, String testingCompleteDate, String testingMethod, String testResult) {
		super();
		this.id = id;
		this.woRaisedBy = woRaisedBy;
		this.woRaisedOn = woRaisedOn;
		this.circle = circle;
		this.cardProfile = cardProfile;
		this.simCardMan = simCardMan;
		this.lSize = lSize;
		this.pN = pN;
		this.invoice = invoice;
		this.startIccid = startIccid;
		this.endIccid = endIccid;
		this.tCompletedate = tCompletedate;
		this.testingCompleteDate = testingCompleteDate;
		this.testingMethod = testingMethod;
		this.testResult = testResult;
	}

    // Getters and setters
    

    // Constructor, equals, hashCode, toString methods (optional)
	
}
