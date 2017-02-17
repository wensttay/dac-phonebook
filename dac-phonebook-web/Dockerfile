FROM payara/server-full
MAINTAINER Wensttay <yattsnew@gmail.com>
ENV DOMAIN domain1
RUN echo 'AS_ADMIN_PASSWORD=admin\n\
 EOF\n'\
>> /opt/pwdfile

RUN \
 $PAYARA_PATH/bin/asadmin start-domain ${DOMAIN} && \
 $PAYARA_PATH/bin/asadmin --user admin --passwordfile=/opt/pwdfile enable-secure-admin
 #$PAYARA_PATH/bin/asadmin --user admin --passwordfile=/opt/pwdfile

ENV DEPLOY ${PAYARA_PATH}/glassfish/domains/${DOMAIN}/autodeploy/
ENTRYPOINT $PAYARA_PATH/bin/asadmin start-domain --verbose ${DOMAIN}
ADD target/dac-phonebook-web.war  ${DEPLOY}
