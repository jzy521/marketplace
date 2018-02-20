# marketplace
Thank you for taking time to review my code. I made two videos for the demo. Please make sure watch them. I put them on google drive, it is safe to download:  

https://drive.google.com/open?id=1WM9a_J25jUxwnW9ITyWApmYEaNdRB5Qe
1 first video forcus on system setup and architecture

https://drive.google.com/open?id=1X99ovd76nytdDeIiBjA-0bwMET9Qrj9Z
2 second video forcus on Rest API

please use mac or linux, becuase you are going to setup kafka for messaging, also all scripts are inside quote. 
(Note you can still do the demo without kafka setup.)
before you start setup local environment, please make sure you have java8 and maven installed.

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

Describe a cloud hosting plan for this service, incorporating scalability, stability, monitoring and disaster recovery.
In the production environment, I will use  three VMs on the cloud. 
1 hosting rest server
2 hosting kafka server
3 hosting database
if we have more users, QPS will increase accordingly, we can use load balancer to dispatch rest request evenly amount rest server. if we use database like cassandra, we can horizontally scale database. For Kafka server, we can also put differnt partitions in a topic on different node. this mean all these three servers can scale up. 
Kafka and cassandra has built in replica functionality, which is good for stability and disaster recovery.


