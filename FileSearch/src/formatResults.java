import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;


public class formatResults {
	static final String masterFilePath = "/Users/ravikrishna/Documents/workspace/ECSE457/src/master4s.txt";
	static final String masterFileModified = "/Users/ravikrishna/Documents/workspace/ECSE457/src/formattedMasterResults.txt";

	static final String analyticalFilePath = "/Users/ravikrishna/Documents/workspace/ECSE457/src/ANALYTICAL_MPEG_5S.txt";
	static final String analyticalFileModified = "/Users/ravikrishna/Documents/workspace/ECSE457/src/formattedAnalyticalResults.txt";

	static final String finalResultsFilePath = "/Users/ravikrishna/Documents/workspace/ECSE457/src/FinalResults.txt";
	
	static final String removeSpecialCharacters = "*+#%b";

	static String line = null;
	static int numberOfMasterResults = 0;
	static int numberOfAnalyticalResults = 0;
	static int numberOfFinalResults = 0;
	
	
	
	public static void formatAllResults() {
		
		File fileMaster = new File(masterFilePath);
		File fileAnalytical = new File(analyticalFilePath);
		
		try {
			
			FileReader fileReaderMaster = new FileReader(fileMaster);
			BufferedReader bufferedReaderMaster = new BufferedReader(fileReaderMaster);
			
			FileReader fileReaderAnalytical = new FileReader(fileAnalytical);
			BufferedReader bufferedReaderAnalytical = new BufferedReader(fileReaderAnalytical);
			
			
			FileWriter fileWriterMaster = new FileWriter(masterFileModified);
			BufferedWriter bufferedWriterMaster = new BufferedWriter(fileWriterMaster);
			
			FileWriter fileWriterAnalytical = new FileWriter(analyticalFileModified);
			BufferedWriter bufferedWriterAnalytical = new BufferedWriter(fileWriterAnalytical);
			
			
			// to format master results
			
			while ((line = bufferedReaderMaster.readLine()) != null) {
				if (line.charAt(0) != removeSpecialCharacters.charAt(0) && line.charAt(0) != removeSpecialCharacters.charAt(1) && line.charAt(0) != removeSpecialCharacters.charAt(2) && line.charAt(0) != removeSpecialCharacters.charAt(3) && line.charAt(0) != removeSpecialCharacters.charAt(4)) {
					bufferedWriterMaster.write(line.replace(" ", ","));		//to get rid of all single spaces
					bufferedWriterMaster.newLine();
					numberOfMasterResults++;
				}
			}
			
			bufferedReaderMaster.close();
			bufferedWriterMaster.close();
			
			//to format analytical results
			
			while ((line = bufferedReaderAnalytical.readLine()) != null) {
				if (line.charAt(0) != removeSpecialCharacters.charAt(0) && line.charAt(0) != removeSpecialCharacters.charAt(1) && line.charAt(0) != removeSpecialCharacters.charAt(2) && line.charAt(0) != removeSpecialCharacters.charAt(3) && line.charAt(0) != removeSpecialCharacters.charAt(4)) {
					bufferedWriterAnalytical.write(line.trim().replaceAll(" +", ","));		//to get rid of all multiple spaces
					bufferedWriterAnalytical.newLine();
					numberOfAnalyticalResults++;
				}
			}
			
			bufferedReaderAnalytical.close();
			bufferedWriterAnalytical.close();
		}

		catch(FileNotFoundException ex) {
            System.out.println("Unable to open file");                
        }
		
        catch(IOException ex) {
            System.out.println("Error reading file");                  
        }
	}
	
