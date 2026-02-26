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
    path: '/category/:category',
    name: 'Category',
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