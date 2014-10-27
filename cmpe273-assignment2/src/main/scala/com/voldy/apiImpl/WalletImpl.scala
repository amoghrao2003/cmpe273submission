package com.voldy.apiImpl

import com.voldy.beans.User
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import com.voldy.beans.IDCard
import scala.collection.mutable.ListBuffer

class WalletImpl {

 var userMap: Map[String, User] = new HashMap[String, User]

 var idCardMap: Map[String,IDCard] = new HashMap[String,IDCard ]
  def saveUsersToMap(user: User) {
    
    println("Adding user to Map id:"+user.id+" and email:"+user.email);
    userMap.put(user.id , user)
  }
 
 	
}