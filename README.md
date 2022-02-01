# Drools-Kotlin-SpringBoot-using-Kmodule.xml-

- Drools Integration with Kotlin Spring Boot Project.
- Agent is the bean Class.
- Controller contains API to validate agent.

- Rules are applied on the various use case of the agent ( for example rules on agent's country , agent's age and more).
- Agenda Event listner is implemented to get the information of the fired rules.
- Configuration of Drools is done with kmodule.xml(in resources/META-INF) and in Services .

- Specification 
  * Contains multiple kieSession.
  * Contains multiple rule files in a session.
  
- log4j2 logger is implemented for the logging and logs are maintained in the MyApp.log file 
- Configuration of log4j2 logger is implemented in the log4j2.xml file in resources.
