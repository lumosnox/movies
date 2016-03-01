package iristk.app.movies;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
	private static int didNotUnderstand;
	private static int notSureEnough;
	private static StringBuffer sb;
	
	public Logger(){
		didNotUnderstand =0;
		notSureEnough=0;
		sb=new StringBuffer();
	}
	
	public void log(){
		didNotUnderstand++;
	}
	public void logUnsure(){
		notSureEnough++;
	}
	
	public void logMessage(String message){
		sb.append(message);
		sb.append("\n");
	}
	
	public static int getDidNotUnderstand(){
		return didNotUnderstand;
	}
	
	public static int getUnsure(){
		return notSureEnough;
	}
	
	public static StringBuffer getMessageLog(){
		return sb;
	}
	
	public static void writeSessionLog(){
		String fileName="LogFile.txt";
		try {
            //open file for appending
            FileWriter fileWriter =
                new FileWriter(fileName,true);
       
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);

            bufferedWriter.write("New Session---");
            bufferedWriter.newLine();
            bufferedWriter.write(" Total times the system did not understand the user was: "+getDidNotUnderstand());
            bufferedWriter.write(" Total times the system was not sure enough: "+getUnsure());
            bufferedWriter.newLine();
            bufferedWriter.write(" The log is as follows: ");
            bufferedWriter.newLine();
            bufferedWriter.write(getMessageLog().toString());
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            
            //close files
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + fileName + "'");
        }
		
	}

}
