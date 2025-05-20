class A {
    test: String <- "test";
};
class B inherits A {
    new_test : String <- "test";
}
class Main {
	main():IO {
		(new IO).out_string("Hello world!\n")
	};
    x: A <- (new B);
	y: B <- (new A);
};