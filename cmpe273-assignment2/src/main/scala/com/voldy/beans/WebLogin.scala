package com.voldy.beans

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.validator.constraints.NotBlank

class WebLogin (){
  var id: String = _
  @JsonProperty("login_id")
  var login_id: String = _
  @NotBlank(message = "URL cannot be blank!")
  @JsonProperty("url")
  var url: String = _
  @NotBlank(message = "Login cannot be blank!")
  @JsonProperty("login")
  var login: String = _
  @NotBlank(message = "Password cannot be blank!")
  @JsonProperty("password")
  var password: String = _
  /*@JsonIgnore
  var isDeleted:Boolean = _*/
  @JsonIgnore
  var user_id: String = _

  def this(login_id: String, url: String, login: String, password: String) {
    this()

    this.login_id = login_id
    this.url = url
    this.login = login
    this.password = password
  }
}