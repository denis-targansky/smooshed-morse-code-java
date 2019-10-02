import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

  public static void main( String[] args ) {
    long startTimeMillis = System.currentTimeMillis( );
    Collection<String> decodedAlphabets = SmooshedMorseCodeDecoder.decodeAlphabet(
        "---.....-.-.--.-..--.-..-.---..--.-..--.-...-.--.....--..---......-.--...-..--.---" );
    long durationMillis = System.currentTimeMillis( ) - startTimeMillis;
    AtomicInteger solutionCount = new AtomicInteger( );
    decodedAlphabets.parallelStream( ).filter( decodedAlphabet -> decodedAlphabet.length( ) == 26 )
        .forEach( decodedAlphabet -> {
          System.out.println( decodedAlphabet );
          solutionCount.incrementAndGet( );
        } );
    System.out.println( "Solutions found : " + solutionCount );
    System.out
        .println( "Calculation took " + durationMillis + " ms (" + durationMillis / 1000D + " s)" );
  }

}
