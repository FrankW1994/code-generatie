package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.service.AccountApiService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Objects;

/**
 * Transaction
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"transactionId"})})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Validated
@Data
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-18T09:28:40.437Z[GMT]")
public class Transaction {
  @Id
  @SequenceGenerator(name="transaction_seq", initialValue = 1000001)
  @GeneratedValue(strategy =GenerationType.SEQUENCE, generator="transaction_seq")
  @JsonProperty("transactionId")
  @Column(unique=true, nullable=false)
  private Long transactionId = null;

  @JsonProperty("ibanSender")
  private String ibanSender = null;

  @JsonProperty("ibanReceiver")
  private String ibanReceiver = null;

  @JsonProperty("nameSender")
  private String nameSender = null;

  @JsonProperty("transactionDate")
  private Timestamp transactionDate = null;

  @JsonProperty("transferAmount")
  private Double transferAmount = null;


  public Transaction()
  {}

  public Transaction(String ibanSender, String ibanReceiver, String nameSender, Timestamp transactionDate, double transferAmount) {
    this.ibanSender = ibanSender;
    this.ibanReceiver = ibanReceiver;
    this.nameSender = nameSender;
    this.transactionDate = transactionDate;
    this.transferAmount = transferAmount;
  }

  public Transaction(String ibanSender, String ibanReceiver, String nameSender, Double transferAmount) {
    this.ibanSender = ibanSender;
    this.ibanReceiver = ibanReceiver;
    this.nameSender = nameSender;
    this.transactionDate = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
    this.transferAmount = transferAmount;
  }


  /**
   * Get ibanSender
   *
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
   *
   * @return ibanReceiver
   * @return ibanReceiver
   **/
  @ApiModelProperty(value = "")

  public String getIbanReceiver() {
    return ibanReceiver;
  }

  public void setIbanReceiver(String ibanReceiver) {
    this.ibanReceiver = ibanReceiver;
  }

  public Transaction transactionId(Long transactionId) {
    this.transactionId = transactionId;
    return this;
  }
  /**
   * Get transactionId
   *
   * @return transactionId
   **/
  @ApiModelProperty(value = "")
  public Long getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(Long transactionId) {
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
  @ApiModelProperty(value = "")

  public String getNameSender() {
    return nameSender;
  }

  public void setNameSender(String nameSender) {
    this.nameSender = nameSender;
  }

  public Transaction transactionDate(Timestamp transactionDate) {
    this.transactionDate = transactionDate;
    return this;
  }

  /**
   * Get transactionDate
   * @return transactionDate
   **/
  @ApiModelProperty(value = "")

  public Timestamp getTransactionDate() {
    return transactionDate;
  }

  public void setTransactionDate(Timestamp transactionDate) {
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
  @ApiModelProperty(value = "")

  public Double getTransferAmount() {
    return transferAmount;
  }

  public void setTransferAmount(Double transferAmount) {
    this.transferAmount = transferAmount;
  }


  @Override
  public boolean equals(Object o) {
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
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
