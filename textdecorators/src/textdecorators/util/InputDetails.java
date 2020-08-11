package textdecorators.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import  java.io.File;
import java.io.IOException;
import java.io.FileWriter;



public class InputDetails implements FileDisplayInterface{

    /**
     * List to store the input file for processing.
     */
    List<String> inpuStrings =new ArrayList<>();
    
    String inputFilename = "";
    String outputFilename= "";
    
    /***
     * Constructor of the Inputdetails.
     * @param inputFilenameIn
     * @param outputFilenameIn
     */
    public InputDetails(String inputFilenameIn,String outputFilenameIn){
        this.inputFilename = inputFilenameIn;
        this.outputFilename = outputFilenameIn;
    }

    /***
     * setValue is help to add all the values from input file into the list.
     * @param returnString - String from the input file.
     */
    public void setValue(String returnString){
       
    /**
     * Checking any other string except from the allowed.
     */    
    Pattern p = Pattern.compile("[^a-zA-Z0-9.,^]", Pattern.CASE_INSENSITIVE);
    Matcher m = p.matcher(returnString);
    boolean check = m.find();
    if (check) {
        System.out.println("There is a special character in the input file.");
        System.exit(0);
    }
      inpuStrings.add(returnString);

   }

   /***
    * getValue method helps to retrieve all the data from the list.
    * @return - the data present in the list.
    */
   public List<String> getValue(){
       return inpuStrings;
   }

   /***
    * updateValue method helps to update the value in the ArrayList.
    * @param count - index of the list.
    * @param value - new value for the list.
    */
   public void updateValue(int count, String value){
       inpuStrings.set(count,value);
   }

   /***
    * writeToFile helps to write all the data into the output file.
    */
   public void writeToFile() {
       try {
            File file = new File(outputFilename);
            if(file.createNewFile());
            FileWriter fileWriter = new FileWriter(file); 
                
            for(int i = 0 ; i < ( getValue()).size(); i++){
                if(getValue().get(i).contains("^") && i != ( getValue()).size()-1){
                    fileWriter.write("\n");}
                else if(getValue().get(i).contains("^")){
                        fileWriter.write("\n");}
                else{
                    fileWriter.write(getValue().get(i) +" ");
                }  
            }
    
                fileWriter.flush();
                fileWriter.close();


        }catch(IOException e){
            System.out.println("An error occurred.");
        }

}
    /**
     * Formatting of the data
     */
    public String toString() {
        return null;
    }
}