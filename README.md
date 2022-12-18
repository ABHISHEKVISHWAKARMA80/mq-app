Send message from java code (in eclipse) to MQ

Prerequisites:
Download and install Eclipse and MQ explorer.

Steps to setup this projects in your system:
1. Download this project named "mq-app"
2. Import in the eclipse
3. The project has two main files, MQFirstAppApplication.java and application.properties.

In the application.properties files, replaced these fields according to your system:
ibm.mq.queueManager=YOUR_QUEUE_MANAGER_NAME
ibm.mq.channel=YOUR_SERVER_CONNECTION_CHANNEL_NAME
ibm.mq.connName=HOST_IP_ADDRESS(YOUR_PORT_NO)

ibm.mq.user=YOUR_USER_NAME

ibm.mq.password=YOUR_USER_NAME_PASSWORD

In the MQFirstAppApplication.java file, for below code lines, give name to your defined queue name:
 jmsTemplate.convertAndSend("YOUR_QUEUE_NAME", "MQ Client");
 return jmsTemplate.receiveAndConvert("YOUR_QUEUE_NAME").toString();
 
 Please refer to these docs if you are not familiar with IBM MQ:
 https://www.ibm.com/docs/en/ibm-mq/9.2?topic=explorer-mq-tutorials
 
 Run the project:
 1. Open MQFirstAppApplication.java file
 2. Run as Java Application
 3. Open the browser
 4. Enter this url: http://localhost:8080/send
 5. Press enter, then message will go the defined queue.
 
----------------------------------------------------------------------------------------------------------------------------------------------------------
 
Send message from Eclipse (in client Window server 9.46.97.147) to MQ server (in Linux server 9.46.72.151):
 
 
1) Run below commands in Linux (here the Linux is MQ server): 

crtmqm ECLIPSE.TO.LINUX 

strmqm ECLIPSE.TO.LINUX 

runmqsc ECLIPSE.TO.LINUX 
define listener(ECLIPSE.TO.LINUX.LISTENER) trptype(tcp) port(1426) control(qmgr) 
START LISTENER(ECLIPSE.TO.LINUX.LISTENER) 
define channel(ECLIPSE.TO.LINUX.CHL) chltype(svrconn)  
define channel(ECLIPSE.TO.LINUX.CHL) chltype(clntconn) conname('9.46.72.151(1426)') qmname(ECLIPSE.TO.LINUX) 
DEFINE QLOCAL (QUEUE.LOCAL) 

sudo usermod -a -G mqm root
setmqaut -m ECLIPSE.TO.LINUX -t qmgr -p root +all 
setmqaut -m ECLIPSE.TO.LINUX -n QUEUE.LOCAL -t q -p root +all 


2) Changes in the windows server (in eclipse):

In application.properties:
ibm.mq.queueManager=ECLIPSE.TO.LINUX
ibm.mq.channel=ECLIPSE.TO.LINUX.CHL
ibm.mq.connName=9.46.72.151(1426)
ibm.mq.user=root
ibm.mq.password=your_password

Mention your queue name in "convertAndSend" method in MQFirstAppApplication.java file:
jmsTemplate.convertAndSend("QUEUE.LOCAL", "abhi");
jmsTemplate.receiveAndConvert("QUEUE.LOCAL").toString();


Run the java program.

Check the sent message in Linux server using below command:
cd /opt/mqm/samp/bin 
./amqsget QUEUE.LOCAL ECLIPSE.TO.LINUX 
