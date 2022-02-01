package com.Drools.Controller

import com.Drools.Entity.Agent
import com.Drools.Service.AgentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/agent")
class AgentController {

    @Autowired
    private val agentService : AgentService?=null

    @GetMapping("/home")
    fun AgentHome():String{

        return "Welcome Agent"
    }

    @PostMapping("/validate")
    fun AgentValidation(@RequestBody agent:Agent):Agent?{

        var newAgent:Agent? = agentService?.agentValidation(agent)
        return newAgent

    }

    @PostMapping("/validate/state")
    fun AgentStateValidation(@RequestBody agent:Agent):Agent?{
        var newAgent:Agent? = agentService?.agentStateValidation(agent)
        return newAgent
    }

}