package com.Drools.Service


import com.Drools.Entity.Agent
import org.drools.compiler.compiler.io.File
import org.kie.api.KieBase
import org.kie.api.KieServices
import org.kie.api.builder.KieFileSystem
import org.kie.api.builder.KieScanner
import org.kie.api.builder.ReleaseId
import org.kie.api.event.rule.AfterMatchFiredEvent
import org.kie.api.event.rule.BeforeMatchFiredEvent
import org.kie.api.event.rule.DefaultAgendaEventListener
import org.kie.api.io.ResourceType
import org.kie.api.runtime.Calendars
import org.kie.api.runtime.KieContainer
import org.kie.api.runtime.KieSession
import org.kie.api.time.Calendar
import org.kie.internal.io.ResourceFactory
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.FileInputStream
import java.time.LocalDateTime


@Service
class AgentService {

    val logger:Logger = LoggerFactory.getLogger("AgentService")
    val  kieServices : KieServices = KieServices.Factory.get()
    var releaseId = kieServices.newReleaseId("com.Drools","DynamicDrools","1.0-SNAPSHOT")
    var kieContainer = kieServices.newKieContainer(releaseId)




    fun agentValidation(agent: Agent):Agent{

        // KieFileSystem is responsible for gathering resources
        // KieFileSystem is responsible for gathering resources
        //val kfs: KieFileSystem = kieServices.newKieFileSystem()

        // You can add your DRL files as InputStream here

        // You can add your DRL files as InputStream here
            //kfs.write(ResourceFactory.newClassPathResource("Rules/AgentRules.drl"))
       // kfs.write(ResourceFactory.newFileResource("C://Users//Mayank_Singh//Desktop//Drools-DayToDay/AgentStateRule.drl"))
        //kfs.write("C://Users//Mayank_Singh//Desktop//Drools-DayToDay/AgentStateRule.drl" , ResourceFactory.newFileResource("AgentStateRule.drl"));
        // You need to specify a unique ReleaseId per KieContainer (= the unit which you store a set of DRL files)
        // ReleaseId consists of "GroupId" + "ArtifactId" + "Version". The same idea of Maven artifact.

        // You need to specify a unique ReleaseId per KieContainer (= the unit which you store a set of DRL files)
        // ReleaseId consists of "GroupId" + "ArtifactId" + "Version". The same idea of Maven artifact.
        //val releaseId: ReleaseId = kieServices.newReleaseId("com.Drools","DynamicDrools","1.0-SNAPSHOT")
       // kfs.generateAndWritePomXML(releaseId)

        // Now resources are built and stored into an internal repository

        // Now resources are built and stored into an internal repository
        //kieServices.newKieBuilder(kfs).buildAll()

        // You can get a KieContainer with the ReleaseId

        // You can get a KieContainer with the ReleaseId
        //val kieContainer: KieContainer = kieServices.newKieContainer(releaseId)

        //val kieSession:KieSession = kieContainer.newKieSession()


        // kmodule.xml approach to initialize releaseId , KieContainer , kieScanner and KieSession
        var kieScanner:KieScanner = kieServices.newKieScanner(kieContainer)
        kieScanner.start(100L)

        var kieSession:KieSession = kieContainer.newKieSession("ksession-rule")

        eventListner(kieSession)
        logger.info("Before Inserting object into session ${LocalDateTime.now()}")
        //println("Before Inserting object into session ${LocalDateTime.now()}")
        kieSession.insert(agent)
        logger.info("After inserting object into session ${LocalDateTime.now()}")
        logger.info("Before Firing rules ${LocalDateTime.now()}")
        //println("After inserting object into session ${LocalDateTime.now()}")
        //println("Before Firing rules ${LocalDateTime.now()}")
        //var startTime:Long = System.nanoTime()
        var TotalRulesFired:Int = kieSession.fireAllRules()
        //var endTime:Long = System.nanoTime()
        logger.info("Total Rules Fired - ${TotalRulesFired}")
        logger.info("After Firing rules ${LocalDateTime.now()}")
        //println("After Firing rules ${LocalDateTime.now()}")
        //println("Time to fire rule ${endTime-startTime}")

        return agent

    }

    fun agentStateValidation(agent: Agent):Agent
    {
        var kieScanner = kieServices.newKieScanner(kieContainer)
        kieScanner.start(100L)

        var kieSession:KieSession = kieContainer.newKieSession("ksession1")
        eventListner(kieSession)

        logger.info("Before Inserting object into session ${LocalDateTime.now()}")
        //println("Before Inserting object into session ${LocalDateTime.now()}")
        kieSession.insert(agent)
        logger.info("After inserting object into session ${LocalDateTime.now()}")
        logger.info("Before Firing rules ${LocalDateTime.now()}")
        //println("After inserting object into session ${LocalDateTime.now()}")
        //println("Before Firing rules ${LocalDateTime.now()}")
        //var startTime:Long = System.nanoTime()
        var TotalRulesFired :Int = kieSession.fireAllRules()
        //var endTime:Long = System.nanoTime()
        logger.info("Total Rules Fired - ${TotalRulesFired}")
        logger.info("After Firing rules ${LocalDateTime.now()}")
        //println("After Firing rules ${LocalDateTime.now()}")
        //println("Time to fire rule ${endTime-startTime}")

        return agent

    }


    fun eventListner(kSession: KieSession)
    {
        kSession?.addEventListener(object :DefaultAgendaEventListener(){
            override fun beforeMatchFired(event: BeforeMatchFiredEvent?) {
                super.beforeMatchFired(event)
                logger.info("Rule to be fired is : ${event?.match?.rule?.name}")
                logger.info("Timer Started")
                //println("Rule to be fired is : ${event?.match?.rule?.name}")
                //println("Timer Started")
                var startTime = LocalDateTime.now()
                logger.info("${startTime}")
                //println("${startTime}")
            }
        })

        kSession?.addEventListener(object :DefaultAgendaEventListener(){
            override fun afterMatchFired(event: AfterMatchFiredEvent?) {
                super.afterMatchFired(event)
                logger.info("rule fired name is : ${event?.match?.rule?.name}")
                logger.info("Timer Stoped")
                //println("rule fired name is : ${event?.match?.rule?.name}")
                //println("Timer Stoped")
                var endTime:LocalDateTime = LocalDateTime.now()
                logger.info("${endTime}")
                //println("${endTime}")
            }
        })
    }

}


