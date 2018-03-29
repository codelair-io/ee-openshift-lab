package tech.fabricate.mpdemo;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;

/**
 * Supplies configuration to this small application.
 * <p>
 * It uses the {@code MP-Config} API to inject configuration values; which, in turn, can be supplied through Java Properties,
 * Operating System environment variables, or by any other means supported by the container being used (such as YAML-files, properties-
 * files, et cetara).
 * <p>
 * The config values read by this class are {@code VANTAGE_POINT_LATITUDE} and {@code VANTAGE_POINT_LONGITUDE} and default to the center
 * of Stockholm, Sweden.
 * <p>
 * This class is marked as {@code Serializable} and the relevance for this in a single non-clustered Über-JAR-deployment is questionable;
 * nevertheless, when deploying this application in a cluster or running the Über-JARs in cluster-mode, we want to have the code by the
 * book.
 *
 * @author <a href="mailto:daniel@redbridge.se">Daniel Pfeifer</a>
 */
@ApplicationScoped
public class Config implements Serializable {
  private static final long serialVersionUID = 1L;

  @Inject
  @ConfigProperty(name = "VANTAGE_POINT_LATITUDE", defaultValue = "59.334591")
  private double latitude;

  @Inject
  @ConfigProperty(name = "VANTAGE_POINT_LONGITUDE", defaultValue = "18.06324")
  private double longitude;

  public double getLatitude() {
    return latitude;
  }

  public double getLongitude() {
    return longitude;
  }
}
