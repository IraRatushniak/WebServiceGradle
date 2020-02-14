package util;

import javax.xml.ws.WebFault;

@WebFault(name = "BookService")
public class MissingBookNameException extends RuntimeException {
    public MissingBookNameException() {
        super("Book name is required.");
    }
}
