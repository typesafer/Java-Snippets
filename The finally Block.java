The finally block always executes when the try block exits. This ensures that the finally block is executed even if an unexpected exception occurs. But finally is useful for more than just exception handling — it allows the programmer to avoid having cleanup code accidentally bypassed by a return, continue, or break. Putting cleanup code in a finally block is always a good practice, even when no exceptions are anticipated.
Note: If the JVM exits while the try or catch code is being executed, then the finally block may not execute. Likewise, if the thread executing the try or catch code is interrupted or killed, the finally block may not execute even though the application as a whole continues.

The try block of the writeList method that you've been working with here opens a PrintWriter. The program should close that stream before exiting the writeList method. This poses a somewhat complicated problem because writeList's try block can exit in one of three ways.

    The new FileWriter statement fails and throws an IOException.
    The vector.elementAt(i) statement fails and throws an ArrayIndexOutOfBoundsException.
    Everything succeeds and the try block exits normally. 

The runtime system always executes the statements within the finally block regardless of what happens within the try block. So it's the perfect place to perform cleanup.

The following finally block for the writeList method cleans up and then closes the PrintWriter.

finally {
    if (out != null) { 
        System.out.println("Closing PrintWriter");
        out.close(); 
    } else { 
        System.out.println("PrintWriter not open");
    } 
} 

In the writeList example, you could provide for cleanup without the intervention of a finally block. For example, you could put the code to close the PrintWriter at the end of the try block and again within the exception handler for ArrayIndexOutOfBoundsException, as follows.

try {
    
    out.close();       //Don't do this; it duplicates code. 
    
} catch (FileNotFoundException e) {
    out.close();       //Don't do this; it duplicates code.
    System.err.println("Caught: FileNotFoundException: " 
                      + e.getMessage());
    throw new RuntimeException(e);
    
} catch (IOException e) {
    System.err.println("Caught IOException: " 
                      + e.getMessage());
}

However, this duplicates code, thus making the code difficult to read and error-prone should you modify it later. For example, if you add code that can throw a new type of exception to the try block, you have to remember to close the PrintWriter within the new exception handler.
Important: The finally block is a key tool for preventing resource leaks. When closing a file or otherwise recovering resources, place the code in a finally block to ensure that resource is always recovered.

If you are using Java SE 7 or later, consider using the try-with-resources statement in these situations, which automatically releases system resources when no longer needed. The next section has more information. 
