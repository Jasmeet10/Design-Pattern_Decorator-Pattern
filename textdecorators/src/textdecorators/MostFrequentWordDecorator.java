package textdecorators;

import textdecorators.util.InputDetails;
import textdecorators.util.MyLogger;

import java.util.Map;
import java.util.HashMap;
import java.util.Collections;


public class MostFrequentWordDecorator extends AbstractTextDecorator{

    private AbstractTextDecorator atd;
	private InputDetails id;

    /***
     * Constructor of the MostFrequentWordDecorator class
     * @param atdIn - wrapping of the next decorator.
     * @param idIn  - input reference.
     */
	public MostFrequentWordDecorator(AbstractTextDecorator atdIn, InputDetails idIn) 
	{
        MyLogger.writeMessage("Inside MostFrequentDecorator  constructor.", MyLogger.DebugLevel.MOSTFREQUENTDECORATOR);
		atd = atdIn;
		id = idIn;
	}
    /***
     * processInputDetails - for MostFrequentWordDecorator, it checks all the frequent words present in the input file.
     */
    @Override
    public void processInputDetails() {
        
		Map<String, Integer> newInput = new HashMap<>();
        String b ="";
        int count = 0;
        for (String a : id.getValue()) {

            if (newInput.containsKey(a.toLowerCase())) {

                int value = newInput.get(a.toLowerCase());
                newInput.put(a.toLowerCase(), value + 1);
            } else {
                newInput.put(a.toLowerCase(), 1);
            }
            newInput.remove("^");
        }
        int maxValueInMap = (Collections.max(newInput.values()));
        
        for (Map.Entry<String, Integer> entry : newInput.entrySet()) {
            if (entry.getValue() == maxValueInMap) {
                b = entry.getKey();
               
            }
        }

        for (String a: id.getValue()) {
            
            if(a.contains(".")){
                a = a.replace(".","");
                if(a.equalsIgnoreCase(b)){
                    a = "MOST_FREQUENT_" + a + "_MOST_FREQUENT";
                    id.updateValue(count,a);
                    
                }
            } else if(a.contains(",")){
                a = a.replace(",","");
                if(a.equalsIgnoreCase(b)){
                    a = "MOST_FREQUENT_" + a + "_MOST_FREQUENT";
                    id.updateValue(count,a);
                }
            } else if(a.equalsIgnoreCase(b)){
                a = "MOST_FREQUENT_" + a + "_MOST_FREQUENT";
                id.updateValue(count,a);
                
            }
            count++;
        }

		 if (null != atd) 
		{
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