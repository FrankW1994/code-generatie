package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Login
 */
@Entity
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-18T09:28:40.437Z[GMT]")
public class Login   {
  @Id
  @JsonProperty("username")
  private String username = null;

  @JsonProperty("password")
  private String password = null;

  public Login username(String username) {
    this.username = username;
    return this;
  }

  public Login(String username, String password) {
    this.username = username;
    this.password = new BCrypt().hashpw(password, BCrypt.gensalt());
  }

  public Login(){}
  /**
   * Get username
   * @return username
  **/
  @ApiModelProperty(example = "FrankWester", value = "")
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Login password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Get password
   * @return password
  **/
  @ApiModelProperty(example = "Password1!", value = "")
  
    public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password/*new BCryptPasswordEncoder().encode(password)*/;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Login login = (Login) o;
    return Objects.equals(this.username, login.username) &&
        Objects.equals(this.password, login.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, password);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Login {\n");
    
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
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
