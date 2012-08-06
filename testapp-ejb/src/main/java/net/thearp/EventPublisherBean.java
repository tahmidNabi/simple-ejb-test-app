package net.thearp;

import org.jboss.ejb3.annotation.LocalBinding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;
import java.util.Random;

@Stateless
//@LocalBinding(jndiBinding = "testapp/EventPublisherBean")
public class EventPublisherBean implements EventPublisher {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Resource(mappedName = "java:/JmsXA")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "topic/SimpleAppEvent")
    private Topic testAppEventTopic;

    private Random random = new Random();


    public void sendMessage() {

        Connection connection = null;

        try {
            connection = connectionFactory.createConnection();
            EventData eventData = getEventData();
            publishMessage(connection, eventData);
        } catch (JMSException e) {
            log.error("Error in closing JMS connection for TestAppEvent topic", e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    log.error("Error in closing JMS connection for AppEvent topic", e);
                }
            }
        }
    }

    private void publishMessage(Connection connection, EventData eventData) throws JMSException {

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageProducer producer = session.createProducer(testAppEventTopic);

        ObjectMessage message = session.createObjectMessage();

        message.setObject(eventData);
        log.debug("Sending JMS message:");


        producer.send(message);

        producer.close();
        session.close();

    }

    private EventData getEventData() {

        EventData eventData = new EventData();

        long timeStamp = System.currentTimeMillis();
        eventData.setEventGenerationTime(timeStamp);
        eventData.setEventId(getUniqueId(timeStamp));

        return eventData;
    }


    private String getUniqueId(long timeStamp) {
        StringBuilder sb = new StringBuilder();
        return sb.append(timeStamp)
                .append("-")
                .append(Thread.currentThread().getId())
                .append("-")
                .append(random.nextInt(1000000)).toString();
    }

}
