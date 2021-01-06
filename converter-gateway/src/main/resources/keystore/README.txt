This how the files in this file were generated

Procedure taken from: http://apetec.com/support/generatesan-csr.htm

Generate key
openssl genrsa -out converter-ms-key.key 2048

Create the SSL certificate request (CSR)
openssl req -new -out converter-ms.csr -key converter-ms-key.key -config cert-config.cnf

Check CSR (optional)
openssl req -text -noout -in converter-ms.csr

Self-sign and create the certificate:
openssl x509 -req -days 3650 -in converter-ms.csr -signkey converter-ms-key.key -out converter-ms.csr -extensions v3_req -extfile cert-config.cnf

Package the key and cert in a PKCS12 file
openssl pkcs12 -export -in converter-ms.csr -inkey converter-ms-key.key -out converter-ms.p12 -name converterall
password: 1QXy9zu9sQLVWOr

As this is a self sign certificate we need to import the certificate into the keystore of the java clients. You can do it like this.
Replace $JAVA_HOME with the path to the java home
# sudo keytool -import -alias converterall -keystore "$JAVA_HOME/lib/security/cacerts" -file converter-ms.csr
sudo keytool -import -alias converterall -keystore /Library/Java/JavaVirtualMachines/zulu-11.jdk/Contents/Home/lib/security/cacerts -file converter-ms.csr

Enter keystore password: changeit -> this is the default password for the java keystore
Trust this certificate? [no]:  yes

