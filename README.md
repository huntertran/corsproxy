CORS PROXY
---

A really simple proxy to enable CORS for unsupported site. Proof-of-concept for open-concordia project

**To deploy to Azure**

```s
az login
mvn clean
mvn package
mvn azure-webapp:deploy
```