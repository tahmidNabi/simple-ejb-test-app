package net.thearp;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.jboss.ejb3.annotation.Depends;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by
 * User: tahmid
 * Date: 8/5/12
 * Time: 12:33 PM
 */

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "topic/SimpleAppEvent")
})
@Depends("jboss.messaging.destination:service=Topic,name=SimpleAppEvent")
public class SimpleMDB implements MessageListener {

    private final Logger log = LoggerFactory.getLogger(getClass());

    public void onMessage(Message message) {
        EventData eventData = null;

        try {
            eventData = (EventData) ((ObjectMessage) message).getObject();
            System.out.println("Received EventData: " + eventData.getEventId() + " generated at " + eventData.getEventGenerationTime());
            log.debug("Received EventData: " + eventData.getEventId() + " generated at " + eventData.getEventGenerationTime());

            //em.persist(eventData);
        } catch (JMSException e) {
            log.error("Error in retrieving JMS message, EventData:" + eventData, e);
        }
    }
}
