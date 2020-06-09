FROM openjdk:8-jdk

LABEL maintainer="Peshotan Irani \"peshotan_irani@sfu.ca\""
LABEL repository="https://csil-git1.cs.surrey.sfu.ca/pia5/cmpt456-project1-starter-code"

# Installs Ant
ENV ANT_VERSION 1.10.6
RUN cd && \
    pwd && \
    wget -q https://archive.apache.org/dist/ant/binaries/apache-ant-${ANT_VERSION}-bin.tar.gz && \
    tar -xzf apache-ant-${ANT_VERSION}-bin.tar.gz && \
    mv apache-ant-${ANT_VERSION} /opt/ant && \
    rm apache-ant-${ANT_VERSION}-bin.tar.gz && \
    wget https://archive.apache.org/dist/ant/ivy/2.4.0/maven2/2.4.0/ivy-2.4.0.jar -P /root/.ant/lib/
ENV ANT_HOME /opt/ant
ENV PATH ${PATH}:/opt/ant/bin

# Compile Lucene
WORKDIR /lucene-solr
COPY . /lucene-solr
RUN ant ivy-bootstrap
RUN ant -f lucene/benchmark/build.xml
RUN ant compile
RUN ant -f lucene/demo/build.xml
