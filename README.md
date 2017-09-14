# rocci-java-example - Example of using occi-core Ruby Gem in Java

This repository presents a way of integrating occi-core Ruby Gem in Java applications. It uses [jrocci-wrappers](https://github.com/Majlen/jrocci-wrappers) library, where the wrappers are developed.

The example uses maven extensively, it is used for fetching the Gem and bundling it inside the Java archive (may be simply swapped for Web archive).
The main output of the example is the shaded jar with all the dependencies.

## Examples
### Mapping ruby classes to Java interfaces
The example shows how to create a Ruby implementation of the following Java interface:
```java
package sources.occi.interfaces;

import java.util.List;

public interface Mixin {
	List<Mixin> getRelations();
	String getTerm();
}
```
The following code example is showing some JRuby features added to Ruby.
* `java_require` statement forces jrubyc to read the Ruby class from file instead of embedding it inside the code.
* `java_package` statement is equivalent to the `package` Java statement.
* `java_import` statement is equivalent to the `import` Java statement.
* `java_implements` statement declares that the Ruby class implements the specified Java interface.
* `java_signature` statement declared the signature of Java method (otherwise its signature is `Object method(Object)`

The following code also shows how to create a Java object (`LinkedList` in `getRelations` function).
```ruby
require 'java'
require 'occi/core'
java_require 'mixin'
java_package 'sources.occi.interfaces'
java_import "sources.occi.interfaces.Mixin"
java_import "java.util.List"

class MixinImpl
	include Mixin
	java_implements Mixin

	def initialize(mixin)
		@mixin = mixin
	end

	java_signature 'List<Mixin> getRelations()'
	def relations()
		list = java.util.LinkedList.new
		@mixin.depends.each do |mixin|
			impl = MixinImpl.new(mixin)
			list.add(impl)
		end
		return list
	end

	java_signature 'String getTerm()'
	def term()
		return @mixin.term
	end
end
```
### Running generic Ruby script
There is an eval method in Java Scripting API (JSR-223), which can be used to run simple scripts (either from string or from a Reader, such as FileReader, ...). In this example it is used to obtain the implementation of Model interface. The static method `getModel` implemented in the interface is used to run the necessary Ruby code.
### Generating wrapper classes automatically
It is possible to generate Java wrappers from ruby classes by invoking jrubyc. Jruby-maven-plugin can be used to generate them automatically during the build process. Snippet of pom.xml down below presents the way to do it.
```xml
<plugin>
	<groupId>de.saumya.mojo</groupId>
	<artifactId>jruby-maven-plugin</artifactId>
	<version>1.1.5</version>
	<configuration>
		<generateJava>true</generateJava>
	</configuration>
	<executions>
		<execution>
			<phase>process-resources</phase>
			<goals>
				<goal>compile</goal>
			</goals>
		</execution>
	</executions>
</plugin>
```
### Bundling ruby gems
It is necessary to add torquebox rubygems-proxy, which fetches the gems for maven. All the gems provided by torquebox repository have the groupId set to rubygems and it is necessary to specify the type of dependency as gem. Snippets of pom.xml are down below.
```xml
<repositories>
	<repository>
		<id>rubygems-releases</id>
		<url>http://rubygems-proxy.torquebox.org/releases</url>
	</repository>
</repositories>
...
<dependencies>
	<dependency>
		<groupId>rubygems</groupId>
		<artifactId>occi-core</artifactId>
		<version>${rocci.version}</version>
		<type>gem</type>
	</dependency>
</dependencies>
```
Bundling gems is done by the gem-maven-plugin in the following way:
```xml
<plugin>
	<groupId>de.saumya.mojo</groupId>
	<artifactId>gem-maven-plugin</artifactId>
	<version>1.1.5</version>
	<configuration>
		<includeRubygemsInResources>true</includeRubygemsInResources>
	</configuration>
	<executions>
		<execution>
			<goals>
				<goal>initialize</goal>
			</goals>
		</execution>
	</executions>
</plugin>
```
However if you bundle java dependencies you need to specify that rubygems are excluded of the bundling process. In case of maven-shade-plugin, it is necessary to add following snippet into the configuration section of the plugin:
```xml
<artifactSet>
	<excludes>
		<exclude>rubygems:*</exclude>
	</excludes>
</artifactSet>
```
