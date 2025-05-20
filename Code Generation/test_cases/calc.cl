ClAss Main inherits IO {
    test(x:Int, y:Int, z:Int): Int {
      ((x+y)*(z+x)/y) - (x/10)
    };
  main(): Object {
   (new IO).out_string("Hello world!\n")
  };
};