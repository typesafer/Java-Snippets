class Hello {
  
  public int x;
  public int y;
  
  public  int add(int x, int y){
    return x + y;
  }
  public static void main(String[] args) {
    Hello one = new Hello();
    System.out.println("hello world" + one.add(3,5));
  }
}
