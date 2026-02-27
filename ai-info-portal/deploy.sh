#!/bin/bash

# AI Info Portal - 一键部署脚本 (CentOS + Docker)
# 使用方法: ./deploy.sh

set -e

echo "========================================"
echo "  AI Info Portal 部署脚本"
echo "========================================"

# 颜色定义
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m'

# 检查 Docker 是否安装
check_docker() {
    if ! command -v docker &> /dev/null; then
        echo -e "${RED}Docker 未安装，正在安装...${NC}"
        yum install -y yum-utils
        yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
        yum install -y docker-ce docker-ce-cli containerd.io docker-compose-plugin
        systemctl start docker
        systemctl enable docker
        echo -e "${GREEN}Docker 安装完成${NC}"
    else
        echo -e "${GREEN}Docker 已安装${NC}"
    fi
}

# 检查 Docker Compose 是否可用
check_docker_compose() {
    if docker compose version &> /dev/null; then
        echo -e "${GREEN}Docker Compose 已可用${NC}"
    elif command -v docker-compose &> /dev/null; then
        echo -e "${GREEN}docker-compose 已安装${NC}"
    else
        echo -e "${YELLOW}正在安装 Docker Compose...${NC}"
        curl -L "https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
        chmod +x /usr/local/bin/docker-compose
        echo -e "${GREEN}Docker Compose 安装完成${NC}"
    fi
}

# 开放防火墙端口
setup_firewall() {
    if command -v firewall-cmd &> /dev/null; then
        echo -e "${YELLOW}配置防火墙...${NC}"
        firewall-cmd --permanent --add-port=80/tcp
        firewall-cmd --permanent --add-port=443/tcp
        firewall-cmd --reload
        echo -e "${GREEN}防火墙配置完成${NC}"
    fi
}

# 部署应用
deploy() {
    echo -e "${YELLOW}开始构建和部署...${NC}"

    # 停止旧容器
    docker compose down 2>/dev/null || docker-compose down 2>/dev/null || true

    # 构建并启动
    if docker compose version &> /dev/null; then
        docker compose up -d --build
    else
        docker-compose up -d --build
    fi

    echo -e "${GREEN}部署完成!${NC}"
}

# 显示状态
show_status() {
    echo ""
    echo "========================================"
    echo -e "${GREEN}  部署成功!${NC}"
    echo "========================================"
    echo ""
    echo "访问地址: http://$(hostname -I | awk '{print $1}')"
    echo ""
    echo "常用命令:"
    echo "  查看日志: docker compose logs -f"
    echo "  停止服务: docker compose down"
    echo "  重启服务: docker compose restart"
    echo ""
    echo "========================================"
}

# 主流程
main() {
    check_docker
    check_docker_compose
    setup_firewall
    deploy
    show_status
}

main