Nginx 极简教程：https://dunwu.github.io/nginx-tutorial/#/nginx-quickstart

文档：https://blog.csdn.net/weixin_43388691/article/details/127878007

拷贝；docker container cp Nginx:/etc/nginx/nginx.conf /Users/fuzhengwei1/Documents/develop/tmp/nginx/conf


部署：
docker run \
--name Nginx \
-d \
-p 8090:80 \
nginx

docker run \
--name Nginx \
-d \
-v  /Users/fuzhengwei1/Documents/develop/tmp/nginx/html:/usr/share/nginx/html \
-v /Users/fuzhengwei1/Documents/develop/tmp/nginx/conf/nginx.conf:/etc/nginx/nginx.conf \
-p 8090:80 \
nginx

403问题：https://cloud.tencent.com/developer/article/1949092

## Nginx 部署

### 1. 首次安装 Nginx 

```java
docker run \
--name Nginx \
-d \
-p 8090:80 \
nginx
```

访问：http://0.0.0.0:8090/

### 2. 拷贝文件到本地

docker container cp Nginx:/etc/nginx/nginx.conf /Users/fuzhengwei/1024/KnowledgePlanet/api-gateway/api-gateway-center/doc/data/nginx

### 3. 重新安装

docker run \
--name Nginx \
-d \
-v /Users/fuzhengwei/1024/KnowledgePlanet/api-gateway/api-gateway-center/doc/data/html:/usr/share/nginx/html \
-v /Users/fuzhengwei/1024/KnowledgePlanet/api-gateway/api-gateway-center/doc/data/nginx/nginx.conf:/etc/nginx/nginx.conf \
-p 8090:80 \
nginx



user  nginx;
worker_processes  auto;

error_log  /var/log/nginx/error.log notice;
pid        /var/run/nginx.pid;


events {
    worker_connections  1024;
}


http {
    include       /etc/nginx/mime.types;
    default_type  application/octet-stream;

    log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                      '$status $body_bytes_sent "$http_referer" '
                      '"$http_user_agent" "$http_x_forwarded_for"';

    access_log  /var/log/nginx/access.log  main;

    sendfile        on;
    #tcp_nopush     on;

    keepalive_timeout  65;

    #gzip  on;

    include /etc/nginx/conf.d/*.conf;

    # 设定负载均衡的服务器列表
    upstream api01 {

        least_conn;

        server 192.168.1.102:9001;
        server 192.168.1.102:9002;
    }

    # 设定负载均衡的服务器列表
    upstream api02 {
        server 192.168.1.102:9003;
    }

    # HTTP服务器
    server {
        # 监听80端口，用于HTTP协议
        listen  80;

        # 定义使用IP/域名访问
        server_name 192.168.1.102;

        # 首页
        index index.html;

        # 反向代理的路径（upstream绑定），location 后面设置映射的路径
        location / {
            proxy_pass http://192.168.1.102:9001;
        }

        location /api01/ {
            proxy_pass http://api01;
        }

         location /api02/ {
            proxy_pass http://api02;
        }
    }
}
