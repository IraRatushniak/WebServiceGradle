package handler;

import org.w3c.dom.Node;
import org.apache.log4j.Logger;
import util.MissingBookNameException;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Handler implements SOAPHandler<SOAPMessageContext> {
    Logger logger = Logger.getLogger(Handler.class);

    private String SERVICE_CONSUMER = "serviceconsumer";

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        Boolean isResponse = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (!isResponse) {
            logger.info("Request");
            logMessageToFile(context);
        } else {
            logger.info("Response");
            logMessageToFile(context);
            checkResponseMessage(context);
        }
        return true;
    }

    private void logMessageToFile(SOAPMessageContext context) {
        SOAPMessage soapMsg = context.getMessage();
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            soapMsg.writeTo(baos);
            logger.info(baos);
        } catch (SOAPException | IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkResponseMessage(SOAPMessageContext context) {
        try {
            SOAPMessage soapMessage = context.getMessage();
            final SOAPBody body = soapMessage.getSOAPBody();
            String response = body.getFirstChild().getLocalName();
            final String responseText = body.getTextContent();
            //verify not empty
            if (response.equals("getBookResponse") && responseText.isEmpty()){
                generateSOAPErrMessage(soapMessage, "There is no book with this name");
            } else if (response.equals("getAllBookResponse") && responseText.isEmpty()) {
                generateSOAPErrMessage(soapMessage, "There is no book in the library");
            } else if (response.equals("removeBookResponse") ||
                    response.equals("updateBookResponse") && responseText.equals("false")) {
                generateSOAPErrMessage(soapMessage, "There is no book with this name");
            }
            return true;
        } catch (SOAPException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void generateSOAPErrMessage(SOAPMessage msg, String reason) {
        logger.info("Fault (reason:" + reason + ")");
        try {
            SOAPBody soapBody = msg.getSOAPPart().getEnvelope().getBody();
            SOAPFault soapFault = soapBody.addFault();
            System.out.println(1);
            soapFault.setFaultString(reason);
            System.out.println(2);
            throw new SOAPFaultException(soapFault);
        } catch (SOAPException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return false;
    }

    @Override
    public void close(MessageContext context) {

    }

    @Override
    public Set<QName> getHeaders() {
        return null;
    }
}