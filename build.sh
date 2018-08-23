gpg --use-agent --armor --detach-sign --output $(mktemp) pom.xml
mvn clean deploy