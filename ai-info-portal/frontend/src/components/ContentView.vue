<template>
  <div class="content-view">
    <button @click="goBack" class="back-button">← 返回</button>
    <article v-if="content" class="content-article">
      <h1>{{ content.title }}</h1>
      <div class="content-body" v-html="content.htmlContent"></div>
    </article>
    <div v-else class="loading">
      <p>加载中...</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { getApiUrl } from '@/config/api'

export default {
  name: 'ContentView',
  props: ['slug'],
  data() {
    return {
      content: null
    }
  },
  mounted() {
    this.fetchContent()
  },
  watch: {
    slug() {
      this.fetchContent()
    }
  },
  methods: {
    async fetchContent() {
      try {
        const response = await axios.get(getApiUrl(`/content/${this.slug}`))
        this.content = response.data
      } catch (error) {
        console.error('获取内容失败:', error)
      }
    },
    goBack() {
      this.$router.go(-1)
    }
  }
}
</script>

<style scoped>
.content-view {
  padding: 2rem 0;
}

.back-button {
  background-color: #3498db;
  color: white;
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  cursor: pointer;
  margin-bottom: 1rem;
  font-size: 1rem;
}

.back-button:hover {
  background-color: #2980b9;
}

.content-article {
  background: white;
  border-radius: 8px;
  padding: 2rem;
  box-shadow: 0 2px 8px rgba(0,0,0,0.1);
}

.content-article h1 {
  color: #2c3e50;
  margin-bottom: 1.5rem;
  padding-bottom: 0.5rem;
  border-bottom: 1px solid #eee;
}

.content-body {
  line-height: 1.8;
  color: #34495e;
}

.content-body h2, .content-body h3 {
  color: #2c3e50;
  margin-top: 1.5rem;
  margin-bottom: 1rem;
}

.content-body p {
  margin-bottom: 1rem;
}

.content-body ul, .content-body ol {
  margin-left: 1.5rem;
  margin-bottom: 1rem;
}

.content-body li {
  margin-bottom: 0.5rem;
}

.content-body table {
  width: 100%;
  border-collapse: collapse;
  margin: 1rem 0;
}

.content-body th, .content-body td {
  border: 1px solid #ddd;
  padding: 0.5rem;
  text-align: left;
}

.content-body th {
  background-color: #f8f9fa;
}

.loading {
  text-align: center;
  padding: 3rem;
  color: #7f8c8d;
}
</style>