package com.voldy.main

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestBody
import com.voldy.beans.User
import com.voldy.apiImpl.WalletImpl
import java.util.concurrent.atomic.AtomicLong
import java.util.Date
import org.springframework.web.bind.annotation.PathVariable
import com.voldy.apiImpl.WalletImpl
import com.voldy.beans.IDCard
import com.voldy.beans.IDCard
import com.voldy.beans.IDCard
import com.voldy.beans.IDCard
import com.voldy.beans.IDCard
import scala.collection.mutable.ListBuffer
import com.voldy.beans.IDCard
import com.voldy.beans.IDCard
import org.springframework.web.bind.annotation.ResponseBody
import com.voldy.beans.IDCard
import com.google.gson.Gson
import java.util.ArrayList
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMessages
import org.springframework.validation.FieldError
import java.util.List
import org.springframework.validation.ObjectError
import com.voldy.exception.ErrorMessage
import javax.validation.Valid
import com.voldy.beans.WebLogin
import com.voldy.beans.WebLogin
import com.voldy.beans.BankAccount
import com.voldy.beans.WebLogin
import com.voldy.beans.WebLogin
import com.voldy.beans.WebLogin
import com.voldy.beans.BankAccount
import com.voldy.beans.BankAccount
import com.voldy.beans.WebLogin
import com.voldy.beans.WebLogin
import com.voldy.beans.BankAccount
import com.voldy.beans.BankAccount
import com.voldy.beans.BankAccount
import org.springframework.web.filter.ShallowEtagHeaderFilter
import org.springframework.context.annotation.Bean
import javax.servlet.Filter

@Controller
@RestController
@RequestMapping(Array("/"))
class WalletController {
  val idCardList = new ArrayList[IDCard];
  val webLoginList = new ArrayList[WebLogin];
  val bankAccountList = new ArrayList[BankAccount];
  final var wallwtImpl:WalletImpl = new WalletImpl()
  final var userCounter:AtomicLong  = new AtomicLong();
  final var idCardCounter:AtomicLong  = new AtomicLong();
  final var webLoginCounter:AtomicLong  = new AtomicLong();
  final var bankaccountCounter:AtomicLong  = new AtomicLong();
  
  final var apiString:String  = "/api/v1";
  
  @RequestMapping(Array("/greeting"))
  def greeting(@RequestParam(value="name", required=false, defaultValue="World")  name:String) = {
    println("Hello World ".concat(name));
  }
  
  //---------------------USER-------------------------------------------
   
  //Create Users in the System
  @RequestMapping(value =Array("/api/v1/users"), method = Array(RequestMethod.POST))
  @ResponseStatus(HttpStatus.CREATED)
  def createUsers(@Valid @RequestBody  user:User):User = {
    user.id  = "u-"+userCounter.getAndIncrement().toString
    user.created_at  = new Date().toString()
    wallwtImpl.saveUsersToMap(user);
    return user;
  }
  
  //View the user
    @RequestMapping(value=Array("/api/v1/users/{user_id}"),method = Array(RequestMethod.GET))
    def viewUser(@PathVariable("user_id") id:String):User ={
    	var userResponse:User = wallwtImpl.userMap.get(id).get
    	return userResponse
    }
    
    //Update the user
    @RequestMapping(value=Array("/api/v1/users/{user_id}"),method = Array(RequestMethod.PUT))
    @ResponseStatus(HttpStatus.CREATED)
    def updateUser(@Valid  @RequestBody  user:User ,@PathVariable("user_id") id:String):User ={
    	println("Removing user with ID :"+id)
    	var userOld:User = wallwtImpl.userMap .remove(id).get
    	println("Old Password :"+userOld.password )
    	println("New Password :"+user.password )
    	user.updated_at = new Date().toString()
    	user.created_at  = userOld.created_at 
    	user.id  = userOld.id 
    	wallwtImpl.saveUsersToMap(user)
    	return user
    }
    
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    def handleException(ex:MethodArgumentNotValidException):ErrorMessage ={
        var fieldErrors:List[FieldError] = ex.getBindingResult().getFieldErrors()
        
        var globalErrors:List[ObjectError] = ex.getBindingResult().getGlobalErrors()
        
        
        var errors:List[String] = new ArrayList[String]
        var error:String = ""
        
         var it = fieldErrors.iterator()
        while(it.hasNext()){
          var fielfError:FieldError = it.next()
          error = fielfError.getField() + ", "+fielfError.getDefaultMessage()
          errors.add(error)
        }
        
        var it1 = globalErrors.iterator()
        while(it1.hasNext()){
          var globalError:ObjectError = it1.next()
          error = globalError.getObjectName() + ", "+globalError.getDefaultMessage()
          errors.add(error)
        }
        return new ErrorMessage(errors);
    }
    
