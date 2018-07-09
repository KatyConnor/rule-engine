package hx.com.example.rule;


import java.util.Date;

/**
 * @Author mingliang
 * @Date 2017-12-28 10:47
 */
public class DebtResourcesInfoDTO {
    private String loanNumber;
    private String userName;
    private Date transferContractSignTime;
    private Date transferContractEffectTime;
    private String certNo;
    private Date sigLoanTime;
    private String telNo;
    private Double loanCapital;
    private Date lendingTime;
    private Double payPrincipal;
    private Double paidInterest;
    private Double paidFine;
    private Double unpaidPrincipal;
    private Double unpaidInterest;
    private Double transferAmount;


    public String getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(String loanNumber) {
        this.loanNumber = loanNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getTransferContractSignTime() {
        return transferContractSignTime;
    }

    public void setTransferContractSignTime(Date transferContractSignTime) {
        this.transferContractSignTime = transferContractSignTime;
    }

    public Date getTransferContractEffectTime() {
        return transferContractEffectTime;
    }

    public void setTransferContractEffectTime(Date transferContractEffectTime) {
        this.transferContractEffectTime = transferContractEffectTime;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public Date getSigLoanTime() {
        return sigLoanTime;
    }

    public void setSigLoanTime(Date sigLoanTime) {
        this.sigLoanTime = sigLoanTime;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public Double getLoanCapital() {
        return loanCapital;
    }

    public void setLoanCapital(Double loanCapital) {
        this.loanCapital = loanCapital;
    }

    public Date getLendingTime() {
        return lendingTime;
    }

    public void setLendingTime(Date lendingTime) {
        this.lendingTime = lendingTime;
    }

    public Double getPayPrincipal() {
        return payPrincipal;
    }

    public void setPayPrincipal(Double payPrincipal) {
        this.payPrincipal = payPrincipal;
    }

    public Double getPaidInterest() {
        return paidInterest;
    }

    public void setPaidInterest(Double paidInterest) {
        this.paidInterest = paidInterest;
    }

    public Double getPaidFine() {
        return paidFine;
    }

    public void setPaidFine(Double paidFine) {
        this.paidFine = paidFine;
    }

    public Double getUnpaidPrincipal() {
        return unpaidPrincipal;
    }

    public void setUnpaidPrincipal(Double unpaidPrincipal) {
        this.unpaidPrincipal = unpaidPrincipal;
    }

    public Double getUnpaidInterest() {
        return unpaidInterest;
    }

    public void setUnpaidInterest(Double unpaidInterest) {
        this.unpaidInterest = unpaidInterest;
    }

    public Double getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(Double transferAmount) {
        this.transferAmount = transferAmount;
    }

}
