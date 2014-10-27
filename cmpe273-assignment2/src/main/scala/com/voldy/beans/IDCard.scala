package com.voldy.beans

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.NotBlank

class IDCard (){
  var id: String = _
  //@NotBlank(message = "Card ID cannot be blank!")
  @JsonProperty("card_id")
  var card_id: String = _
  @NotBlank(message = "Card Name cannot be blank!")
  @JsonProperty("card_name")
  var card_name:String = _
  @NotBlank(message = "Card Number cannot be blank!")
  @JsonProperty("card_number")
  var card_number:String  = _
  @NotBlank(message = "Expiration Date cannot be blank!")
  @JsonProperty("expiration_date")
  var expiration_date:String = _
  /*@JsonIgnore
  var isDeleted:Boolean = _*/
  @JsonIgnore
  var user_id:String=_
  
  def this(card_id:String,card_name:String,card_number:String,expiration_date:String) {
    this()
    
    this.card_id = card_id
    this.card_name = card_name
    this.card_number = card_number
    this.expiration_date = expiration_date
  }
}