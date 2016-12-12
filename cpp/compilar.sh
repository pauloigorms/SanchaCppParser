#--
# author @ Paulo Moraes [http://pauloigormoraes.com]
# Data: 12/09/2016
#--

#--- Commands --- #

javacc AnalisadorLS.jj
chmod 777 output/
cd output/
javac AnalisadorLS.java
java AnalisadorLS
