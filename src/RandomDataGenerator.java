import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RandomDataGenerator {

  public static final String randomAlphabetPermutation( ) {
    List<Character> alphabet = new ArrayList<>( MorseCodeConstants.characterToMorseMap.keySet( ) );
    Collections.shuffle( alphabet );
    return alphabet.stream( ).map( String::valueOf ).collect( Collectors.joining( ) );
  }
}
