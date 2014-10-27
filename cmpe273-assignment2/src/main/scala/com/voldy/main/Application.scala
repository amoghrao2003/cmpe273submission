package com.voldy.main

import org.springframework.context.annotation.ComponentScan
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.SpringApplication
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Bean
import org.springframework.web.filter.ShallowEtagHeaderFilter
import javax.servlet.Filter



@Configuration
@EnableAutoConfiguration
@ComponentScan
class Application
object SampleWebApplication extends App {
  SpringApplication.run(classOf[Application]);
}



