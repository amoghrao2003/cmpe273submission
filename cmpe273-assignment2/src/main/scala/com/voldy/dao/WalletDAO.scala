package com.voldy.dao

import com.mongodb.DBCollection
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.mongodb.DB
import com.voldy.beans.User
import java.util.Date
import com.mongodb.BasicDBObject
import com.mongodb.DBObject
import com.voldy.beans.IDCard
import com.mongodb.DBCursor
import java.util.ArrayList
import com.voldy.beans.BankAccount
import com.voldy.beans.WebLogin

class WalletDAO {
  
  
    var mongoURIString:String = "mongodb://voldy:voldy@ds047930.mongolab.com:47930/voldy"
    var  mongoClient:MongoClient = new MongoClient(new MongoClientURI(mongoURIString))
    var  walletDatabase:DB = mongoClient.getDB("voldy")
    var usersCollection:DBCollection = walletDatabase.getCollection("users")
    var idCardCollection:DBCollection = walletDatabase.getCollection("idCard")
    var webLoginCollection:DBCollection = walletDatabase.getCollection("webLogin")
    var bankAccountCollection:DBCollection = walletDatabase.getCollection("bankAccount")
  
  
  def createUsers(user:User):User = {
    var o:BasicDBObject = new BasicDBObject("user_id",user.uid ).
    append("email", user.email).
    append("password", user.password).
    append("created_at", user.created_at).
    append("updated_at", user.updated_at )
    
    usersCollection.insert(o);
    return user;
  }
   
  def findUsers(uid:String):User = {
    var user:User = new User()
    var o:BasicDBObject = new BasicDBObject("user_id",uid )
    var dbObject:DBObject = usersCollection.findOne(o)
    user.uid  = dbObject.get("user_id").toString()
    user.email = dbObject.get("email").toString()
    user.password = dbObject.get("password").toString()
    user.created_at  = dbObject.get("created_at").toString()
    if(dbObject.get("updated_at")!=null){
      user.updated_at  = dbObject.get("updated_at").toString()
    }
    return user;
  }
  def updateUser(user:User) = {
    var searchQuery:BasicDBObject =new BasicDBObject().append("uid", user.uid )
    var newDoc:BasicDBObject = new BasicDBObject()
    newDoc.append("$set", new BasicDBObject().append("password",user.password).append("updated_at", user.updated_at ))
    usersCollection.update(searchQuery, newDoc)
  }
 //--------------IDCard-------------
 def createIDCard(idCard:IDCard):IDCard = {
    var o:BasicDBObject = new BasicDBObject("user_id",idCard.user_id  ).
    append("card_id", idCard.card_id ).
    append("card_name", idCard.card_name ).
    append("card_number", idCard.card_number ).
    append("expiration_date", idCard.expiration_date )
    idCardCollection .insert(o);
    return idCard;
  }
 
  def findIDCards(uid:String):ArrayList[IDCard] = {
    val idCardListResult1 = new ArrayList[IDCard]
    var o:BasicDBObject = new BasicDBObject("user_id",uid )
    var idCard:IDCard = null
    var dbCursor:DBCursor = idCardCollection .find(o)
    while(dbCursor.hasNext()){
      var dbObject:DBObject = dbCursor.next()
      idCard  = new IDCard()
      idCard.card_id  = dbObject.get("card_id").toString()
      idCard.card_name  = dbObject.get("card_name").toString()
      idCard.card_number  = dbObject.get("card_number").toString()
      idCard.expiration_date  = dbObject.get("expiration_date").toString()
      idCardListResult1.add(idCard);
    }
    return idCardListResult1;
  }
  
  def removeIDCard(uid:String , cid:String) = {
    var removeQuery:BasicDBObject = new BasicDBObject("user_id",uid).
    append("card_id", cid)
	idCardCollection .remove(removeQuery)
  }
  //---------WebLogin
   def createWebLogin(webLogin:WebLogin):WebLogin = {
    var o:BasicDBObject = new BasicDBObject("user_id",webLogin.user_id).
    append("login_id", webLogin.login_id ).
    append("login", webLogin.login ).
    append("password", webLogin.password ).
    append("url", webLogin.url )
    webLoginCollection .insert(o);
    return webLogin;
  }
 
  def findWebLogin(uid:String):ArrayList[WebLogin] = {
    val webLoginResult1 = new ArrayList[WebLogin]
    var o:BasicDBObject = new BasicDBObject("user_id",uid )
    var webLogin:WebLogin = null
    var dbCursor:DBCursor = webLoginCollection.find(o)
    while(dbCursor.hasNext()){
      var dbObject:DBObject = dbCursor.next()
      webLogin  = new WebLogin()
      webLogin.login_id  = dbObject.get("login_id").toString()
      webLogin.login  = dbObject.get("login").toString()
      webLogin.password  = dbObject.get("password").toString()
      webLogin.url  = dbObject.get("url").toString()
      webLoginResult1.add(webLogin);
    }
    return webLoginResult1;
  }
  
  def removeWebLogin(uid:String , lid:String) = {
    var removeQuery:BasicDBObject = new BasicDBObject("user_id",uid).
    append("login_id", lid)
	webLoginCollection  .remove(removeQuery)
  }
  
  //-----------------Bank Account
  def createBankAccount(bankAccount:BankAccount):BankAccount = {
    var o:BasicDBObject = new BasicDBObject("user_id",bankAccount.user_id).
    append("ba_id", bankAccount.ba_id ).
    append("account_name", bankAccount.account_name ).
    append("routing_number", bankAccount.routing_number ).
    append("account_number", bankAccount.account_number )
    bankAccountCollection  .insert(o);
    return bankAccount;
  }
 
  def findBankAccount(uid:String):ArrayList[BankAccount] = {
    val bankAccountResult1 = new ArrayList[BankAccount]
    var o:BasicDBObject = new BasicDBObject("user_id",uid )
    var bankAccount:BankAccount = null
    var dbCursor:DBCursor = bankAccountCollection .find(o)
    while(dbCursor.hasNext()){
      var dbObject:DBObject = dbCursor.next()
      bankAccount  = new BankAccount()
      bankAccount.ba_id   = dbObject.get("ba_id").toString()
      bankAccount.account_name   = dbObject.get("account_name").toString()
      bankAccount.account_number   = dbObject.get("account_number").toString()
      bankAccount.routing_number   = dbObject.get("routing_number").toString()
      bankAccountResult1.add(bankAccount);
    }
    return bankAccountResult1;
  }
  
  def removeBankAccount(uid:String , baid:String) = {
    var removeQuery:BasicDBObject = new BasicDBObject("user_id",uid).
    append("ba_id", baid)
	bankAccountCollection .remove(removeQuery)
  }
  
}