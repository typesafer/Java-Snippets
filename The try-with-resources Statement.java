The try-with-resources statement is a try statement that declares one or more resources. A resource is as an object that must be closed after the program is finished with it. The try-with-resources statement ensures that each resource is closed at the end of the statement. Any object that implements the new java.lang.AutoCloseable interface can be used as a resource. The classes java.io.InputStream, OutputStream, Reader, Writer, java.sql.Connection, Statement, and ResultSet have been retrofitted to implement the AutoCloseable interface and can all be used as resources in a try-with-resources statement.

Consider the following example that reads the first line from a file. It uses an instance of BufferedReader to read data from the file. BufferedReader is a resource that must be closed after the program is finished with it. This example uses a finally block to ensure that the instance of BufferedReader is closed regardless of whether the try statement completes normally or abruptly (as a result of the method BufferedReader.readLine throwing an IOException):

static String readFirstLineFromFile(String path) throws IOException {
  BufferedReader br = new BufferedReader(new FileReader(path));
  try {
    return br.readLine();
  } finally {
    br.close();
  }
}

In Java SE 7 and later, you can use a try-with-resources statement instead of a finally block to close resources. The resources are closed whether the try block completes normally or throws an exception.

In the following example, the resource declared in the try-with-resources statement is a BufferedReader. The declaration statement appears within parentheses immediately after the try keyword. The class BufferedReader, in JDK 7 and later, implements the interface java.lang.AutoCloseable.

static String readFirstLineFromFile(String path) throws IOException {
  try (BufferedReader br = new BufferedReader(new FileReader(path))) {
    return br.readLine();
  }
}

You may declare one or more resources in a try-with-resources statement. The following is a static method that copies a file. This example shows how to release two resources in previous releases of Java SE:
Note: The following examples for copying files are for illustrative purposes only. As of the Java SE 7 release, the Files class provides a method for copying a file. See Copying a File or Directory for more information.

static void copy(String src, String dest) throws IOException {
  InputStream in = new FileInputStream(src);
  try {
    OutputStream out = new FileOutputStream(dest);
    try {
      byte[] buf = new byte[8 * 1024];
      int n;
      while ((n = in.read(buf)) >= 0)
        out.write(buf, 0, n);
    } finally {
      out.close();
    }
  } finally {
    in.close();
  }
}

The following example has the same functionality as the previous example, except it uses a try-with-resources statement:

static void copy(String src, String dest) throws IOException {
  try (InputStream in = new FileInputStream(src);
       OutputStream out = new FileOutputStream(dest)) {
    byte[] buf = new byte[8192];
    int n;
    while ((n = in.read(buf)) >= 0)
      out.write(buf, 0, n);
  }
}

In this example, the try-with-resources statement contains two declarations that are separated by a semicolon: InputStream and OutputStream. When the block of code that directly follows it terminates, either normally or because of an exception, the close methods of the OutputStream and InputStream objects are automatically called in this order. Note that the close methods of resources are called in the opposite order of their creation.

The following example uses a try-with-resources statement to automatically close a java.sql.Statement object:

  public static void viewTable(Connection con) throws SQLException {

    String query = "select COF_NAME, SUP_ID, PRICE, SALES, TOTAL from COFFEES";

    try (Statement stmt = con.createStatement()) {

      ResultSet rs = stmt.executeQuery(query);

      while (rs.next()) {
        String coffeeName = rs.getString("COF_NAME");
        int supplierID = rs.getInt("SUP_ID");
        float price = rs.getFloat("PRICE");
        int sales = rs.getInt("SALES");
        int total = rs.getInt("TOTAL");
        System.out.println(coffeeName + ", " + supplierID + ", " + price +
                           ", " + sales + ", " + total);
      }

    } catch (SQLException e) {
      JDBCTutorialUtilities.printSQLException(e);
    }
  }

The resource java.sql.Statement used in this example is part of the JDBC 4.1 and later API.

Note: A try-with-resources statement can have catch and finally blocks just like an ordinary try statement. In a try-with-resources statement, any catch or finally block is run after the resources declared have been closed.
Suppressed Exceptions

An exception can be thrown from the block of code associated with the try-with-resources statement. For example, see the method copy(String src, String dest) in one of the previous examples. In addition, up to two exceptions can be thrown from the try-with-resources statement when it tries to close the InputStream or OutputStream objects. If an exception is thrown from the block and one or more exceptions are thrown from the try-with-resources statement, then those exceptions thrown from the try-with-resources statement are suppressed, and the exception thrown by the block is the one that is thrown by the copy method. You can retrieve these suppressed exceptions by calling the Throwable.getSuppressed method from the exception thrown by the block.
Classes That Implement the AutoCloseable Interface

In Java SE 7 and later, the following classes implement the java.lang.AutoCloseable or java.io.Closeable interface (which now extends AutoCloseable) and can be used in try-with-resources statements:

    java.io.Closeable: This interface now extends the java.lang.AutoCloseable interface. The close method of the Closeable interface throws exceptions of type IOException while the close method of the AutoCloseable interface throws exceptions of type Exception. Consequently, subclasses of the AutoCloseable interface can override this behavior of the close method to throw specialized exceptions, such as IOException, or no exception at all.
    java.nio.channels.FileLock
    javax.imageio.stream.ImageInputStream
    java.beans.XMLEncoder
    java.beans.XMLDecoder
    java.io.ObjectInput
    java.io.ObjectOutput
    javax.sound.sampled.Line
    javax.sound.midi.Receiver
    javax.sound.midi.Transmitter
    javax.sound.midi.MidiDevice
    java.util.Scanner
    java.sql.Connection
    java.sql.ResultSet
    java.sql.Statement
