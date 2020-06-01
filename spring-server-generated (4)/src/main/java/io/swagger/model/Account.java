package io.swagger.model;

import java.io.Serializable;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Account
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"userId", "IBAN"})})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-18T09:28:40.437Z[GMT]")
@Data
public class Account {
  @JsonProperty("userId")
  private Long userId = null;

  @Id
  @GeneratedValue(generator ="system-uuid")
  @GenericGenerator(name="system-uuid", strategy = "uuid")
  @JsonProperty("IBAN")
  @Column(name = "IBAN")
  private String IBAN = null;

  public Account()
  {}

  public Account(Long userId, String IBAN, RankEnum rank, StatusEnum status, Double balance, String currency) {
    this.userId = userId;
    this.IBAN = IBAN;
    this.rank = rank;
    this.status = status;
    this.balance = balance;
    this.currency = currency;
  }

  public Account(String nlfout) {
    this.IBAN = nlfout;
  }

  /**
   * Gets or Sets rank
   */
  public enum RankEnum {
    CURRENT("Current"),
    
    SAVING("Saving");

    private String value;

    RankEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static RankEnum fromValue(String text) {
      for (RankEnum b : RankEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("rank")
  private RankEnum rank = null;

  @JsonProperty("balance")
  private Double balance = null;

  @JsonProperty("currency")
  private String currency = null;

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    ACTIVE("Active"),
    
    BLOCKED("Blocked");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("status")
  private StatusEnum status = null;

  public Account userId(Long userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  **/
  @ApiModelProperty(example = "2", required = true, value = "")
      @NotNull

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Account IBAN(String IBAN) {
    this.IBAN = IBAN;
    return this;
  }

  /**
   * Get IBAN
   * @return IBAN
  **/
  @ApiModelProperty(example = "NLxxINHO0xxxxxxxxx", required = true, value = "")
      @NotNull

    public String getIBAN() {
    return IBAN;
  }

  public void setIBAN(String IBAN) {
    if(!IBAN.matches("NL\\d\\dINHO\\d\\d\\d\\d\\d\\d\\d\\d"))
    {
      throw new IllegalArgumentException("IBAN MUST BE TYPE OF NLXXINHOXXXXXXXX");
    }
    this.IBAN = IBAN;
  }

  public Account rank(RankEnum rank) {
    this.rank = rank;
    return this;
  }

  /**
   * Get rank
   * @return rank
  **/
  @ApiModelProperty(example = "Current", value = "")
  
    public RankEnum getRank() {
    return rank;
  }

  public void setRank(RankEnum rank) {
    this.rank = rank;
  }

  public Account balance(Double balance) {
    this.balance = balance;
    return this;
  }

  /**
   * Get balance
   * minimum: 0
   * @return balance
  **/
  @ApiModelProperty(example = "100", required = true, value = "")
      @NotNull

  @DecimalMin("0")  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }

  public Account currency(String currency) {
    this.currency = currency;
    return this;
  }

  /**
   * Get currency
   * @return currency
  **/
  @ApiModelProperty(example = "EUR", value = "")
  
    public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public Account status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(example = "Active", value = "")
  
    public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Account account = (Account) o;
    return Objects.equals(this.userId, account.userId) &&
        Objects.equals(this.IBAN, account.IBAN) &&
        Objects.equals(this.rank, account.rank) &&
        Objects.equals(this.balance, account.balance) &&
        Objects.equals(this.currency, account.currency) &&
        Objects.equals(this.status, account.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, IBAN, rank, balance, currency, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Account {\n");
    
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    IBAN: ").append(toIndentedString(IBAN)).append("\n");
    sb.append("    rank: ").append(toIndentedString(rank)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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
