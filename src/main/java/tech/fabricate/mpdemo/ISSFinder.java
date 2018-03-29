package tech.fabricate.mpdemo;

import org.eclipse.microprofile.auth.LoginConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * This marker-class shows that we want to expose our {@code JAX-RS}-services on the context path {@code /app}.
 * <p>
 * Besides triggering the registration of REST resources, this class also defines the login configuration for {@code MP-JWT}. The
 * annotation is replaceable with {@code web.xml}-configuration, but it's quite nice to have a simple annotation for this.
 *
 * @author <a href="mailto:daniel@redbridge.se">Daniel Pfeifer</a>
 */
@ApplicationPath("/app")
@LoginConfig(authMethod = "MP-JWT", realmName = "MP-JWT")
public class ISSFinder extends Application {
  // intentionally left empty
}
