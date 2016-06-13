package designProject;
import java.io.*;


public class generateResults {
	static final String masterFilePath = "/Users/ravikrishna/Documents/workspace/designProject/src/designProject/master_mwd4s_rconvec.txt";
	static final String masterFileModified = "/Users/ravikrishna/Documents/workspace/designProject/src/designProject/formattedMasterResults.csv";

	static final String analyticalFilePath = "/Users/ravikrishna/Documents/workspace/designProject/src/designProject/exhaustive_4s_analytical.txt";
	static final String analyticalFileModified = "/Users/ravikrishna/Documents/workspace/designProject/src/designProject/formattedAnalyticalResults.csv";

	static final String finalResultsFilePath = "/Users/ravikrishna/Documents/workspace/designProject/src/designProject/FinalResults.txt";
	
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
				if (line.charAt(0) != removeSpecialCharacters.charAt(0) && line.charAt(0) != removeSpecialCharacters.charAt(1) && line.charAt(0) != removeSpecialCharacters.charAt(2)) {
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
	
	
	public static void generateFinalResults() {
		
		File fileFormattedMasterResults = new File(masterFileModified);
		File fileFormattedAnalyticalResults = new File(analyticalFileModified);
		
		try {
			
			FileReader fileReaderMasterFormatted = new FileReader(fileFormattedMasterResults);
			BufferedReader bufferedReaderMasterFormatted = new BufferedReader(fileReaderMasterFormatted);
			
			FileReader fileReaderAnalyticalFormatted = new FileReader(fileFormattedAnalyticalResults);
			BufferedReader bufferedReaderAnalyticalFormatted = new BufferedReader(fileReaderAnalyticalFormatted);		
			
			FileWriter fileWriterFinalResult = new FileWriter(finalResultsFilePath);
			BufferedWriter bufferedWriterFinalResult = new BufferedWriter(fileWriterFinalResult);			
			
			String line1 = "";
			String line2 = "";
			String temp1 = "";
			String temp2 = "";
			String MTTF = "";
			String MTTFPrime = "";
			while ((line1 = bufferedReaderMasterFormatted.readLine()) != null && (line2 = bufferedReaderAnalyticalFormatted.readLine()) != null) {
				line1 = bufferedReaderMasterFormatted.readLine();
				line2 = bufferedReaderAnalyticalFormatted.readLine();
				
				temp1 = line1.substring(0, 49);
				temp2 = line2.substring(0, 49);
				MTTF = line1.substring(63, 68);
				MTTFPrime = line2.substring(67, 74);
				
				if (temp1.equals(temp2)) {
					bufferedWriterFinalResult.write(temp1 + ","+ MTTF + "" + MTTFPrime);
					numberOfFinalResults++;
				}
			}
			
			bufferedReaderMasterFormatted.close();
			bufferedReaderAnalyticalFormatted.close();
			bufferedWriterFinalResult.close();
			
		}
		
		catch(FileNotFoundException ex) {
            System.out.println("Unable to open file");                
        }
		
        catch(IOException ex) {
            System.out.println("Error reading file");                  
        }
		
	}
	
	
	public static void main(String[] args) {
		formatAllResults();
		System.out.println("Number of master results are " + numberOfMasterResults);
		System.out.println("Number of analytical results are " + numberOfAnalyticalResults);
		System.out.println("Number of final results are " + numberOfFinalResults);
	}
}
