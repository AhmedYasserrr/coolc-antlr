class B inherits A {
    s : String <- "test";
};

class C inherits B {
    s : String <- "test";
};

class A inherits C {
    s : String <- "test";
};

class Main {
	main():IO {
		(new IO).out_string("Hello world!\n")
	};
};