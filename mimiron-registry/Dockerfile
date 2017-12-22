FROM alpine:3.5
MAINTAINER Sheldon Zhang https://github.com/zhangxd1989

RUN echo "https://mirrors.ustc.edu.cn/alpine/v3.5/main" > /etc/apk/repositories && \
    echo "https://mirrors.ustc.edu.cn/alpine/v3.5/community" >> /etc/apk/repositories && \
    apk update && \
    apk add nodejs git openssh curl bash inotify-tools jq && \
    rm -rf /var/cache/apk/* && \
    npm --registry=https://registry.npm.taobao.org install -g simplywatch && \
    npm --registry=https://registry.npm.taobao.org install -g git2consul@0.12.13 && \
    mkdir -p /etc/git2consul.d

ADD /load-config.sh /
VOLUME /config

ENV CONFIG_MODE=filesystem
ENV INIT_SLEEP_SECONDS=5
ENV CONSUL_URL=localhost
ENV CONSUL_PORT=8500
ENV CONFIG_DIR=/config

CMD /load-config.sh
