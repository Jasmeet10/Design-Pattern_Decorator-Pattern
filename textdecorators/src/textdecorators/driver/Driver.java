package textdecorators.driver;

import textdecorators.util.FileProcessor;
import textdecorators.util.InputDetails;
import textdecorators.util.MyLogger;
import textdecorators.KeywordDecorator;
import textdecorators.MostFrequentWordDecorator;
import textdecorators.SentenceDecorator;
import textdecorators.SpellCheckDecorator;
import textdecorators.AbstractTextDecorator;
import textdecorators.util.FileDisplayInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;


public class Driver {
    private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 5;
    
    public static void main(String[] args){
        
            String inputFilename = args[0];
            String outputFilename = args[3];
            String logger = args[4];
            String Returnline = "";

        try {
            
            /***
             * Valadating all the command line inputs.
             */
            if (args[0].isEmpty()) {
                System.out.println("input file name is empty, Check README for the usage!");
                System.exit(0);
            }
             if (args[1].isEmpty()) {
                System.out.println("misspelled file name is empty, Check README for the usage!");
                System.exit(0);
            }
            if (args[2].isEmpty()) {
                System.out.println("keywords file name is empty, Check README for the usage!");
                System.exit(0);
            }
            if (args[3].isEmpty()) {
                System.out.println("output file name is empty, Check README for the value usage!");
                System.exit(0);
            }
            if (args[4].isEmpty()) {
                System.out.println("Logger file name is empty, Check README for the usage!");
                System.exit(0);
            }

            if ((args.length != 5) || (args[0].equals("${input}")) || (args[1].equals("${misspelled}")) || (args[2].equals("${keyword}")) || (args[3].equals("${output}")) || (args[4].equals("${debug}")) ) {
               System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
                System.exit(0);
            }
            
            
            int debugValue = Integer.parseInt(logger);
            MyLogger.setDebugValue(debugValue);
            if(debugValue < 1 || debugValue > 5) {
                MyLogger.Message("WARNING!! The assigned debug value is out of range. Check README for the value usage!");
                System.exit(0);
            }

            File file = new File(args[0]);
            File file1 = new File(args[3]);
            file1.delete();
            
            
           
            /***
             * Processing the Decorator pattern.
             */

            InputDetails inputD = new InputDetails(inputFilename, outputFilename);
            
            FileProcessor fileProcessor = new FileProcessor(inputFilename);
            while ((Returnline = fileProcessor.poll()) != null) {
                
                inputD.setValue(Returnline);
            }
            /***
             * checking for the empty files.
             */
            if(file.length()== 0){
                System.out.println("Input File is empty");
                System.exit(0);
            }
             AbstractTextDecorator sentenceDecorator = new SentenceDecorator(null, inputD);
		    AbstractTextDecorator spellCheckDecorator = new SpellCheckDecorator(sentenceDecorator, inputD);
		    AbstractTextDecorator keywordDecorator = new KeywordDecorator(spellCheckDecorator, inputD);
            AbstractTextDecorator mostFreqWordDecorator = new MostFrequentWordDecorator(keywordDecorator, inputD); 

            mostFreqWordDecorator.processInputDetails();

            ((FileDisplayInterface) inputD).writeToFile();
    
        } 
        
         catch (FileNotFoundException e) {
            System.out.println("Missing Input File");
            System.exit(0);
        }
        catch (IOException e) {
            System.out.println("IO Exception");
            System.exit(0);
        } 
        catch (SecurityException e){
            System.out.println("You do not have the read permissions to the input file");
            System.exit(0);
        }
        catch (InvalidPathException e){
            System.out.println("Invalid path");
            System.exit(0);
        }  
            
    }
}