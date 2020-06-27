package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;


/**
 * ApiKey
 */
@Validated
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-18T09:28:40.437Z[GMT]")
public class ApiKey {

  @Id
  @JsonProperty("apiKey")
  @Column(unique=true, nullable=false)
  private String apiKey;

  @JsonProperty("userId")
  Integer userId = null;

  private LocalDateTime tokenCreated = null;
  private LocalDateTime tokenExpires = null;

  public ApiKey(){
    this.apiKey = UUID.randomUUID().toString();
  }

  public ApiKey(String apikey)
  {
    this.apiKey = apiKey;
  }

  public ApiKey(String apiKey, Integer userId) {
    this.apiKey = apiKey;
    this.userId = userId;
  }

  public ApiKey(String apiKey, Integer userId, LocalDateTime tokenCreated, LocalDateTime tokenExpires) {
    this.apiKey = apiKey;
    this.userId = userId;
    this.tokenCreated = tokenCreated;
    this.tokenExpires = tokenExpires;
  }

  /**
   * Get id
   * @return id
   **/
  public ApiKey apiKey(String apiKey) {
    this.apiKey = apiKey;
    return this;
  }

  /**
   * Get apiKey
   * @return apiKey
   **/
  public String getApiKey() {
    return apiKey;
  }

  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

  public ApiKey username(Integer userId) {
    this.userId = userId;
    return this;
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
    return
            Objects.equals(this.apiKey, apiKey.apiKey) &&
            Objects.equals(this.userId, apiKey.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(apiKey, userId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiKey {\n");


    sb.append("    apiKey: ").append(toIndentedString(apiKey)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
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

  /**
   * Get userId
   * @return userId
   **/
  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getUserId() {
    return userId;
  }

  public LocalDateTime getTokenCreated() {
    return tokenCreated;
  }

  public void setTokenCreated(LocalDateTime tokenCreated) {
    this.tokenCreated = tokenCreated;
  }

  public LocalDateTime getTokenExpires() {
    return tokenExpires;
  }

  public void setTokenExpires(LocalDateTime tokenExpires) {
    this.tokenExpires = tokenExpires;
  }


}

