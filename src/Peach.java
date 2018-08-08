public class Peach implements Comparable<Peach>{
  protected int     ripeness;
  protected boolean bad;

  public Peach(int ripeness, boolean bad){
    this.ripeness = ripeness;
    this.bad = bad;
  }
  
  public Peach(int ripeness){
    this(ripeness, false);
  }
  
  public int getRipeness(){ return ripeness; }
  
  /** ages a peach in some way */
  public void age(){}
  
  @Override 
  public int compareTo(Peach other){
    return 0;
  }
  
}