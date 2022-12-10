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