	public static void createNewResults() {
		
		File fileFormattedMasterResults = new File(masterFileModified);
		File fileFormattedAnalyticalResults = new File(analyticalFileModified);
		
		try {
			FileReader fileFormattedReaderMaster = new FileReader(fileFormattedMasterResults);
			BufferedReader bufferedFormattedReaderMaster = new BufferedReader(fileFormattedReaderMaster);
			
			FileReader fileFormattedReaderAnalytical = new FileReader(fileFormattedAnalyticalResults);
			BufferedReader bufferedFormattedReaderAnalytical = new BufferedReader(fileFormattedReaderAnalytical);
			
			FileWriter fileWriterFinalResults = new FileWriter(finalResultsFilePath);
			BufferedWriter bufferedWriterFinalResults = new BufferedWriter(fileWriterFinalResults);
			
			String line1 = null;
			String line2 = null;
			String temp1 = null;
			String temp2 = null;
			String temp3 = null;
			String temp4 = null;
			
			System.out.println("///////////////");
			System.out.println("Computing final results...");
//			System.out.println("Master before comparing");
			
			//bufferedWriterFinalResults.write("not hello");
			
			/* for text files with 142000+ results 
			 */
			
			while ((line1 = bufferedFormattedReaderMaster.readLine()) != null) {
				
				//for master results
				temp1 = line1.substring(0, 39);
				temp3 = line1.substring(45, 46);
				
				FileReader fileFormattedReaderAnalytical1 = new FileReader(fileFormattedAnalyticalResults);
				BufferedReader bufferedFormattedReaderAnalytical1 = new BufferedReader(fileFormattedReaderAnalytical1);
				
				while ((line2 = bufferedFormattedReaderAnalytical1.readLine()) != null) {
					
					//for analytical results
					temp2 = line2.substring(0, 39);
					temp4 = line2.substring(45, 46);
					
					if (temp1.equals(temp2)) {
						
					//	System.out.println("OH YES");
					//for combining results
						bufferedWriterFinalResults.write(line1 + "," + line2);
						bufferedWriterFinalResults.newLine();
						numberOfFinalResults++;
					}			
					
				}
				bufferedFormattedReaderAnalytical1.close();
			}
			
			bufferedFormattedReaderMaster.close();
			bufferedFormattedReaderAnalytical.close();
			bufferedWriterFinalResults.close();
			
		}
			
		
		/*	for text files with 5 inputs
		*/
		
//			
//			while ((line1 = bufferedFormattedReaderMaster.readLine()) != null) {
//				
//				temp1 = line1.substring(0, 7);
//				temp3 = line1.substring(8, 10);
//				
//				FileReader fileFormattedReaderAnalytical1 = new FileReader(fileFormattedAnalyticalResults);
//				BufferedReader bufferedFormattedReaderAnalytical1 = new BufferedReader(fileFormattedReaderAnalytical1);
//				
//				while ((line2 = bufferedFormattedReaderAnalytical1.readLine()) != null) {
//					
//					temp2 = line2.substring(0, 7);
//					temp4 = line2.substring(8, 10);
//					
//					if (temp1.equals(temp2)) {
//						
//					//	System.out.println("OH YES");
//						bufferedWriterFinalResults.write(temp1 + ","  +temp3 + "," + temp4);
//						bufferedWriterFinalResults.newLine();
//						numberOfFinalResults++;
//			
//					}			
//					
//				}
//				bufferedFormattedReaderAnalytical1.close();
//			}
//			
//			System.out.println("///////////////");
//			
//			System.out.println("closing and flushing all the files from the buffer");
//			
//			bufferedFormattedReaderMaster.close();
//			bufferedFormattedReaderAnalytical.close();
//			bufferedWriterFinalResults.close();
//			
//		}
//	
		catch(FileNotFoundException ex) {
            System.out.println("Unable to open file");                
        }
		
        catch(IOException ex) {
            System.out.println("Error reading file");                  
        }
		
	}
	
//	
//	public static void generateFinalResults() {
//		
//		File fileFormattedMasterResults = new File(masterFileModified);
//		File fileFormattedAnalyticalResults = new File(analyticalFileModified);
//		
//		try {
//			
//			FileReader fileReaderMasterFormatted = new FileReader(fileFormattedMasterResults);
//			BufferedReader bufferedReaderMasterFormatted = new BufferedReader(fileReaderMasterFormatted);
//			
//			FileReader fileReaderAnalyticalFormatted = new FileReader(fileFormattedAnalyticalResults);
//			BufferedReader bufferedReaderAnalyticalFormatted = new BufferedReader(fileReaderAnalyticalFormatted);		
//			
//			FileWriter fileWriterFinalResult = new FileWriter(finalResultsFilePath);
//			BufferedWriter bufferedWriterFinalResult = new BufferedWriter(fileWriterFinalResult);			
//			
//			
//			String line1 = "";
//			String line2 = "";
//			String substring1 = ""; 
//			
//			while ((line1 = bufferedReaderMasterFormatted.readLine()) != null) {
//				System.out.println(line1);
//			}
//			
//			
//			/*
//			String line1 = "";
//			String line2 = "";
//			String temp1 = "";
//			String temp2 = "";
//			String MTTF = "";
//			String MTTFPrime = "";
//			while ((line1 = bufferedReaderMasterFormatted.readLine()) != null && (line2 = bufferedReaderAnalyticalFormatted.readLine()) != null) {
//				line1 = bufferedReaderMasterFormatted.readLine();
//				line2 = bufferedReaderAnalyticalFormatted.readLine();
//				
//				temp1 = line1.substring(0, 49);
//				temp2 = line2.substring(0, 49);
//				MTTF = line1.substring(63, 68);
//				MTTFPrime = line2.substring(67, 74);
//				
//				if (temp1.equals(temp2)) {
//					bufferedWriterFinalResult.write(temp1 + ","+ MTTF + "" + MTTFPrime);
//					numberOfFinalResults++;
//				}
//			}
//			*/
//			
//			bufferedReaderMasterFormatted.close();
//			bufferedReaderAnalyticalFormatted.close();
//			bufferedWriterFinalResult.close();
//			
//		}
//		
//		catch(FileNotFoundException ex) {
//            System.out.println("Unable to open file");                
//        }
//		
//        catch(IOException ex) {
//            System.out.println("Error reading file");                  
//        }
//		
//	}
	
	
	public static void main(String[] args) {
		
		formatAllResults();
		
		System.out.println("Number of master results are " + numberOfMasterResults);
		System.out.println("Number of analytical results are " + numberOfAnalyticalResults);
		
//		createNewResults();
		
		System.out.println("Number of final results are " + numberOfFinalResults);
	}
}
