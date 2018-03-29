/*
 * issfinder by Daniel Pfeifer (RedBridge Group).
 *
 * To the extent possible under law, the person who associated CC0 with
 * issfinder has waived all copyright and related or neighboring rights
 * to issfinder.
 *
 * You should have received a copy of the CC0 legalcode along with this
 * work.  If not, see <http://creativecommons.org/publicdomain/zero/1.0/>.
 */

package tech.fabricate.issfinder;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.Set;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import tech.fabricate.issfinder.model.Daylight;
import tech.fabricate.issfinder.remote.OpenNotifyProxy;
import tech.fabricate.issfinder.remote.SunriseSunsetProxy;

@RequestScoped
@Path("/issvisible")
public class ISSVisibleResource {

  @Inject
  private Config config;
  @Inject
  private OpenNotifyProxy openNotifyProxy;
  @Inject
  private SunriseSunsetProxy sunriseSunsetProxy;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response doGetJson(@QueryParam("lat") Double lat, @QueryParam("lon") Double lon) {
    if (lat == null || lon == null) {
      lat = config.getLatitude();
      lon = config.getLongitude();
    }

    return getIssPassTime(lat, lon)
        .map(dateTime -> Response.ok(Json.createObjectBuilder()
            .add("issVisible", dateTime.toEpochSecond())
            .add("status", "OK")
            .build()))
        .orElse(Response.serverError().entity(Json.createObjectBuilder()
            .addNull("issVisible")
            .add("status", "Failed")
            .build()))
        .build();
  }

  private Optional<ZonedDateTime> getIssPassTime(final double lat, final double lon) {
    final Set<Instant> instants = openNotifyProxy.fetchIssPassDates(lat, lon);

    for (final Instant instant : instants) {
      final ZonedDateTime dateTime = ZonedDateTime.ofInstant(instant, ZoneOffset.UTC);
      final Daylight daylightForDate = sunriseSunsetProxy.fetchDaylightForDate(config.getLatitude(), config.getLongitude(), dateTime);

      if (daylightForDate.isNight(dateTime)) {
        return Optional.of(dateTime);
      }
    }

    return Optional.empty();
  }
}
