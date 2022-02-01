package com.Drools

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class AgentApplication
     fun main(args : Array<String>){
           runApplication<AgentApplication>(*args)
     }