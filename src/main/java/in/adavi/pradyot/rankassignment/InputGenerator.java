package in.adavi.pradyot.rankassignment;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InputGenerator {
  
  private static final String INPUT_FILE = "input.csv";
  
  public List<Student> generatorInput(int count) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
  
    List<Student> students = new ArrayList<>();
    
    for (int i = 0; i < count; i++) {
      Student student = new Student();
      student.setName("S"+(i+1));
      student.setRank(i+1);
      
      students.add(student);
    }
  
    Writer writer = new FileWriter(INPUT_FILE);
    StatefulBeanToCsv<Student> studentStatefulBeanToCsv = new StatefulBeanToCsvBuilder<Student>(writer)
      .withSeparator(CSVWriter.DEFAULT_SEPARATOR).build();

    studentStatefulBeanToCsv.write(students);
    writer.close();
  
    return students;
  }
  
  public void updateRank(List<Student> students,Integer noOfStudents) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

    Integer totalStudents = students.size();
    for (int i = 0; i < noOfStudents; i++){
      Integer studentId = (new Random().nextInt(totalStudents)+1);
      if(studentId > totalStudents){
        studentId--;
      }
      students.get(studentId-1).setUpdatedRank(i+1);
    }

    Writer writerRank = new FileWriter("inputUpdated.csv");
    StatefulBeanToCsv<Student> studentRankStatefulBeanToCsv = new StatefulBeanToCsvBuilder<Student>(writerRank)
      .withSeparator(CSVWriter.DEFAULT_SEPARATOR).build();

    studentRankStatefulBeanToCsv.write(students);
    writerRank.close();
  }
}
