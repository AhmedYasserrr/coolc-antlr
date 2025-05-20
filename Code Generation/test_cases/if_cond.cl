class Test {
  say(cond:Bool): String {
    if cond then
        "if"
      else
        "else"
    fi
  };
};

ClAss Main inherits IO {
  main(): Object {
   (new IO).out_string("Hello world!\n")
  };
};