package in.adavi.pradyot.rankassignment;

import java.util.Objects;

public class Student {
  
  private String name;
  
  private Integer rank;
  
  private Integer updatedRank;
  
  private Integer modCountAhead;
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public Integer getRank() {
    return rank;
  }
  
  public void setRank(Integer rank) {
    this.rank = rank;
  }
  
  public Student() {
    this.modCountAhead = 0;
    this.updatedRank = 0;
    this.rank = 0;
  }
  
  public Integer getUpdatedRank() {
    return updatedRank;
  }
  
  public void setUpdatedRank(Integer updatedRank) {
    this.updatedRank = updatedRank;
  }
  
  public Integer getModCountAhead() {
    return modCountAhead;
  }
  
  public void setModCountAhead(Integer modCountAhead) {
    this.modCountAhead = modCountAhead;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Student)) return false;
    Student student = (Student) o;
    return getName().equals(student.getName()) &&
      getRank().equals(student.getRank());
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(getName(), getRank());
  }
  
  @Override
  public String toString() {
    return "Student{" +
      "name='" + name + '\'' +
      ", rank=" + rank +
      ", updatedRank=" + updatedRank +
      ", modCountAhead=" + modCountAhead +
      '}';
  }
}
