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
        sh 'mvn clean package'
      }
    }

    stage ("Run integration tests") {
      steps {
        sh 'mvn verify'
      }
    }

    stage ("Build image") {
      steps {
        sh 'oc start-build issfinder --from-file=target/issfinder-swarm.jar --follow'
      }
    }

  }
}
