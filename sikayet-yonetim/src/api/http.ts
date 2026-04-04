import axios from 'axios'
import { storage } from '../utils/storage'

export const http = axios.create({
  baseURL: 'http://localhost:8080/api',
})

http.interceptors.request.use((config) => {
  const token = storage.getToken()

  if (token) {
    config.headers.Authorization = `Bearer ${token}`
  }

  return config
})
