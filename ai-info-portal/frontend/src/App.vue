<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <div id="app">
    <nav class="navbar">
      <div class="nav-container">
        <RouterLink to="/" class="nav-brand">AI Info Portal</RouterLink>
        <ul class="nav-menu">
          <li class="nav-item">
            <RouterLink to="/category/AI_HISTORY" class="nav-link">AI发展史</RouterLink>
          </li>
          <li class="nav-item">
            <RouterLink to="/category/POPULAR_PRODUCTS" class="nav-link">热门产品</RouterLink>
          </li>
          <li class="nav-item">
            <RouterLink to="/category/AI_ENCYCLOPEDIA" class="nav-link">AI名称百科</RouterLink>
          </li>
        </ul>
      </div>
    </nav>

    <main class="main-content">
      <RouterView />
    </main>

    <footer class="footer">
      <div class="container">
        <p>&copy; 2026 AI Info Portal. All rights reserved.</p>
      </div>
    </footer>
  </div>
</template>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  line-height: 1.6;
  color: #333;
  background-color: #f8f9fa;
}

.navbar {
  background-color: #2c3e50;
  padding: 1rem 0;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}

.nav-container {
  max-width: 1200px;
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 2rem;
}

.nav-brand {
  color: white;
  font-size: 1.5rem;
  font-weight: bold;
  text-decoration: none;
}

.nav-menu {
  display: flex;
  list-style: none;
}

.nav-item {
  margin-left: 2rem;
}

.nav-link {
  color: white;
  text-decoration: none;
  transition: color 0.3s;
}

.nav-link:hover,
.nav-link.router-link-active {
  color: #3498db;
}

.main-content {
  max-width: 1200px;
  margin: 2rem auto;
  padding: 0 2rem;
  flex: 1;
}

.footer {
  background-color: #2c3e50;
  color: white;
  padding: 2rem 0;
  margin-top: auto;
}

.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 2rem;
  text-align: center;
}
</style>