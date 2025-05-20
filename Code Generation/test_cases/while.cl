class Test {
  say(x: Bool): Object {
    while x loop
    (new IO).out_string("Hello world!\n")
    pool
  };
};

ClAss Main inherits IO {
  main(): Object {
   (new IO).out_string("Hello world!\n")
  };
};