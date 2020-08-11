package textdecorators;

import textdecorators.util.InputDetails;
import textdecorators.util.MyLogger;

public class SentenceDecorator extends AbstractTextDecorator{

	private AbstractTextDecorator atd;
	private InputDetails id;

	/***
     * Constructor of the SentenceDecorator class
     * @param atdIn - wrapping of the next decorator.
     * @param idIn  - input reference.
     */
	public SentenceDecorator(AbstractTextDecorator atdIn, InputDetails idIn) 
	{
		MyLogger.writeMessage("Inside SentenceDecorator constructor.", MyLogger.DebugLevel.SENTENCEDECORATOR);
		atd = atdIn;
		id = idIn;
	}

	/***
	 * processInputDetails - for SentenceDecorator, it add the BEGIN_SENTENCE__ and __END_SENTENCE, in all the file.
	 */
	@Override
	public void processInputDetails() {
		
		int count=0;
      
		int last = id.getValue().size()-2;
        for (String a:id.getValue()) {
            if(count==0){
                a = "BEGIN_SENTENCE__"+a;
				id.updateValue(count,a);
			}
            else if(count == last){
				
                a = a.replace(".","");
                a = a+"__END_SENTENCE.";
				id.updateValue(count,a);
                //break;
            }
            else if (a.contains(".")) {
           a = a.replace(".","");
            a = a +"__END_SENTENCE."+"BEGIN_SENTENCE__";
			id.updateValue(count,a);
            }
            count++;
        }
		
		if (null != atd) {
					atd.processInputDetails();
				} 
	}
	/**
     * Formatting of the data
     */
	public String toString() {
        return null;
    }
    
}