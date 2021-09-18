package in.adavi.pradyot.rankassignment;

import java.util.Comparator;

public class StudentRankComparator implements Comparator<Student> {
  @Override
  public int compare(Student o1, Student o2) {
    return o1.getRank()-o2.getRank();
  }
}
