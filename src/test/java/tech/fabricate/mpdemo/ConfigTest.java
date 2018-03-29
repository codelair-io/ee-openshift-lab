package tech.fabricate.mpdemo;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(Arquillian.class)
public class ConfigTest {

  @Inject
  private Config config;

  @Deployment
  public static WebArchive createArchive() {
    return ShrinkWrap
        .create(WebArchive.class, "ConfigTest.war")
        .addClass(Config.class)
        .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
  }

  @Test
  public void testWhetherDefaultConfigIsCorrect() {
    assert config.getLatitude() == 59.334591 &&
        config.getLongitude() == 18.06324;
  }
}
