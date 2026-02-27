<template>
  <div class="category-view">
    <h1>{{ categoryName }}</h1>
    <div v-if="contents.length > 0" class="content-list">
      <div
        v-for="content in contents"
        :key="content.id"
        class="content-card"
        @click="goToContent(content.slug)"
      >
        <h3>{{ content.title }}</h3>
        <div
          class="content-preview"
          v-html="getPreview(content.htmlContent)"
        ></div>
      </div>
    </div>
    <div v-else class="no-content">
      <p>暂无内容</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { getApiUrl } from '@/config/api'

export default {
  name: 'CategoryView',
  props: ['category'],
  data() {
    return {
      contents: [],
      categoryName: ''
    }
  },
  mounted() {
    this.fetchContents()
  },
  watch: {
    category() {
      this.fetchContents()
    }
  },
  methods: {
    async fetchContents() {
      try {
        const categoryMap = {
          'AI_HISTORY': 'AI发展史',
          'POPULAR_PRODUCTS': '热门产品',
          'AI_ENCYCLOPEDIA': 'AI名称百科'
        }

        this.categoryName = categoryMap[this.category] || this.category

        const response = await axios.get(getApiUrl(`/content/category/${this.category}`))
        this.contents = response.data
      } catch (error) {
        console.error('获取内容失败:', error)
      }
    },
    goToContent(slug) {
      this.$router.push(`/content/${slug}`)
    },
    getPreview(html) {
      // 提取HTML前150个字符作为预览
      const div = document.createElement('div')
      div.innerHTML = html
      const text = div.textContent || div.innerText || ''
      return text.substring(0, 150) + (text.length > 150 ? '...' : '')
    }
  }
}
</script>

<style scoped>
.category-view {
  padding: 2rem 0;
}

.content-list {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 1.5rem;
  margin-top: 2rem;
}

.content-card {
  background: white;
  border-radius: 8px;
  padding: 1.5rem;
  box-shadow: 0 2px 8px rgba(0,0,0,0.1);
  cursor: pointer;
  transition: transform 0.2s, box-shadow 0.2s;
}

.content-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 15px rgba(0,0,0,0.15);
}

.content-card h3 {
  color: #2c3e50;
  margin-bottom: 1rem;
}

.content-preview {
  color: #7f8c8d;
  line-height: 1.6;
}

.no-content {
  text-align: center;
  padding: 3rem;
  color: #7f8c8d;
}
</style>