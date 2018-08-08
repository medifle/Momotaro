public class Position{
  protected int x;
  protected int y;
  
  public Position(int x, int y){
    this.x = x;
    this.y = y;
  }
  
  public int getX(){ return x; }
  public int getY(){ return y; }
  
  @Override
  public String toString(){
    return "["+x+","+y+"]";
  }
}
  