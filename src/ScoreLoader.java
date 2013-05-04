import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Writes and loads a list of Integers from a textfile
 * @author Daniel Ly
 * @version CS56, Spring 2013
 */
public class ScoreLoader {
	private String filePath;

	/**
	* Constructor to create an object of class ScoreLoader
	* @param filePath File path of the file the ScoreLoader will read and load from
	*/
	public ScoreLoader(String filePath) {
		this.filePath = filePath;
	}

	/**
	* Scans the save file and assigns its values into an ArrayList
	* @return ArrayList of values obtained from the save file
	*/
	public ArrayList<Integer> loadScores() throws FileNotFoundException, IOException {
		File file = new File(filePath);
		file.createNewFile(); // make file if it does not exist
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		ArrayList<Integer> scoreList = new ArrayList<Integer>();
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			String value = line;
			Integer scoreRec = new Integer(value);
			scoreList.add(scoreRec);
		}
		bufferedReader.close();
		return scoreList;
	}

	/**
	* Rewrites the save file with up to 10 scores
	* @param scores An ArrayList containing all the scores to be written
	*/
	public void saveScore(ArrayList<Integer> scores) throws IOException {
		int count = 0;
		FileWriter fileWriter = new FileWriter(filePath);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		if (scores != null) {
			Collections.sort(scores); // Sorts scores into ascending order
			Collections.reverse(scores); // reverses scores into descending order
			for (Integer score : scores) {
				// writes up to 10 scores into the save file
				if (count < 10){
					bufferedWriter.write(score + "\n");
					count++;
				}
			}
		}
		// if less than 10 scores are entered, fill the remaining spots with 0
		while (count < 10){ 
			bufferedWriter.write(0 + "\n");
			count++;
		}
		bufferedWriter.close();
	}
}