import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MorseCodeConstants {

  public static final String[] MORSE_ALPHABET = ".- -... -.-. -.. . ..-. --. .... .. .--- -.- .-.. -- -. --- .--. --.- .-. ... - ..- ...- .-- -..- -.-- --.."
      .split( " " );

  public static final Map<Character, String> characterToMorseMap;

  public static final Map<String, Character> morseToCharacterMap;

  static {
    char alphaCharacter = 'a';
    Map<Character, String> morseMap = new HashMap<>( );
    Map<String, Character> charMap = new HashMap<>( );
    for ( String morseAlphabetToken : MORSE_ALPHABET ) {
      morseMap.put( alphaCharacter, morseAlphabetToken );
      charMap.put( morseAlphabetToken, alphaCharacter );
      alphaCharacter++;
    }
    characterToMorseMap = Collections.unmodifiableMap( morseMap );
    morseToCharacterMap = Collections.unmodifiableMap( charMap );
  }

}
