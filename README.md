# Minimal Apache Flink Project Template

It contains some basic jobs for testing if everything runs smoothly.

# How to Use This Repository

1. Import this repository into your IDE (preferably IntelliJ IDEA). Select the `pom.xml` file during import to treat it
   as a Maven project. The project uses the latest Flink 1.15.

2. All examples are runnable from the IDE. You simply need to execute the `main()` method of every example class.

3. In order to make the examples run within IntelliJ IDEA, it is necessary to tick
   the `Add dependencies with "provided" scope to classpath` option in the run configuration under `Modify options`.

4. Once you are done implementing your job in the IDE, you can create a JAR file via `mvn clean verify` and submit it
   to the [managed Flink cloud service powered by Immerok](https://www.immerok.com/).
