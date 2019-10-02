import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class SmooshedMorseCodeDecoder {

  public static final Collection<String> decodeAlphabet( final String encodedAlphabet ) {
    Collection<String> decodedAlphabets = decodeAlphabetRecursive(
        encodedAlphabet,
        decodeFirstCharactersUnique( encodedAlphabet, new ArrayList<>( ) ),
        new ArrayList<>( ) );
    
    return decodedAlphabets;
  }

  private static final Collection<String> decodeAlphabetRecursive(
      final String encodedAlphabet,
      final Collection<Character> decodedFirstAlphaCharacters,
      final Collection<Character> previouslyDecodedAlphaCharacters ) {
    Collection<String> possibleDecodings = decodedFirstAlphaCharacters.parallelStream( ).flatMap( decodedFirstAlphaCharacter -> {

      // Chop off the current first decoded character
      String restOfEncodedAlphabet = encodedAlphabet.substring(
          MorseCodeConstants.characterToMorseMap.get( decodedFirstAlphaCharacter ).length( ) );

      if ( restOfEncodedAlphabet.isEmpty( ) ) {
        return Arrays.asList( String.valueOf( decodedFirstAlphaCharacter ) ).stream( );
      }

      Collection<Character> currentDecodedMorseCharacters = new ArrayList<>(
          previouslyDecodedAlphaCharacters );
      currentDecodedMorseCharacters.add( decodedFirstAlphaCharacter );

      Collection<Character> decodedSecondMorseCharacters = decodeFirstCharactersUnique(
          restOfEncodedAlphabet,
          currentDecodedMorseCharacters );
      
      if ( decodedSecondMorseCharacters.isEmpty( ) ) {
        return Arrays.asList( String.valueOf( decodedFirstAlphaCharacter ) ).stream( );
      }

      return decodeAlphabetRecursive(
          restOfEncodedAlphabet,
          decodedSecondMorseCharacters,
          currentDecodedMorseCharacters ).stream( )
              .map( possibleDecoding -> decodedFirstAlphaCharacter + possibleDecoding );
    } ).collect( Collectors.toList( ) );
    
    return possibleDecodings;
  }

  private static final Collection<Character> decodeFirstCharactersUnique(
      final String encodedString,
      final Collection<Character> previouslyDecodedAlphaCharacters ) {
    Collection<Character> decodedFirstChars = new ArrayList<>( );
    for ( String morseAlpha : MorseCodeConstants.MORSE_ALPHABET ) {
      Character alphaChar = MorseCodeConstants.morseToCharacterMap.get( morseAlpha );
      if ( !previouslyDecodedAlphaCharacters.contains( alphaChar ) // Can't have duplicates
          && encodedString.startsWith( morseAlpha ) ) {
        decodedFirstChars.add( alphaChar );
      }
    }

    return decodedFirstChars;
  }

}
