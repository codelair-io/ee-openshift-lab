# ISSFinder

The primary purpose of this application is to showcase in the form of a lab the process of continuous integration and delivery of a Java
EE Microprofile application on OpenShift.

## Prerequisites

To build and run this application all you need is Java 8 to be installed on your computer.

## Execution

### Compiling and packaging

TODO

### Running

TODO

## OpenShift

You can create all the required artifacts yourself, but a complete template that creates the required artifacts can be imported to OpenShift
can be processed using `oc process -f openshift/template.json | oc create -f -`.

### Builds

TODO

### Pipeline

TODO

#### Manual testing and promotion

TODO

## License

ISSFinder by Daniel Pfeifer (RedBridge Group).

To the extent possible under law, the person who associated CC0 with ISSFinder has waived all copyright and related or neighboring rights
to ISSFinder.

You should have received a copy of the CC0 legalcode along with this work. If not, see <http://creativecommons.org/publicdomain/zero/1.0/>.
