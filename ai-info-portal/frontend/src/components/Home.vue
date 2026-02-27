<template>
  <div class="home">
    <section class="hero">
      <div class="hero-content">
        <h1>欢迎来到AI信息中心</h1>
        <p>探索人工智能的发展历程、热门产品和相关知识</p>
      </div>
    </section>

    <section class="categories">
      <div class="card-grid">
        <router-link to="/category/ai_history" class="card">
          <h3>AI发展史</h3>
          <p>了解人工智能从诞生到现在的重大事件和里程碑</p>
        </router-link>

        <router-link to="/category/popular_products" class="card">
          <h3>热门产品</h3>
          <p>探索当前市场上最受欢迎的人工智能产品和服务</p>
        </router-link>

        <router-link to="/category/ai_encyclopedia" class="card">
          <h3>AI名称百科</h3>
          <p>查阅各种人工智能相关术语和技术名词的解释</p>
        </router-link>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  name: 'Home'
}
</script>

<style scoped>
.hero {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  padding: 4rem 0;
  text-align: center;
  margin-bottom: 2rem;
}

.hero-content h1 {
  font-size: 2.5rem;
  margin-bottom: 1rem;
}

.hero-content p {
  font-size: 1.2rem;
  opacity: 0.9;
}

.categories {
  padding: 2rem 0;
}

.card-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 2rem;
  margin-top: 2rem;
}

.card {
  background: white;
  border-radius: 8px;
  padding: 2rem;
  text-align: center;
  text-decoration: none;
  color: inherit;
  box-shadow: 0 2px 10px rgba(0,0,0,0.1);
  transition: transform 0.3s, box-shadow 0.3s;
}

.card:hover {
  transform: translateY(-5px);
  box-shadow: 0 5px 20px rgba(0,0,0,0.15);
}

.card h3 {
  color: #2c3e50;
  margin-bottom: 1rem;
}

.card p {
  color: #7f8c8d;
}
</style>