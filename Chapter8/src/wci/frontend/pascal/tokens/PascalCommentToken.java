package wci.frontend.pascal.tokens;

import wci.frontend.*;
import wci.frontend.pascal.*;

import static wci.frontend.Source.EOF;
import static wci.frontend.pascal.PascalErrorCode.UNEXPECTED_EOF;
import static wci.frontend.pascal.PascalTokenType.*;

//The whole class is added by ourselves

public class PascalCommentToken extends PascalToken{
	/**
     * Constructor.
     * @param source the source from where to fetch the token's characters.
     * @throws Exception if an error occurred.
     */
    public PascalCommentToken(Source source)
        throws Exception
    {
        super(source);
    }
    
    protected void extract() throws Exception {
    	
        StringBuilder textBuffer = new StringBuilder();

    	char currentChar = currentChar();
    	
    	
		    if (currentChar == '{') {
		        do {
		        	textBuffer.append(currentChar);
		            currentChar = nextChar();  // consume comment characters
		           
		        } while ((currentChar != '}') && (currentChar != EOF));
		
		        // Found closing '}'?
		        if (currentChar == '}') {
		        	textBuffer.append(currentChar);
		            currentChar = nextChar();  // consume the '}'
		            type = COMMENT;  
		        } else {
		            type = ERROR;
		            value = UNEXPECTED_EOF;
		        }
		        text = textBuffer.toString();
		    }
    }
}