    //---------------------ID CARD-------------------------------------------
    //Create ID card.. check data in request payload
    @RequestMapping(value =Array("/api/v1/users/{user_id}/idcards"), method = Array(RequestMethod.POST))
  	@ResponseStatus(HttpStatus.CREATED)
    def createIDCard(@Valid  @RequestBody  idCard:IDCard,@PathVariable("user_id") id:String):IDCard = {
	    println("Adding ID Card for user :"+id )
        idCard.card_id   = "c-"+idCardCounter .getAndIncrement().toString
        idCard.user_id = id;
        //idCard.isDeleted = false;
	    idCardList.add(idCard);
        //wallwtImpl.idCardMap.put(id, idCardList)
	    return idCard;
  	}
    
    //List all ID cards
    @RequestMapping(value =Array("/api/v1/users/{user_id}/idcards"), method = Array(RequestMethod.GET))
  	 def listAllIDCard(@PathVariable("user_id") id:String):ArrayList[IDCard] = {
      println("Inside listAll card with cards "+idCardList.size())
      val idCardListResult = new ListBuffer[IDCard]
      val idCardListResult1 = new ArrayList[IDCard]
      val it = idCardList.iterator
      while(it.hasNext()){
        val idCardBean:IDCard  = it.next
        println("user id in IDcard bean "+idCardBean.user_id )
        if(idCardBean.user_id.equals(id)){
          //idCardListResult += idCardBean
          idCardListResult1.add(idCardBean)
        }
      }
        
      
     return idCardListResult1
  	}
    
    //Delete ID card
    @RequestMapping(value=Array("/api/v1/users/{user_id}/idcards/{card_id}"),method = Array(RequestMethod.DELETE))
  	@ResponseStatus(HttpStatus.NO_CONTENT) 
    def deleteIDCard(@PathVariable("user_id") id:String ,@PathVariable("card_id") cid:String ) = {
      println("Inside listAll card with cards "+idCardList.size())
      val itemsToRemoveList = new ArrayList[IDCard]
      val it = idCardList.iterator
      while(it.hasNext){
        val idCard:IDCard  = it.next
        if(idCard.user_id.equals(id) && idCard.card_id.equals(cid)){
        itemsToRemoveList.add(idCard);
        println("Removed ID card with id :"+idCard.card_id )
        }
      }
      idCardList.removeAll(itemsToRemoveList);
  	}
    
    //------------------- WEB LOGIN-----------------------
    
    //Create WEB LOGIN card.. check data in request payload
    @RequestMapping(value =Array("/api/v1/users/{user_id}/weblogins"), method = Array(RequestMethod.POST))
  	@ResponseStatus(HttpStatus.CREATED)
    def createWebLogin(@Valid  @RequestBody  webLogin:WebLogin,@PathVariable("user_id") id:String):WebLogin = {
	    println("Adding Web Login for user :"+id )
        webLogin.login_id    = "l-"+webLoginCounter  .getAndIncrement().toString
        webLogin.user_id = id;
        //idCard.isDeleted = false;
	    webLoginList .add(webLogin);
        //wallwtImpl.idCardMap.put(id, idCardList)
	    return webLogin;
  	}
    
