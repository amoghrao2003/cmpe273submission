package com.voldy.beans

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonIgnore

class BankAccount (){
  var id: String = _
  @JsonProperty("ba_id")
  var ba_id: String = _
  @JsonProperty("account_name")
  var account_name: String = _
  @JsonProperty("routing_number")
  var routing_number: String = _
  @JsonProperty("account_number")
  var account_number: String = _
  /*@JsonIgnore
  var isDeleted:Boolean = _*/
  @JsonIgnore
  var user_id: String = _

  def this(ba_id: String, account_name: String, routing_number: String, account_number: String) {
    this()

    this.ba_id = ba_id
    this.account_name = account_name
    this.routing_number = routing_number
    this.account_number = account_number

  }
}