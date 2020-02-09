package handler;

import org.apache.log4j.Logger;
import util.MissingBookNameException;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
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
        // incoming request
        if (!isResponse) {
            logger.info("Request");
            logMessageToFile(context);
//            checkRequestMessage(context);
        } else {
            logger.info("Response");
            logMessageToFile(context);
//            checkResponseMessage(context);
        }
        return true;
    }

    private void logMessageToFile(SOAPMessageContext context) {
        SOAPMessage soapMsg = context.getMessage();
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            soapMsg.writeTo(baos);
            //log request or response to file
            logger.info(baos);
        } catch (SOAPException | IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkResponseMessage(SOAPMessageContext context) {
        try {
            SOAPMessage soapMessage = context.getMessage();
            final SOAPBody body = soapMessage.getSOAPBody();
            logger.info("Response: " + body.getFirstChild().getLocalName());
            //verify not empty
            final String responseText = body.getTextContent();
            return true;
        } catch (SOAPException e) {
            e.printStackTrace();
        }
        return true;
    }

//    // Check ConsumerApplication
//    private boolean checkRequestMessage(SOAPMessageContext context) {
//        try {
//            SOAPMessage soapMessage = context.getMessage();
//            final SOAPBody body = soapMessage.getSOAPBody();
//            final String textFromBody = body.getTextContent();
////            System.out.println(param3);
////            if (param3==null){
////                throw new MissingBookNameException();
////            }
//
////            SOAPMessage message = context.getMessage();
////            SOAPBody body = message.getSOAPBody();
////            SOAPElement requestElt =
////                    ((SOAPElement) body.getFirstChild());
//
////            System.out.println("SOAPElement " +  requestElt);
//
////            SOAPMessage soapMessage = context.getMessage();
////            SOAPPart soapPart = soapMessage.getSOAPPart();
////            SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
////            String soapBody = soapEnvelope.getBody().getLocalName();
////            SOAPHeader soapHeader = soapEnvelope.getHeader();
//            SOAPMessage soapMsg = context.getMessage();
//            System.out.println();
//            try {
//            } catch (MissingBookNameException e) {
//                generateSOAPErrMessage(soapMsg, "Book name is missing");
//            }
//
////            System.out.println(soapHeader);
////            JAXBContext jc = JAXBContext.newInstance(AppicationsInfoRequest.class.getPackage().getName());
////            JAXBSource source = new JAXBSource(jc, requestElt);
////
////            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
////            Schema schema = schemaFactory.newSchema(new StreamSource(AppicationsInfoRequest.class.getClassLoader().getResource("xsd/application-store.xsd").toURI().toString()));
////
////            System.out.println("Schema " +  schema.toString());
////
////            Validator validator = schema.newValidator();
////            validator.setErrorHandler(new SchemaValidationErrorHandler());
////            validator.validate(source);
//
//        } catch (SOAPException e) {
//            System.err.println(e);
//        }
//        return true;
//    }
//
//    private void generateSOAPErrMessage(SOAPMessage msg, String reason) {
//        try {
//            SOAPBody soapBody = msg.getSOAPPart().getEnvelope().getBody();
//            SOAPFault soapFault = soapBody.addFault();
//            soapFault.setFaultString(reason);
//            throw new SOAPFaultException(soapFault);
//        } catch (SOAPException e) {
//        }
//    }

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