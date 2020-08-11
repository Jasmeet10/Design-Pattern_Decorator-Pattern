package textdecorators;

import textdecorators.util.InputDetails;
import textdecorators.util.MyLogger;
import textdecorators.util.FileProcessor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SpellCheckDecorator extends AbstractTextDecorator{

    private AbstractTextDecorator atd;
	private InputDetails id;

    /***
     * Constructor of the SpellCheckDecorator class
     * @param atdIn - wrapping of the next decorator.
     * @param idIn  - input reference.
     */
	public SpellCheckDecorator(AbstractTextDecorator atdIn, InputDetails idIn) 
	{
        MyLogger.writeMessage("Inside SpellCheckDecorator constructor.", MyLogger.DebugLevel.SPELLCHECKDECORATOR);
		atd = atdIn;
		id = idIn;
	}
    /***
	 * processInputDetails - for SpellCheckDecorator, it checks all the misspelled words present in the misspelled.txt file. 
	 */
    @Override
    public void processInputDetails() {
        String b ="";
		try{
            File file = new File("misspelled.txt");
			FileProcessor fileProcessor = new FileProcessor("misspelled.txt");
		
    	while ((b = fileProcessor.poll()) != null) {
			 int count = 0;
        for (String a:id.getValue()) {
            
            if(a.toLowerCase().contains(b.toLowerCase())){
                 if(a.equalsIgnoreCase(b)){
                        a = "SPELLCHECK_" + a + "_SPELLCHECK";
                        id.updateValue(count,a);
                    }
                    else if((a.contains("MOST_FREQUENT"))&& (a.contains("KEYWORD"))){    
                    a= a.replace("MOST_FREQUENT","");
                    a = a.replace("KEYWORD", "");
                    a = a.replace("_", "");
                    a= a.replace("_","");
                    if(a.equalsIgnoreCase(b)){
                        a = a.replace(b,"MOST_FREQUENT_KEYWORD_SPELLCHECK_" + b + "_SPELLCHECK_KEYWORD_MOST_FREQUENT");
                        id.updateValue(count,a);
                    }
                }else if(a.contains("KEYWORD")){
                    a= a.replace("KEYWORD","");
                    a= a.replace("_","");
                    if(a.equalsIgnoreCase(b)){
                        a = a.replace(b,"KEYWORD_SPELLCHECK_" + b + "_SPELLCHECK_KEYWORD");
                        id.updateValue(count,a);
                    }
                }else if(a.contains("MOST_FREQUENT")){
                    a= a.replace("MOST_FREQUENT","");
                    a= a.replace("_","");
                    if(a.equalsIgnoreCase(b)){
                        a = a.replace(b,"MOST_FREQUENT_SPELLCHECK_" + b + "_SPELLCHECK_MOST_FREQUENT");
                        id.updateValue(count,a);
                    }
                }else if(a.contains(".")){
                    a = a.replace(".","");
                    if(a.equalsIgnoreCase(b)) {
                        a = "SPELLCHECK_" + a + "_SPELLCHECK";
                        a = a+".";
                    
						id.updateValue(count,a);
                    }
                }else if(a.contains(",")){
                    a = a.replace(",","");
                    if(a.equalsIgnoreCase(b)) {
                        a = "SPELLCHECK_" + a + "_SPELLCHECK";
                        a = a+",";
                    
						id.updateValue(count,a);
                    }
                } 
            } 
            count++;
        }
        }
        /***
         * checking for the empty files.
         */
        if(file.length()== 0){
            System.out.println("Misspelled File is empty");
            System.exit(0);
        } 
		
		if (null != atd) 
		{
			atd.processInputDetails();
		}

		}
	
		catch (FileNotFoundException e) {
            System.out.println("Missing Misspelled File");
            System.exit(0);
        }
		catch (IOException e) {
            System.out.println("IO Exception");
            System.exit(0);
        } 

    }
    /**
     * Formatting of the data
     */
    public String toString() {
        return null;
    }
    
}