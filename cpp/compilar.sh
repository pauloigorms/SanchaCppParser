#--
# author @ Paulo Moraes [http://pauloigormoraes.com]
# Data: 12/09/2016
#--

#--- Commands --- #

javacc AnalisadorSintatico.jj
chmod 777 sintatico/
cd sintatico/
javac AnalisadorSintatico.java
java AnalisadorSintatico
