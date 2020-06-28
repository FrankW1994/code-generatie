package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
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

  private LocalDateTime apiKeyCreated = null;
  private LocalDateTime apiKeyExpires = null;

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

  public ApiKey(String apiKey, Integer userId, LocalDateTime apiKeyCreated, LocalDateTime apiKeyExpires) {
    this.apiKey = apiKey;
    this.userId = userId;
    this.apiKeyCreated = apiKeyCreated;
    this.apiKeyExpires = apiKeyExpires;
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

  public LocalDateTime getApiKeyCreated() {
    return apiKeyCreated;
  }

  public void setApiKeyCreated(LocalDateTime tokenCreated) {
    this.apiKeyCreated = tokenCreated;
  }

  public LocalDateTime getApiKeyExpires() {
    return apiKeyExpires;
  }

  public void setApiKeyExpires(LocalDateTime tokenExpires) {
    this.apiKeyExpires = tokenExpires;
  }


}

