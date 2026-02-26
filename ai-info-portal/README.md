# AI信息中心

这是一个使用Java + Vue开发的AI信息展示网站，包含AI发展史、热门产品和AI名称百科三个主要模块。

## 项目结构

```
ai-info-portal/
├── backend/                 # Spring Boot 后端服务
│   ├── src/main/java/       # Java源代码
│   ├── src/main/resources/  # 配置文件和静态资源
│   └── pom.xml             # Maven依赖配置
├── frontend/                # Vue前端应用
│   ├── src/                # Vue源代码
│   ├── public/             # 静态资源
│   ├── package.json        # npm依赖配置
│   └── vite.config.js      # Vite构建配置
└── content/                 # Markdown格式的内容文件
    ├── ai_history/         # AI发展史内容
    ├── popular_products/   # 热门产品内容
    └── ai_encyclopedia/    # AI名称百科内容
```

## 安装与运行

### 后端 (Spring Boot)

1. 确保已安装JDK 17+ 和Maven
2. 进入backend目录：
   ```bash
   cd ai-info-portal/backend
   ```
3. 构建项目：
   ```bash
   mvn clean install
   ```
4. 运行应用：
   ```bash
   mvn spring-boot:run
   ```

   或者打包后运行：
   ```bash
   mvn package
   java -jar target/ai-info-backend-0.0.1-SNAPSHOT.jar
   ```

   后端服务将在 http://localhost:8080 上运行

### 前端 (Vue)

1. 确保已安装Node.js和npm
2. 进入frontend目录：
   ```bash
   cd ai-info-portal/frontend
   ```
3. 安装依赖：
   ```bash
   npm install
   ```
4. 运行开发服务器：
   ```bash
   npm run dev
   ```

   前端应用将在 http://localhost:3000 上运行

## API接口

- 获取特定分类的所有内容：`GET /api/content/category/{category}`
- 获取特定内容：`GET /api/content/{slug}`
- 保存内容：`POST /api/content`

支持的分类：
- `AI_HISTORY` - AI发展史
- `POPULAR_PRODUCTS` - 热门产品
- `AI_ENCYCLOPEDIA` - AI名称百科

## 内容管理

所有内容均以Markdown格式存储在`content/`目录中，系统会在启动时自动加载这些内容到数据库中。要添加新内容，只需在相应的子目录中创建Markdown文件，并在应用重启后即可访问。

## 技术栈

- 后端：Spring Boot, JPA, H2 Database, CommonMark (Markdown解析)
- 前端：Vue 3, Vue Router, Axios, Vite