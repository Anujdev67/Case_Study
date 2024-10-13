package exception;

public class IncidentNumberNotFoundException extends RuntimeException {
  public IncidentNumberNotFoundException(String message) {
    super(message);
  }
}
