package net.thearp;

import javax.ejb.Local;

/**
 * @author mubin
 * @since 6/20/12 11:48 AM
 */
@Local
public interface EventPublisher {
    public void sendMessage();
}
