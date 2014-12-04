

You associate exception handlers with a try block by providing one or more catch blocks directly after the try block. No code can be between the end of the try block and the beginning of the first catch block.

try {

} catch (ExceptionType name) {

} catch (ExceptionType name) {

}

Each catch block is an exception handler and handles the type of exception indicated by its argument. The argument type, ExceptionType, declares the type of exception that the handler can handle and must be the name of a class that inherits from the Throwable class. The handler can refer to the exception with name.

The catch block contains code that is executed if and when the exception handler is invoked. The runtime system invokes the exception handler when the handler is the first one in the call stack whose ExceptionType matches the type of the exception thrown. The system considers it a match if the thrown object can legally be assigned to the exception handler's argument.

The following are two exception handlers for the writeList method — one for two types of checked exceptions that can be thrown within the try statement:

try {

} catch (FileNotFoundException e) {
    System.err.println("FileNotFoundException: "
                        + e.getMessage());
    throw new SampleException(e);

} catch (IOException e) {
    System.err.println("Caught IOException: "
                        + e.getMessage());
}

Both handlers print an error message. The second handler does nothing else. By catching any IOException that's not caught by the first handler, it allows the program to continue executing.

The first handler, in addition to printing a message, throws a user-defined exception. (Throwing exceptions is covered in detail later in this chapter in the How to Throw Exceptions section.)

In this example, when the FileNotFoundException is caught it causes a user-defined exception called SampleException to be thrown. You might want to do this if you want your program to handle an exception in this situation in a specific way.

Exception handlers can do more than just print error messages or halt the program. They can do error recovery, prompt the user to make a decision, or propagate the error up to a higher-level handler using chained exceptions, as described in the Chained Exceptions section.
Catching More Than One Type of Exception with One Exception Handler

In Java SE 7 and later, a single catch block can handle more than one type of exception. This feature can reduce code duplication and lessen the temptation to catch an overly broad exception.

In the catch clause, specify the types of exceptions that block can handle, and separate each exception type with a vertical bar (|):

catch (IOException|SQLException ex) {
    logger.log(ex);
    throw ex;
}

Note: If a catch block handles more than one exception type, then the catch parameter is implicitly final. In this example, the catch parameter ex is final and therefore you cannot assign any values to it within the catch block.
