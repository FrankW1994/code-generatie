package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * WithdrawDeposit
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-18T09:28:40.437Z[GMT]")
public class WithdrawDeposit   {
  @JsonProperty("iban")
  private String iban = null;

  @JsonProperty("transactionId")
  private Integer transactionId = null;

  @JsonProperty("transactionDate")
  private String transactionDate = null;

  @JsonProperty("transferAmount")
  private Double transferAmount = null;

  public WithdrawDeposit iban(String iban) {
    this.iban = iban;
    return this;
  }

  /**
   * Get iban
   * @return iban
  **/
  @ApiModelProperty(value = "")
  
    public String getIban() {
    return iban;
  }

  public void setIban(String iban) {
    this.iban = iban;
  }

  public WithdrawDeposit transactionId(Integer transactionId) {
    this.transactionId = transactionId;
    return this;
  }

  /**
   * Get transactionId
   * @return transactionId
  **/
  @ApiModelProperty(value = "")
  
    public Integer getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(Integer transactionId) {
    this.transactionId = transactionId;
  }

  public WithdrawDeposit transactionDate(String transactionDate) {
    this.transactionDate = transactionDate;
    return this;
  }

  /**
   * Get transactionDate
   * @return transactionDate
  **/
  @ApiModelProperty(value = "")
  
    public String getTransactionDate() {
    return transactionDate;
  }

  public void setTransactionDate(String transactionDate) {
    this.transactionDate = transactionDate;
  }

  public WithdrawDeposit transferAmount(Double transferAmount) {
    this.transferAmount = transferAmount;
    return this;
  }

  /**
   * Get transferAmount
   * @return transferAmount
  **/
  @ApiModelProperty(value = "")
  
    public Double getTransferAmount() {
    return transferAmount;
  }

  public void setTransferAmount(Double transferAmount) {
    this.transferAmount = transferAmount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WithdrawDeposit withdrawDeposit = (WithdrawDeposit) o;
    return Objects.equals(this.iban, withdrawDeposit.iban) &&
        Objects.equals(this.transactionId, withdrawDeposit.transactionId) &&
        Objects.equals(this.transactionDate, withdrawDeposit.transactionDate) &&
        Objects.equals(this.transferAmount, withdrawDeposit.transferAmount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(iban, transactionId, transactionDate, transferAmount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WithdrawDeposit {\n");
    
    sb.append("    iban: ").append(toIndentedString(iban)).append("\n");
    sb.append("    transactionId: ").append(toIndentedString(transactionId)).append("\n");
    sb.append("    transactionDate: ").append(toIndentedString(transactionDate)).append("\n");
    sb.append("    transferAmount: ").append(toIndentedString(transferAmount)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