     //List all web sites login 
    @RequestMapping(value =Array("/api/v1/users/{user_id}/weblogins"), method = Array(RequestMethod.GET))
  	 def listAllWebLogins(@PathVariable("user_id") id:String):ArrayList[WebLogin] = {
      println("Inside listAll webLogins "+webLoginList .size())
      val webLoginResult = new ArrayList[WebLogin]
      val it = webLoginList .iterator
      while(it.hasNext()){
        val webLoginBean:WebLogin  = it.next
        println("user id in web login bean "+webLoginBean.user_id )
        if(webLoginBean.user_id.equals(id)){
          webLoginResult.add(webLoginBean)
        }
      }
     return webLoginResult
  	}
    //Delete Web Login
    @RequestMapping(value=Array("/api/v1/users/{user_id}/weblogins/{login_id}"),method = Array(RequestMethod.DELETE))
  	@ResponseStatus(HttpStatus.NO_CONTENT)  
    def deleteWebLogin(@PathVariable("user_id") id:String ,@PathVariable("login_id") lid:String ) = {
      println("Inside weblogins "+webLoginList  .size())
      val itemsToRemoveList = new ArrayList[WebLogin]
      val it = webLoginList.iterator
      while(it.hasNext){
        val webLogin:WebLogin  = it.next
        if(webLogin.user_id.equals(id) && webLogin.login_id.equals(lid)){
        itemsToRemoveList.add(webLogin);
        println("Removed web login:"+webLogin.login_id )
        }
      }
      webLoginList.removeAll(itemsToRemoveList);
  	}
    //---------------------BANK ACCOUNT-------------------------------------------
    
    //Create BANK ACCOUNT 
    @RequestMapping(value =Array("/api/v1/users/{user_id}/bankaccounts"), method = Array(RequestMethod.POST))
  	@ResponseStatus(HttpStatus.CREATED)
    def createBankAccount(@Valid @RequestBody  bankAccount:BankAccount,@PathVariable("user_id") id:String):BankAccount = {
	    println("Adding Bank account for user :"+id )
        bankAccount.ba_id    = "b-"+bankaccountCounter.getAndIncrement().toString
        bankAccount.user_id = id;
        //idCard.isDeleted = false;
	    bankAccountList .add(bankAccount);
        //wallwtImpl.idCardMap.put(id, idCardList)
	    return bankAccount;
  	}
    
     //List all bank accounts 
    @RequestMapping(value =Array("/api/v1/users/{user_id}/bankaccounts"), method = Array(RequestMethod.GET))
  	 def listAllBankAccounts(@PathVariable("user_id") id:String):ArrayList[BankAccount] = {
      println("Inside listAll bank account "+bankAccountList .size())
      val bankAccountResultList = new ArrayList[BankAccount]
      val it = bankAccountList .iterator
      while(it.hasNext()){
        val bankAccountBean:BankAccount  = it.next
        println("user id in bank account bean "+bankAccountBean.user_id )
        if(bankAccountBean.user_id.equals(id)){
          bankAccountResultList.add(bankAccountBean)
        }
      }
     return bankAccountResultList
  	}
    
    
   //Delete Web Login
    @RequestMapping(value=Array("/api/v1/users/{user_id}/bankaccounts/{ba_id}"),method = Array(RequestMethod.DELETE))
  	@ResponseStatus(HttpStatus.NO_CONTENT)  
    def deleteBankAccount(@PathVariable("user_id") id:String ,@PathVariable("ba_id") baid:String ) = {
      println("Inside bankAccounts "+bankAccountList .size())
      val itemsToRemoveList = new ArrayList[BankAccount]
      val it = bankAccountList.iterator
      while(it.hasNext){
        val bankAccount:BankAccount  = it.next
        if(bankAccount.user_id.equals(id) && bankAccount.ba_id.equals(baid)){
        itemsToRemoveList.add(bankAccount);
        println("Removed bank account :"+bankAccount.ba_id )
        }
      }
      bankAccountList.removeAll(itemsToRemoveList);
  	}
    
    @Bean
  def etagFilter(): Filter = {
    val shallowEtagHeaderFilter = new ShallowEtagHeaderFilter()
    return shallowEtagHeaderFilter
  }
  }
  
