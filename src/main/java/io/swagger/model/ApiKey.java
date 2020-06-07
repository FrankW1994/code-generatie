package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Objects;


/**
 * ApiKey
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-18T09:28:40.437Z[GMT]")
@Data
@Entity
public class ApiKey   {
  @Id
  @SequenceGenerator(name="transaction_seq", initialValue = 1000001)
  @GeneratedValue(strategy =GenerationType.SEQUENCE, generator="transaction_seq")
  @JsonProperty("transactionId")
  @Column(unique=true, nullable=false)
  private Long Id = null;

  @JsonProperty("apiKey")
  private String apiKey = null;

  @JsonProperty("username")
  private String username = null;


  public ApiKey id(Long id) {
    this.Id = id;
    return this;
  }

  public ApiKey() {
  }

  public ApiKey(Long Id, String apiKey, String username) {
    this.apiKey = apiKey;
    this.username = username;
  }

  /**
   * Get id
   * @return id
   **/
  @ApiModelProperty(example = "1000001", value = "")

  public Long getId() {
    return Id;
  }

  public void setId(Long id) {
    this.Id = id;
  }

  public ApiKey apiKey(String apiKey) {
    this.apiKey = apiKey;
    return this;
  }

  /**
   * Get apiKey
   * @return apiKey
   **/
  @ApiModelProperty(example = "abcdef12345", value = "")

  public String getApiKey() {
    return apiKey;
  }

  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

  public ApiKey username(String username) {
    this.username = username;
    return this;
  }

  /**
   * Get username
   * @return username
   **/
  @ApiModelProperty(example = "billy", value = "")

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiKey apiKey = (ApiKey) o;
    return Objects.equals(this.Id, apiKey.Id) &&
            Objects.equals(this.apiKey, apiKey.apiKey) &&
            Objects.equals(this.username, apiKey.username);
  }

  @Override
  public int hashCode() {
    return Objects.hash(Id, apiKey, username);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiKey {\n");

    sb.append("    id: ").append(toIndentedString(Id)).append("\n");
    sb.append("    apiKey: ").append(toIndentedString(apiKey)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
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

