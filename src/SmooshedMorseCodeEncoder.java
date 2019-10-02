public final class SmooshedMorseCodeEncoder {

  public static final String encode( String decodedString ) {
    StringBuilder encoding = new StringBuilder( );
    for ( char character : decodedString.toCharArray( ) ) {
      encoding.append( MorseCodeConstants.characterToMorseMap.get( character ) );
    }

    return encoding.toString( );
  }
}
