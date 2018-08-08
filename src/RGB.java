public class RGB{
  public static RGB RED = new RGB(255,0,0);
  public static RGB GREEN = new RGB(0,255,0);
  public static RGB BLUE = new RGB(0,0,255);
  public static RGB YELLOW = new RGB(255,255,0);
  public static RGB MAGENTA = new RGB(255,0,255);
  public static RGB CYAN = new RGB(0,255,255);
  
  
  protected int red;
  protected int green;
  protected int blue;
  
  public RGB(int red, int green, int blue){
    this.red = red;
    this.green = green;
    this.blue = blue;
  }
  
  public RGB(int gray){
    this(gray,gray,gray);
  }
 
  
  
  
}