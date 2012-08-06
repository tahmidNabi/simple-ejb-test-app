package servlet;

import net.thearp.EventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by
 * User: tahmid
 * Date: 8/5/12
 * Time: 12:40 PM
 */
public class SimpleServlet extends HttpServlet {

    @EJB
    private EventPublisher eventPublisher;

    private final Logger log = LoggerFactory.getLogger(getClass());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.debug(" Request received");

        eventPublisher.sendMessage();

    }

}
