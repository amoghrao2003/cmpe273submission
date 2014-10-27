package com.voldy.beans

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import scala.beans.BeanProperty
@JsonIgnoreProperties(ignoreUnknown = true)
class RoutingDetails (){
  
  @BeanProperty
  @JsonProperty("customer_name")
  var customer_name: String = _
  @BeanProperty
  @JsonProperty("routing_number")
  @BeanProperty
  var routing_number: String = _
  @JsonProperty("change_date")
  @BeanProperty
  var change_date: String = _
  @JsonProperty("data_view_code")
  @BeanProperty
  var data_view_code: String = _
  @JsonProperty("message")
  @BeanProperty
  var message: String = _
  @JsonProperty("record_type_code")
  @BeanProperty
  var record_type_code: String = _
  @JsonProperty("zip")
  @BeanProperty
  var zip: String = _
  @JsonProperty("office_code")
  @BeanProperty
  var office_code: String = _
  @JsonProperty("telephone")
  @BeanProperty
  var telephone: String = _
  @JsonProperty("rn")
  @BeanProperty
  var rn: String = _
  @JsonProperty("address")
  @BeanProperty
  var address: String = _
  @JsonProperty("code")
  @BeanProperty
  var code: String = _
   @JsonProperty("state")
   @BeanProperty
  var state: String = _
   @JsonProperty("new_routing_number")
   @BeanProperty
  var new_routing_number: String = _
   @JsonProperty("institution_status_code")
   @BeanProperty
  var institution_status_code: String = _
  @JsonProperty("city")
  @BeanProperty
  var city: String = _
  
  def this(customer_name:String,routing_number:String) {
    this()
    this.customer_name = customer_name
    this.routing_number = routing_number
  }

}