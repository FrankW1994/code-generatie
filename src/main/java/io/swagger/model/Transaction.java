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
 * Transaction
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-11T15:46:56.875Z[GMT]")
public class Transaction   {
  @JsonProperty("ibanSender")
  private String ibanSender = null;

  @JsonProperty("ibanReceiver")
  private String ibanReceiver = null;

  @JsonProperty("transactionId")
  private Integer transactionId = null;

  @JsonProperty("nameSender")
  private String nameSender = null;

  @JsonProperty("transactionDate")
  private String transactionDate = null;

  @JsonProperty("transferAmount")
  private Double transferAmount = null;

  public Transaction ibanSender(String ibanSender) {
    this.ibanSender = ibanSender;
    return this;
  }

  /**
   * Get ibanSender
   * @return ibanSender
  **/
  @ApiModelProperty(value = "")
  
    public String getIbanSender() {
    return ibanSender;
  }

  public void setIbanSender(String ibanSender) {
    this.ibanSender = ibanSender;
  }

  public Transaction ibanReceiver(String ibanReceiver) {
    this.ibanReceiver = ibanReceiver;
    return this;
  }

  /**
   * Get ibanReceiver
   * @return ibanReceiver
  **/
  @ApiModelProperty(example = "NL00INGB0000000000", value = "")
  
    public String getIbanReceiver() {
    return ibanReceiver;
  }

  public void setIbanReceiver(String ibanReceiver) {
    this.ibanReceiver = ibanReceiver;
  }

  public Transaction transactionId(Integer transactionId) {
    this.transactionId = transactionId;
    return this;
  }

  /**
   * Get transactionId
   * @return transactionId
  **/
  @ApiModelProperty(example = "12345678", value = "")
  
    public Integer getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(Integer transactionId) {
    this.transactionId = transactionId;
  }

  public Transaction nameSender(String nameSender) {
    this.nameSender = nameSender;
    return this;
  }

  /**
   * Get nameSender
   * @return nameSender
  **/
  @ApiModelProperty(example = "Frank Wester", value = "")
  
    public String getNameSender() {
    return nameSender;
  }

  public void setNameSender(String nameSender) {
    this.nameSender = nameSender;
  }

  public Transaction transactionDate(String transactionDate) {
    this.transactionDate = transactionDate;
    return this;
  }

  /**
   * Get transactionDate
   * @return transactionDate
  **/
  @ApiModelProperty(example = "06-05-2020", value = "")
  
    public String getTransactionDate() {
    return transactionDate;
  }

  public void setTransactionDate(String transactionDate) {
    this.transactionDate = transactionDate;
  }

  public Transaction transferAmount(Double transferAmount) {
    this.transferAmount = transferAmount;
    return this;
  }

  /**
   * Get transferAmount
   * @return transferAmount
  **/
  @ApiModelProperty(example = "20", value = "")
  
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
    Transaction transaction = (Transaction) o;
    return Objects.equals(this.ibanSender, transaction.ibanSender) &&
        Objects.equals(this.ibanReceiver, transaction.ibanReceiver) &&
        Objects.equals(this.transactionId, transaction.transactionId) &&
        Objects.equals(this.nameSender, transaction.nameSender) &&
        Objects.equals(this.transactionDate, transaction.transactionDate) &&
        Objects.equals(this.transferAmount, transaction.transferAmount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ibanSender, ibanReceiver, transactionId, nameSender, transactionDate, transferAmount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Transaction {\n");
    
    sb.append("    ibanSender: ").append(toIndentedString(ibanSender)).append("\n");
    sb.append("    ibanReceiver: ").append(toIndentedString(ibanReceiver)).append("\n");
    sb.append("    transactionId: ").append(toIndentedString(transactionId)).append("\n");
    sb.append("    nameSender: ").append(toIndentedString(nameSender)).append("\n");
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
