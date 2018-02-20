# marketplace
I made two videos for demo  
1 first video forcus on system setup and architecture
2 second video forcus on Rest API

please use mac or linux, becuase you are going to setup kafka for messaging, also all scripts are inside quota. 
(Note you can still do the demo without kafka setup.)
before you start first video, please make sure you have java8 and maven installed.

First video content system setup:
   - create folder " mkdir demo ", then " cd demo "
   - download project using " git clone https://github.com/jzy521/marketplace.git "
   - " cd marketplace " , " tar -xzf kafka_2.11-1.0.0.tgz ", then " cd kafka_2.11-1.0.0 "
   - start zookeeper server : " bin/zookeeper-server-start.sh config/zookeeper.properties "
   - start kafka server : " bin/kafka-server-start.sh config/server.properties "
   - import project into eclipse 
   - run App.java 
   - go to http://localhost:8080/
   - go to http://localhost:8080/h2-console/

Second video content Rest Api demo:
   - go to http://localhost:8080/swagger-ui.html


The time the exercise took (after dev environment is set up)
   8 hours
Exercise Difficulty: Easy, Moderate, Difficult, Very Difficult
   Moderate
How did you feel about the exercise itself? (1 lowest, 10 highest—awesome way to assess coding ability)
   9
How do you feel about coding an exercise as a step in the interview process?  (1 lowest, 10 highest—awesome way to assess coding ability)
   9
