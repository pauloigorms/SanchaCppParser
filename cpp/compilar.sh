#--
# author @ Paulo Moraes [http://pauloigormoraes.com]
# Data: 12/09/2016
#--

#--- Commands --- #

javacc cpp.jj
chmod 777 output/
cd output/
javac AnalisadorLexico.java
java AnalisadorLexico
