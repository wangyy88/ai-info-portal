import { createApp } from 'vue'
import { createRouter, createWebHistory } from 'vue-router'
import App from './App.vue'
import Home from './components/Home.vue'
import CategoryView from './components/CategoryView.vue'
import ContentView from './components/ContentView.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/category/ai_history',
    name: 'Category',
    component: CategoryView,
    props: true
  },
  {
    path: '/category/popular_products',
    name: 'PopularProducts',
    component: CategoryView,
    props: true
  },
  {
    path: '/category/ai_encyclopedia',
    name: 'AiEncyclopedia',
    component: CategoryView,
    props: true
  },
  {
    path: '/content/:slug',
    name: 'Content',
    component: ContentView,
    props: true
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

const app = createApp(App)
app.use(router)
app.mount('#app')