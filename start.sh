echo y | cp ./web-site/src/MyWebSite.jar ./
mrp=/root/.m2/repository
java=/software/jdk11/bin/java
className=io.github.kloping.mywebsite.MyWebSiteApplication
classPath=$(cat ./web-site/classpath.txt)
calssPath=${classPath}
classPath=${classPath//%mrp%/$mrp}
$java -Dfile.encoding=UTF-8 -classpath ${classPath}./MyWebSite.jar $className
