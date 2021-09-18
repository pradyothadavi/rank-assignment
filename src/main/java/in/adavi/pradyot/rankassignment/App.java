package in.adavi.pradyot.rankassignment;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import in.adavi.pradyot.rankassignment.mathalgo.MathRankAlgo;

import java.io.IOException;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        InputGenerator inputGenerator = new InputGenerator();
        MathRankAlgo mathRankAlgo = new MathRankAlgo();
        try {
            long startTime = System.currentTimeMillis();
            List<Student> students = inputGenerator.generatorInput(10);
            inputGenerator.updateRank(students,3);
            mathRankAlgo.rank(students);
            long endTime = System.currentTimeMillis();
            System.out.println("Time Taken in ms : "+(endTime-startTime));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvDataTypeMismatchException e) {
            e.printStackTrace();
        } catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
}
