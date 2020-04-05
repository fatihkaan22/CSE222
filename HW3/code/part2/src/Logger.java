
import java.io.*;

/**
 * It calculates the time passed between two log() methods and constructs a String with message and runtime.
 */
public class Logger {
  /**
   * Log messages appends over this StringBuilder
   */
  private StringBuilder sb = new StringBuilder();
  /**
   * Saves the current time when log() method call to compare next time
   */
  private long prevTime;

  /**
   * Initialize class to enable for further log() calls.
   */
  public void init() {
    prevTime = System.currentTimeMillis();
    sb.append("Running Time (ms)     Message\n");
  }


  /**
   * Logs time passed and message
   *
   * @param message Message to be added as log entry
   */
  public void log(String message) {
    Long timePassed = System.currentTimeMillis() - prevTime;
    sb.append(String.format("%13d", timePassed)).append(" ms: ");
    sb.append("    ");
    sb.append(message).append('\n');
    prevTime = System.currentTimeMillis();
  }

  /**
   * Saves log records to specified file.
   *
   * @param filename to be saved
   */
  public void writeToFile(String filename) {
    BufferedWriter bf;
//    System.out.println(sb.toString());
    try {
      bf = new BufferedWriter(new FileWriter(new File(filename)));
      bf.write(sb.toString());
      bf.flush();
      bf.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
