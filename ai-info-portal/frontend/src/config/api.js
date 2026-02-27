// API 配置
// 使用 Vite 代理，所以使用相对路径
const API_BASE_URL = import.meta.env.VITE_API_BASE_URL || '/api'

export const getApiUrl = (path) => {
  return `${API_BASE_URL}${path}`
}

export default {
  API_BASE_URL
}