package textdecorators;

import textdecorators.util.InputDetails;
import textdecorators.util.MyLogger;
import textdecorators.util.FileProcessor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class KeywordDecorator extends AbstractTextDecorator{

    private AbstractTextDecorator atd;
	private InputDetails id;

    /***
     * Constructor of the KeywordDecorator class
     * @param atdIn - wrapping of the next decorator.
     * @param idIn  - input reference.
     */
	public KeywordDecorator(AbstractTextDecorator atdIn, InputDetails idIn) 
	{
        MyLogger.writeMessage("Inside KeywordDecorator constructor.", MyLogger.DebugLevel.KEYWORDDECORATOR);
		atd = atdIn;
		id = idIn;
	}

    /***
     * processInputDetails - for KeywordDecorator, it checks all the keyword present in the keyword.txt file.
     */
    @Override
    public void processInputDetails() {
		String b ="";
		try{
            File file = new File("keyword.txt");
			FileProcessor fileProcessor = new FileProcessor("keyword.txt");
		
    	while ((b = fileProcessor.poll()) != null) {
			int count =0;
        for (String a:id.getValue()) {
                 if(a.toLowerCase().contains(b.toLowerCase())){
                 if(a.contains("MOST_FREQUENT")){
                        a= a.replace("MOST_FREQUENT","");
                        a= a.replace("_","");
                        if(a.equalsIgnoreCase(b)){
                            a = "MOST_FREQUENT_KEYWORD_" + a + "_KEYWORD_MOST_FREQUENT";
						id.updateValue(count,a);
                        }
                }else if(a.contains(".")){
                    a = a.replace(".","");
                    if(a.equalsIgnoreCase(b)) {
                        a = "KEYWORD_" + a + "_KEYWORD";
                        a = a+".";
                    
						id.updateValue(count,a);
                    }
                }else if(a.contains(",")){
                    a = a.replace(",","");
                    if(a.equalsIgnoreCase(b)) {
                        a = "KEYWORD_" + a + "_KEYWORD";
                        a = a+",";
                    
						id.updateValue(count,a);
                    }
                }else if(a.equalsIgnoreCase(b)){
                    a = "KEYWORD_" + a + "_KEYWORD";
                    
					id.updateValue(count,a);
                }
            }
            count++; 
        }
        }
        /***
         * checking for the empty files.
         */
        if(file.length()== 0){
            System.out.println("Keyword File is empty");
            System.exit(0);
        }

		if (null != atd) 
		{
			atd.processInputDetails();
		}

		}
	
		catch (FileNotFoundException e) {
            System.out.println("Missing Keyword File");
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