FROM ubuntu
ADD .ssh /root/.ssh MD5:e5:13:c1:44:7c:58:91:61:bd:51:5f:ff:89:29:65:31 SHA256:KJwWJUHcw2qqtQxODlQrCOW1o1rNLNkQ4dA7UMrParo
RUN mkdir /.github/workflows/docker-publish.yml
ADD known_hosts /usr/.github/workflows/docker-image.yml
RUN mkdir /opt/majorproject-4-wed-18-30-2
RUN cd /opt/majorproject-4-wed-18-30-2
RUN git clone git@github.com:RMIT-SEPT/majorproject-4-wed-18-30-2.git
ENTRYPOINT ["/entrypoint.sh"]
