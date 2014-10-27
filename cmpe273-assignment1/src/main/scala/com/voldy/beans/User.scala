package com.voldy.beans

import scala.beans.BeanProperty
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.NotBlank
import java.util.Date


class User(){
   
  @JsonProperty("id")
  var id: String = _
  @NotBlank(message = "Email cannot be blank!")
  @JsonProperty("email")
  var email:String = _
  @NotBlank(message = "Password cannot be blank!")
  @JsonProperty("password")
  var password:String  = _
  @JsonProperty("created_at")
  var created_at:String = _
  @JsonProperty("updated_at")
  var updated_at:String = _
  
  
  def this(id:String,email:String,password:String,created_at:String,updated_at:String) {
    this()
    
    this.id = id
    this.email = email
    this.password = password
    this.created_at = created_at
    this.updated_at = updated_at
  }
  
  
  
}
  

  

