package com.hm.sortingcsvfile.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Class CSVSortingServiceUtils is used to read the data from CSV file and
 * Sort the data based on second Sort definition file.
 * 
 * @author IBM India PVT LTD
 * @version 1.1.0 * @author IBM India PVT LTD
 * @version 1.1.0
 */
public final class CSVSortingServiceUtils {

	/**
	 * readFileToSort method is used to read the data from CSV file.
	 *
	 * @param csvFile
	 *            the csv file
	 * @return the string[][]
	 * @throws URISyntaxException
	 *             the URI syntax exception
	 */
	public static String[][] readFileToSort(String csvFile) throws URISyntaxException {
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		String[][] input = null;
		try {
			if (csvFile != null && !csvFile.isEmpty()) {
				InputStream inputStream = loadResource(csvFile);
				if (inputStream != null) {
					input = new String[4][3];
					br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
					int i = 0;
					while ((line = br.readLine()) != null) {
						input[i] = line.split(cvsSplitBy);
						i++;
					}
				}
			} else {
				System.out.println("File Name should not empty");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return input;
	}

	/**
	 * Load resource is used to get the InputStream based on the filename.
	 *
	 * @param csvFile
	 *            the csv file
	 * @return the input stream
	 */
	public static InputStream loadResource(String csvFile) {
		URL resource = CSVSortingServiceUtils.class.getResource(csvFile.substring(1));
		File file = null;
		InputStream inputStream = null;
		try {
			if (resource != null) {
				file = Paths.get(resource.toURI()).toFile();
				inputStream = new FileInputStream(file);
			} else {
				System.out.println("Please enter a valid file name");
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return inputStream;
	}

	/**
	 * readSortOrderFile method is used to read the data from CSV file and
	 * performing sorting.
	 *
	 * @param csvFile
	 *            the csv file
	 * @param sortOrderCol
	 *            the sort order col
	 * @return the list
	 */
	public static List<String> readSortOrderFile(String csvFile, String sortOrderCol) {
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		List<String> input = null;
		try {
			if (csvFile != null && !csvFile.isEmpty()) {
				InputStream inputStream = loadResource(csvFile.substring(1));
				if (inputStream != null) {
					input = new ArrayList<String>();
					br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
					int i = 0;
					int sortColNo = -1;
					while ((line = br.readLine()) != null) {
						String[] colValues = line.split(cvsSplitBy);
						if (i == 0) {
							List<String> labelCol = Arrays.asList(colValues);
							sortColNo = labelCol.indexOf(sortOrderCol);
						} else {
							input.add(colValues[sortColNo]);
						}
						i++;
					}
				}
			} else {
				System.out.println("Not a valid csv file");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return input;
	}
}
