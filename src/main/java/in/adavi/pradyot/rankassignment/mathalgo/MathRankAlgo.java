package in.adavi.pradyot.rankassignment.mathalgo;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import in.adavi.pradyot.rankassignment.RankAlgo;
import in.adavi.pradyot.rankassignment.Student;
import in.adavi.pradyot.rankassignment.StudentRankComparator;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class MathRankAlgo implements RankAlgo {
  
  @Override
  public void rank(List<Student> students) {
    
    Integer maxRank = 0;
    Integer noOfChanges = 0;
    for (int i = 0; i < students.size(); i++) {
      Integer updateRank = students.get(i).getUpdatedRank();
      if(updateRank >= maxRank)
        maxRank = updateRank;
      if(updateRank != 0){
        noOfChanges = noOfChanges + 1;
      } else {
        students.get(i).setModCountAhead(noOfChanges);
      }
    }
  
    Writer writerChanged = null;
    try {
      writerChanged = new FileWriter("inputChanged.csv");
    } catch (IOException e) {
      e.printStackTrace();
    }
    StatefulBeanToCsv<Student> studentChangedStatefulBeanToCsv = new StatefulBeanToCsvBuilder<Student>(writerChanged)
      .withSeparator(CSVWriter.DEFAULT_SEPARATOR).build();
  
    try {
      studentChangedStatefulBeanToCsv.write(students);
    } catch (CsvDataTypeMismatchException e) {
      e.printStackTrace();
    } catch (CsvRequiredFieldEmptyException e) {
      e.printStackTrace();
    }
    try {
      writerChanged.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  
    for (int i = 0; i < students.size(); i++) {
      Student student = students.get(i);
      Integer noOfCh = student.getModCountAhead();
      Integer update = student.getUpdatedRank();
      if(update == 0) {
        Integer currentRank = student.getRank();
        Integer newRank = maxRank-noOfCh+currentRank;
        student.setRank(newRank);
      } else {
        student.setRank(update);
      }
    }
  
    Writer writerFinal = null;
    try {
      writerFinal = new FileWriter("output.csv");
    } catch (IOException e) {
      e.printStackTrace();
    }
    StatefulBeanToCsv<Student> studentFinalStatefulBeanToCsv = new StatefulBeanToCsvBuilder<Student>(writerFinal)
      .withSeparator(CSVWriter.DEFAULT_SEPARATOR).build();
  
    try {
      students.sort(new StudentRankComparator());
      studentFinalStatefulBeanToCsv.write(students);
    } catch (CsvDataTypeMismatchException e) {
      e.printStackTrace();
    } catch (CsvRequiredFieldEmptyException e) {
      e.printStackTrace();
    }
    try {
      writerFinal.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
