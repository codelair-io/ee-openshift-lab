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

pipeline {

  agent {
    node {
      label "maven"
    }
  }

  stages {

    // Compile stage
    stage ("Compile") {
      steps {
        sh 'JAVA_TOOL_OPTIONS="-Dhttp.proxyHost=proxy.int.redbridge.se -Dhttp.proxyPort=3128 -Dhttps.proxyHost=proxy.int.redbridge.se -Dhttps.proxyPort=3128" mvn package'
      }
    }

    stage ("Run integration tests") {
      steps {
        sh 'JAVA_TOOL_OPTIONS="-Dhttp.proxyHost=proxy.int.redbridge.se -Dhttp.proxyPort=3128 -Dhttps.proxyHost=proxy.int.redbridge.se -Dhttps.proxyPort=3128" mvn verify'
      }
    }

    stage ("Build image") {
      steps {
        sh 'oc start-build issfinder --from-file=target/issfinder-swarm.jar --follow'
      }
    }

  }
}
