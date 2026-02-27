# AI Info Portal 部署指南

## 服务器要求

- CentOS 7+ / CentOS Stream
- Docker 已安装
- 开放 80 和 443 端口

## 快速部署步骤

### 1. 上传项目到服务器

```bash
# 方式一: 使用 git clone (推荐)
git clone <你的仓库地址> /opt/ai-info-portal
cd /opt/ai-info-portal

# 方式二: 使用 scp 上传
scp -r ./ai-info-portal root@你的服务器IP:/opt/
```

### 2. 一键部署

```bash
cd /opt/ai-info-portal
chmod +x deploy.sh
./deploy.sh
```

### 3. 验证部署

访问 `http://你的服务器IP` 即可看到网站。

## 手动部署

如果一键脚本有问题，可以手动执行:

```bash
# 构建并启动
docker compose up -d --build

# 查看日志
docker compose logs -f

# 停止服务
docker compose down
```

## HTTPS 配置 (使用域名)

### 方式一: 使用 Nginx 反向代理 + Let's Encrypt

```bash
# 安装 certbot
yum install -y certbot python3-certbot-nginx

# 获取证书 (替换 your-domain.com)
certbot --nginx -d your-domain.com

# 自动续期
systemctl enable certbot-renew.timer
```

### 方式二: 修改 docker-compose 添加 SSL

创建 `nginx-ssl.conf`:

```nginx
server {
    listen 443 ssl;
    server_name your-domain.com;

    ssl_certificate /etc/nginx/ssl/fullchain.pem;
    ssl_certificate_key /etc/nginx/ssl/privkey.pem;

    location / {
        root /usr/share/nginx/html;
        try_files $uri $uri/ /index.html;
    }

    location /api/ {
        proxy_pass http://backend:9004/api/;
    }
}

server {
    listen 80;
    server_name your-domain.com;
    return 301 https://$server_name$request_uri;
}
```

## 常用命令

| 操作 | 命令 |
|------|------|
| 查看状态 | `docker compose ps` |
| 查看日志 | `docker compose logs -f` |
| 重启服务 | `docker compose restart` |
| 停止服务 | `docker compose down` |
| 更新部署 | `git pull && docker compose up -d --build` |

## 目录结构

```
ai-info-portal/
├── backend/                 # 后端 Spring Boot
│   └── Dockerfile
├── frontend/                # 前端 Vue + Vite
│   ├── Dockerfile
│   └── nginx.conf
├── docker-compose.yml       # Docker Compose 配置
├── deploy.sh               # 一键部署脚本
└── README-DEPLOY.md        # 本文档
```