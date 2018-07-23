package com.hm.sortingcsvfile.main;

import java.util.Arrays;
import java.util.List;

/**
 * The Class SortingCSVFile is Used to sort the CSV Files.
 * @author IBM India PVT LTD
 * @version 1.1.0
 * 
 */
public class SortingCSVFile {
	
	/**
	 * The main method is used to get the input from Command line and
	 * instantiate the CSVSortingService for Sorting the file.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		// First Argument from Command line having name of the file
		String fileToSort = args[0];		
		
		/* Second argument from Command line having the column number to sort 
		 * csv file one		
		 */
		String columnToSort = args[1];
		
		// Third argument from command line having the name of the file
		String sortOrderDefinitionFile = args[2];
		
		/* Fourth argument from command line having the column name 
		 * to sort of csv file two
		 */
		String sortOrderdefinitionColumn = args[4];
		
		String input[][] = CSVSortingServiceUtils.readFileToSort(fileToSort);
		List<String> sortOrder = CSVSortingServiceUtils.readSortOrderFile(sortOrderDefinitionFile, sortOrderdefinitionColumn);
		List<String> colLabels = Arrays.asList(input[0]);
		String[] colArr = input[0];
		int indexToSort = colLabels.indexOf(columnToSort);		
		String output[] = new String[sortOrder.size()];
		for (int i = 1; i< input.length;i++){
			int sortIndex = sortOrder.indexOf(input[i][indexToSort]);
			output[sortIndex] = String.join(",", input[i]);
		}
		StringBuilder result = new StringBuilder();        
		for (int i=0; i < colArr.length; i++){
			if (i != colArr.length-1) {
				result.append(colArr[i]).append(",");
			} else {
				result.append(colArr[i]);
			}			
		}
		System.out.println(result);
		for (int i=0;i<output.length;i++){
			if (output[i] != null){
				System.out.println(output[i]);
			}
		}
	}

}
 