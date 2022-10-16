# docker-ms


-Dspring-boot.run.jvmArguments="-Xms512m -Xmx1024m"

<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <configuration>
        <jvmArguments>
            -Xms512m
            -Xmx1024m
        </jvmArguments>
    </configuration>
</plugin>